package Model;

public class Dice {
    private int faceValue;
    public int getFaceValue(){
        return faceValue;
    }
    public void setFaceValue(int x) {
        faceValue = x;
    }
    public void roll() {
        faceValue = ((int)(Math.random()*6+1));
    }
}