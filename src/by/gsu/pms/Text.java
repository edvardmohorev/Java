package by.gsu.pms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text {

    private List<Sentence> sentences;

    public Text(File file){
        String regex = "[.!?]";
        Pattern pattern = Pattern.compile(regex);
        sentences = new LinkedList<>();
        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                Matcher m = pattern.matcher(line);
                List<String> punctEnds = new ArrayList<>();
                while (m.find()){
                    punctEnds.add(m.group());

                }
                String[] sentencesLine = line.split("[?.]");
                for (int i = 0; i < sentencesLine.length; i++){
                    sentencesLine[i] += punctEnds.get(i);
                }
                for (String sentence : sentencesLine){
                    sentences.add(new Sentence(sentence));
                }
            }
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    @Override
    public String toString() {
        return "Text{" +
                "sentences=" + sentences + "\n" +
                '}';
    }
}
