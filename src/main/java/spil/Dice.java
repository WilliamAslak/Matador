package spil;

public class Dice {

    private int faceValue;

    public Dice(){

    }

    public int getFaceValue(){
        return faceValue;
    }
    public void roll() {
        faceValue = ((int)(Math.random()*6+1));
    }

}