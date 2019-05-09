package by.gsu.pms;

public class Word extends Lexeme{

    private String word;

    public Word(String word){
        super(word);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
