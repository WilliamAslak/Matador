package spil;

public class Field {

    private final String title;
    private final int value;
    private final boolean extraTurn;

    public Field(String t, int v, boolean e){
        title = t;
        value = v;
        extraTurn = e;
    }

    public String getTitle(){
        return title;
    }
    public int getValue() {
        return value;
    }
    public boolean isExtraTurn(){
        return extraTurn;
    }

}
