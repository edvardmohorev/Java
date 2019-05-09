
import by.gsu.pms.Sentence;
import by.gsu.pms.Text;

import java.io.File;

public class Runner2 {
    private static final String FILE_PATH = "src/in_text.txt";
    private static final String START_CHAR = "с";
    private static final String END_CHAR = "т";
    public static void main(String[] args) {

        Text text = new Text(new File(FILE_PATH));
        System.out.println(text);
        System.out.println("Substring is starting with ->"+START_CHAR+" and ends with ->"+END_CHAR);
        for (Sentence sentence : text.getSentences()){
            String normalSentence = sentence.toString();
            int startIndex = normalSentence.toLowerCase().indexOf(START_CHAR);
            int endIndex = normalSentence.toLowerCase().lastIndexOf(END_CHAR);
            String leftAnswer = normalSentence.substring(0,startIndex).trim();
            String rightAnswer = normalSentence.substring(endIndex).trim();

           // System.out.println("Выпизал:" +normalSentence.substring(startIndex,endIndex));
            System.out.println("Обрезал:" +normalSentence.substring(0,startIndex)+" "+normalSentence.substring(endIndex));
            sentence.setLexemes(new Sentence(leftAnswer+rightAnswer).getLexemes());
        }
        System.out.println(text);
    }
}