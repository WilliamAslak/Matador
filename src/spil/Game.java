package spil;

public class Game {
    public static void main(String[] args) {

        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Rafflecup rc = new Rafflecup();

        for (int i = 0; i < 100; i++) {
            rc.roll(d1,d2);
            System.out.println(rc.getSum(d1,d2));
        }

    }

}
