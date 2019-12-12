import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * проект реализует консольный многопользовательский чат.
 * вход в программу запуска сервера - в классе Server.
 * @author izotopraspadov, the tech
 * @version 2.0
 */

class ServerSomthing extends Thread {

    private Socket socket; // сокет, через который сервер общается с клиентом,
    // кроме него - клиент и сервер никак не связаны
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток завписи в сокет
    private Card[] cards;
    LinkedList<Card> cardLinkedList;
    private int playerScore;
    private int serverScore=0;
    private StringBuilder stringBuilder;

    /**
     * для общения с клиентом необходим сокет (адресные данные)
     * @param socket
     * @throws IOException
     */

    public ServerSomthing(Socket socket) throws IOException {
        this.socket = socket;
        // если потоку ввода/вывода приведут к генерированию искдючения, оно проброситься дальше
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


        // сооюбщений новому поключению
        start(); // вызываем run()
    }
    @Override
    public void run() {
        String word;
        newCard();
        cardLinkedList = new LinkedList<>(Arrays.asList(cards));
        playerScore=0;
        giveCardPlayer();
        try {
            try {
                while (true) {
                    word = in.readLine();
                    if(word.equals("more")) {
                        giveCardPlayer();
                    }
                    if(playerScore>21) {
                        this.downService(); // харакири
                        System.out.println("Player:"+playerScore);
                        System.out.println("Daun");
                        break; // если пришла пустая строка - выходим из цикла прослушки
                    }
                    if (word.equals("full")){
                        while (serverScore<19){
                            giveCardServer();
                            System.out.println("Server:"+serverScore);
                            out.write("Lose");
                            out.flush();
                        }
                        if (playerScore>serverScore ||serverScore>21){
                            System.out.println("Win");
                            out.write("Win");
                            out.flush();
                        }
                        else
                            System.out.println("Lose");
                        System.out.println("Echoing: " + word);
                    }

                }
            } catch (NullPointerException ignored) {} catch (InterruptedException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            this.downService();
        }
    }

    private void giveCardServer() throws InterruptedException {
        Random random=new Random();
        int num=random.nextInt(cardLinkedList.size());
        Card card=cardLinkedList.get(num);
        serverScore+=card.value;
        System.out.println(cardLinkedList.get(num));

        cardLinkedList.remove(num);
        Thread.sleep(1000);
    }

    /**
     * отсылка одного сообщения клиенту по указанному потоку
     * @param msg
     */
    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}
    }
    private void giveCardPlayer(){
        Random random=new Random();
        int num=random.nextInt(cardLinkedList.size());
        Card card=cardLinkedList.get(num);
        playerScore+=card.value;
        try {
            out.write(cardLinkedList.get(num)+"\n"+playerScore+"\n");
            out.flush();
            System.out.println(cardLinkedList.get(num));
            System.out.println(playerScore);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cardLinkedList.remove(num);
    }
    private void newCard(){
        cards= new Card[]{
                new Card("K", "10", 10),
                new Card("K", "J", 3),
                new Card("K", "Q", 4),
                new Card("K", "K", 5),
                new Card("K", "T", 11),
                new Card("Б", "10", 10),
                new Card("Б", "J", 3),
                new Card("Б", "Q", 4),
                new Card("Б", "K", 5),
                new Card("Б", "T", 11),
                new Card("П", "10", 10),
                new Card("П", "J", 3),
                new Card("П", "Q", 4),
                new Card("П", "K", 5),
                new Card("П", "T", 11),
                new Card("Ч", "10", 10),
                new Card("Ч", "J", 3),
                new Card("Ч", "Q", 4),
                new Card("Ч", "K", 5),
                new Card("Ч", "T", 11),
        };
    }
    
    /**
     * закрытие сервера
     * прерывание себя как нити и удаление из списка нитей
     */
    private void downService() {
            try {
            if(!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                for (ServerSomthing vr : Server.serverList) {
                    if(vr.equals(this)) vr.interrupt();
                    Server.serverList.remove(this);
                }
            }
        } catch (IOException ignored) {}
    }
}

/**
 * класс хранящий в ссылочном приватном
 * списке информацию о последних 10 (или меньше) сообщениях
 */

class Story {
    private LinkedList<String> story = new LinkedList<>();
}

public class Server {

    public static final int PORT = 8080;
    public static LinkedList<ServerSomthing> serverList = new LinkedList<>(); // список всех нитей - экземпляров
    // сервера, слушающих каждый своего клиента
    public static Story story; // история переписки
    
    /**
     * @param args
     * @throws IOException
     */
    
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        story = new Story();
        System.out.println("Server Started");
        try {
            while (true) {
                // Блокируется до возникновения нового соединения:
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerSomthing(socket)); // добавить новое соединенние в список
                } catch (IOException e) {
                    // Если завершится неудачей, закрывается сокет,
                    // в противном случае, нить закроет его:
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}