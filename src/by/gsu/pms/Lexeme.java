package by.gsu.pms;

public class Lexeme {
    private String lexeme;

    public Lexeme(String lexeme){
        this.lexeme = lexeme;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return lexeme;
    }
    @Override
    public boolean equals(Object object){
        return this.lexeme.equals(object.toString());
    }
}
