package Fields;

import spil.Player;

public class Street extends Field{
    String color;
    int price;
    boolean isOwned = false;

    public Street(String name, String color, int price){
        super(name);
        this.color=color;
        this.price=price;
    }

   public void purchase (Player p){

   }

   public void sell (Player p){

   }
}
