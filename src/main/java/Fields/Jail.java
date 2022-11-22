package Fields;

import spil.Player;

import java.util.ArrayList;

public class Jail extends Field {

    ArrayList<Player> inmates ;

    public Jail(){
    super("Jail");
        inmates = new ArrayList<Player>();
    }
    public void putInJail(Player p){
    inmates.add(p);
    }

    public void release(Player p){
    inmates.remove(p);
    }
}
