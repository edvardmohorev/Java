package by.gsu.pms;

public class Mark extends Lexeme{

    private String mark;

    public Mark(String mark){
        super(mark);
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
