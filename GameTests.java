import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {

    private void setNumPlayers(int numPlayers) {
        System.setIn(new ByteArrayInputStream(Integer.toString(numPlayers).getBytes()));
    }

    private void setNumPlayers(String numPlayers) {
        System.setIn(new ByteArrayInputStream(numPlayers.getBytes()));
    }

    @Test
    void testContainsAllItems() {

        Game game = new Game();
        List<Item> items = game.getItems();

        assertTrue(items.contains(new Token("Miss Scarlett")));
        assertTrue(items.contains(new Token("Colonel Mustard")));
        assertTrue(items.contains(new Token("Mrs. White")));
        assertTrue(items.contains(new Token("Mr. Green")));
        assertTrue(items.contains(new Token("Mrs. Peacock")));
        assertTrue(items.contains(new Token("Professor Plum")));

        assertTrue(items.contains(new Weapon("Candlestick")));
        assertTrue(items.contains(new Weapon("Dagger")));
        assertTrue(items.contains(new Weapon("Lead Pipe")));
        assertTrue(items.contains(new Weapon("Revolver")));
        assertTrue(items.contains(new Weapon("Rope")));
        assertTrue(items.contains(new Weapon("Spanner")));

        assertTrue(items.contains(new Room("Ball Room")));
        assertTrue(items.contains(new Room("Conservatory")));
        assertTrue(items.contains(new Room("Billiard Room")));
        assertTrue(items.contains(new Room("Library")));
        assertTrue(items.contains(new Room("Study")));
        assertTrue(items.contains(new Room("Hall")));
        assertTrue(items.contains(new Room("Lounge")));
        assertTrue(items.contains(new Room("Dining Room")));
        assertTrue(items.contains(new Room("Kitchen")));
    }

    @Test
    void testValidAskNumPlayers() {
        Game game = new Game();

        ByteArrayInputStream input;

        for (Integer i = 2; i <=6; i++) {
            setNumPlayers(i);
            assertEquals((int) i, game.askNumPlayers());
        }
    }

    @Test
    void testInvalidAskNumPlayers() {
        Game game = new Game();

        setNumPlayers("a");
        setNumPlayers(7);
        setNumPlayers(1);
        setNumPlayers("abc2421");
        setNumPlayers("aDIA79");
        setNumPlayers("2");

        assertEquals(2, game.askNumPlayers());
    }

    @Test
    void testInitialisedPlayers() {
        ByteArrayInputStream input = new ByteArrayInputStream("5".getBytes());
        System.setIn(input);

        Game game = new Game();

        assertEquals(0, game.getPlayers().size());

        game.generateItems();
        game.initialisePlayers();

        assertEquals(5, game.getPlayers().size());
    }

    @Test
    void testDealToPlayers() {

        Game game = new Game();
        // 21 items in total

        setNumPlayers(2);
        game.initialisePlayers();
        game.dealItemsToPlayers();
        assertEquals(9, game.getPlayers().get(0).getItems().size());
        assertEquals(9, game.getPlayers().get(1).getItems().size());

        setNumPlayers(3);
        game.initialisePlayers();
        game.dealItemsToPlayers();
        for (Player p : game.getPlayers()) {
            assertEquals(6, p.getItems().size());
        }

        setNumPlayers(4);
        game.initialisePlayers();
        game.dealItemsToPlayers();
        for (int i = 0; i < 2; i++) {
            assertEquals(5, game.getPlayers().get(i).getItems().size());
        }
        for (int i = 2; i < 4; i++) {
            assertEquals(4, game.getPlayers().get(i).getItems().size());
        }

        setNumPlayers(5);
        game.initialisePlayers();
        game.dealItemsToPlayers();
        for (int i = 0; i < 3; i++) {
            assertEquals(4, game.getPlayers().get(i).getItems().size());
        }
        for (int i = 3; i < 5; i++) {
            assertEquals(3, game.getPlayers().get(i).getItems().size());
        }

        setNumPlayers(6);
        game.initialisePlayers();
        game.dealItemsToPlayers();
        for (int i = 0; i < 6; i++) {
            assertEquals(3, game.getPlayers().get(i).getItems().size());
        }
    }

}
