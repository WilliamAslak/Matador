package spil;

public class Rafflecup {

    public Rafflecup(){

    }
    public void roll(Dice d1, Dice d2){
        d1.setFaceValue((int)(Math.random()*6+1));
        d2.setFaceValue((int)(Math.random()*6+1));
    }
    public boolean getEns(Dice d1, Dice d2){
        return d1.getFaceValue() == d2.getFaceValue();
    }
    public int getSum(Dice d1, Dice d2){
        return d1.getFaceValue()+d2.getFaceValue();
    }
}