package test;

import spil.Account;
import spil.Player;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    // Positive test
    @org.junit.jupiter.api.Test
    void setWallet() {
        Player player = new Player();

        player.getAccount().setWallet(-5000);
        assertTrue(player.getAccount().getWallet() >= 0, "Fake news");
    }


    // Negative test
    @org.junit.jupiter.api.Test
    void withdraw() {
        Player player= new Player();

        player.getAccount().withdraw(10000);
        assertFalse(player.getAccount().getWallet()<0);
    }
}