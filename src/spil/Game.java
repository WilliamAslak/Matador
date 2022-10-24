package spil;

public class Game {
    public static void main(String[] args) {

        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Rafflecup rc = new Rafflecup();

        Field fields[] = new Field[]{
                new Field("",0,false), //first field is irrelevant as you cant get 1 with 2 dice
                new Field("Tower", 250, false),
                new Field("Crater", -100, false),
                new Field("Palace gates", 100, false),
                new Field("Cold Desert", -20, false),
                new Field("Walled city", 180, false),
                new Field("Monastery",0,false),
                new Field("Black cave",-70,false),
                new Field("Huts in the mountain",60,false),
                new Field("The Werewall (werewolf-wall)",-80,true),
                new Field("The pit",-50,false),
                new Field("Goldmine",650,false)
        };


    }

}
