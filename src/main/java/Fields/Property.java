package Fields;

import spil.Player;

public class Property extends Field{
    String color;
    int price;
    int rent;
    boolean isOwned;
    public Property(String color,int price,int rent,boolean isOwned){
        super("Property");
        this.color=color;
        this.price=price;
        this.rent=rent;
        this.isOwned=isOwned;
    }
   public void purchase (Player p){



   }

   public void sell (Player p){

   }
}
