package test;

import spil.Account;
import spil.Player;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    // Positive test
    @org.junit.jupiter.api.Test
    void setWallet() {
        Player player = new Player();

        player.getAccount().setBalance(-50);
        assertTrue(player.getAccount().getBalance() >= 0, "Fake news");

        player.getAccount().setBalance(50);
        assertFalse(player.getAccount().getBalance()<=0);
    }


    // Negative test
    @org.junit.jupiter.api.Test
    void withdraw() {

    }
}