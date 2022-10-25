package spil;

public class Field {

    private String title;
    private int value;
    private boolean extraTurn;

    public Field(String t, int v){
        title = t;
        value = v;
        extraTurn = false;
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

    public void setExtraTurn(boolean extraTurn) {
        this.extraTurn = extraTurn;
    }

}
