package test;

import Model.Player;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    // Positive test
    @org.junit.jupiter.api.Test
    void deposit() {
        Player player = new Player("Kaj", 0);

        int expected = 50;
        player.getAccount().deposit(50);

        int actual = player.getAccount().getWallet();
        assertEquals(expected, actual);
    }

    // Negative test
    @org.junit.jupiter.api.Test
    void withdraw() {
        Player player = new Player("Kaj", 421);

        player.getAccount().withdraw(420);

        assertFalse(player.getAccount().getWallet()>1);
    }

    // Negative value test
    @org.junit.jupiter.api.Test
    void negative() {
        Player player = new Player("Kaj", 400);

        player.getAccount().withdraw(420);

        assertTrue(player.getAccount().getWallet()>=0);
    }
}