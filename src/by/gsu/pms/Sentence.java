package by.gsu.pms;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sentence {
    private List<Lexeme> lexemes;

    private static final String PUNCTUATION_REGEX = "[«»,.!?/\\-]";

    public Sentence(){
    }

    public Sentence(String sentence){
        lexemes = new LinkedList<>();

        String[] lexemesLine = sentence.split(" ");
        for (String lexeme : lexemesLine){
            Word word = null;
            Mark mark = null;

            Pattern pattern = Pattern.compile(PUNCTUATION_REGEX);
            Matcher matcher = pattern.matcher(lexeme);
            boolean isFoundOnStartOfLexeme = false;

            while (matcher.find()){
                if (matcher.start() == 0){
                    isFoundOnStartOfLexeme = true;
                }
                mark = new Mark(matcher.group());
            }

            for (String item : lexeme.split(PUNCTUATION_REGEX)){
                word = new Word(item);
            }

            if (isFoundOnStartOfLexeme){

                lexemes.add(mark);
                if (word != null){
                    lexemes.add(word);
                }

            }else {

                if (word != null){
                    lexemes.add(word);
                }
                if (mark != null){
                    lexemes.add(mark);
                }

            }
        }
    }

    public List<Lexeme> getLexemes() {
        return lexemes;
    }

    public void setLexemes(List<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    @Override
    public String toString() {
        return "\nSentence{" +
                "lexemes=" + lexemes +
                '}';
    }
}
