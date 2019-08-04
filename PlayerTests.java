//package Tests;
//
//import GamePackage.Board;
//import GamePackage.Player;
//import GamePackage.Token;
//import org.junit.Test;
//
//import java.awt.*;
//import java.util.ArrayList;
//
//import static org.junit.Assert.*;
//
//public class PlayerTests {
//
//    @Test
//    public void tGetItems(){
//        Player p = new Player(new Token("", 0,0));
//        Item  revolver = new Weapon("Revolver");
//        p.addItem(revolver);
//        assertTrue(p.getItems.get(0) == revolver);
//        assertTrue(p.getItems.size() == 0);
//    }
//
//    @Test
//    /**
//     * attempts to add a string in stead of an item
//     */
//    public void tBadAdd(){
//        Player p = new Player(new Token("", 0,0));
//        Item  revolver = new Weapon("Revolver");
//        p.addItem(revolver);
//        try {
//            p.addItem("what?");
//        }catch(BadParameterException e){
//            assertTrue(true);
//            return;
//        }
//        fail();
//    }
//
//    @Test
//    public void tGetToken(){
//        Player p = new Player(new Token("Professor Plum", 0,0));
//        assertEquals(p.getToken(), new Token("Professor Plum", 0,0));
//    }
//
//    @Test
//    public void tRollDice(){
//        Player p = new Player(new Token("Professor Plum", 0,0));
//        ArrayList<Integer> possibleRolls= new ArrayList<>();
//        for(int i =1; i<13;i++){
//            possibleRolls.add(i);
//        }
//        for(int i = 0; i<1000;i++) {
//            assertTrue(possibleRolls.contains(p.roll()));
//        }
//    }
//
//    @Test
//    public void tAllowedSuggestion(){
//        Player p = new Player(new Token("Professor Plum", 0,0));
//        Item dagger = new Weapon("Dagger");
//        Item scarlet = new Token("Miss scarlet",0,0);
//        Item kitchen = new Room("Kitchen");
//        assertTrue(
//p.suggest(kitchen, dagger, scarlet).get(0) == kitchen &&
//        p.suggest(kitchen, dagger, scarlet).get(1) == dagger &&
//        p.suggest(kitchen, dagger, scarlet).get(2) == scarlet
//        );
//    }
//
//    @Test
//    /**
//     * checks that a suggestion must be made with 1 of each item type
//     */
//    public void tTwoWepSuggestion(){
//        Player p = new Player(new Token("Professor Plum", 0,0));
//        Item dagger = new Weapon("Dagger");
//        Item rope = new Weapon("Rope");
//        Item kitchen = new Room("Kitchen");
//        try {
//            p.suggest(kitchen, dagger, rope).get(0);
//        }catch(BadParameterException e){
//            assertTrue(true);
//            return;
//        }
//        fail();
//    }
//
//    @Test
//    public void tAllowedRefutation() {
//        Item scarlet = new Token("Miss scarlet", 8, 8);
//        Player p1 = new Player(new Token("Professor Plum", 0, 0));
//        Player p2 = new Player(scarlet);
//        Item dagger = new Weapon("Dagger");
//
//        Item kitchen = new Room("Kitchen");
//        p2.addItem(dagger);
//        //dagger is the only option available to p2
//        assertTrue(p2.refute(p1.suggest(kitchen, dagger, scarlet), dagger) == dagger);
//    }
//
//    @Test
//    public void tNoRevealRefute() {
//        Item scarlet = new Token("Miss scarlet", 8, 8);
//        Player p1 = new Player(new Token("Professor Plum", 0, 0));
//        Player p2 = new Player(scarlet);
//        Item dagger = new Weapon("Dagger");
//
//        Item kitchen = new Room("Kitchen");
//        Item empty = new Weapon("empty");
//        //p2 does not have to/ cannot reveal a card because they have none
//        assertTrue(p2.refute(p1.suggest(kitchen, dagger, scarlet), empty) == empty);
//    }
//
//    @Test
//    public void tNoRevealRefute2() {
//        Item scarlet = new Token("Miss scarlet", 8, 8);
//        Player p1 = new Player(new Token("Professor Plum", 0, 0));
//        Player p2 = new Player(scarlet);
//        Item dagger = new Weapon("Dagger");
//
//        Item kitchen = new Room("Kitchen");
//        Item empty = new Weapon("empty");
//        p2.addItem(new Item("Revolver"));
//        //p2 does not have to/ cannot reveal a card because none of theirs match the suggestion
//        assertTrue(p2.refute(p1.suggest(kitchen, dagger, scarlet), empty)==empty);
//    }
//
//    @Test
//    /**
//     * tests that refutation with an item not in the suggestion is invalid
//     */
//    public void tBadRefute() {
//        Item scarlet = new Token("Miss scarlet", 8, 8);
//        Player p1 = new Player(new Token("Professor Plum", 0, 0));
//        Player p2 = new Player(scarlet);
//        Item dagger = new Weapon("Dagger");
//
//        Item kitchen = new Room("Kitchen");
//        Item revolver = new Item("Revolver");
//        p2.addItem(revolver);
//        assertTrue(p2.refute(p1.suggest(kitchen, dagger, scarlet), revolver)==null);
//    }
//
//    @Test
//    /**
//     * tests that refutation with an item not held by the player is invalid
//     */
//    public void tBadRefute2() {
//        Player p1 = new Player(new Token("Professor Plum", 0, 0));
//        Player p2 = new Player(new Token("Miss Scarlet"), 8, 8);
//        Item dagger = new Weapon("Dagger");
//        Item scarlet = new Token("Miss scarlet", 0, 0);
//        Item kitchen = new Room("Kitchen");
//        Item revolver = new Item("Revolver");
//        p2.addItem(revolver);
//        assertTrue(p2.refute(p1.suggest(kitchen, dagger, scarlet), new Room("Conservatory"))==null);
//    }
//
//    @Test
//    public void tValidAccusation() {
//        Item dagger = new Weapon("Dagger");
//        Item scarlet = new Token("Miss scarlet", 0, 0);
//        Item kitchen = new Room("Kitchen");
//        Player p1 = new Player(new Envelope(kitchen, dagger, scarlet),(new Token("Professor Plum", 0, 0)));
//
//        assertTrue(p1.accuse(kitchen, dagger, scarlet));
//    }
//
//    @Test
//    public void tWrongAccusation() {
//        Item dagger = new Weapon("Dagger");
//        Item scarlet = new Token("Miss scarlet", 0, 0);
//        Item kitchen = new Room("Kitchen");
//        Item revolver = new Item("Revolver");
//        Player p1 = new Player(new Envelope(kitchen, dagger, scarlet),(new Token("Professor Plum", 0, 0)));
//
//        assertFalse(p1.accuse(kitchen, revolver, scarlet));
//    }
//
//    @Test
//    public void tInvalidAccusation() {
//        Item dagger = new Weapon("Dagger");
//        Item scarlet = new Token("Miss scarlet", 0, 0);
//        Item kitchen = new Room("Kitchen");
//        Item revolver = new Item("Revolver");
//        Player p1 = new Player(new Envelope(kitchen, dagger, scarlet),(new Token("Professor Plum", 0, 0)));
//        try {
//            p1.accuse(dagger, revolver, scarlet);
//        }catch(BadParameterException e){
//            assertTrue(true);
//            return;
//        }
//        fail();
//    }
//
//    @Test
//    public void tPlayerMove() {
//        ArrayList<Token> tokens = new ArrayList<Token>();
//        Token green = new Token("Mr. Green", 14, 0);
//        tokens.add(green);
//        Board board = new Board(tokens);
//        Player p1 = new Player(green);
//        assertTrue(p1.moveToken(19, 7, board, 12));
//        assertTrue(board.getToken(19,7) == green);
//        assertTrue(board.getToken(14, 0) == null);
//    }
//
//    @Test
//    /**
//     * checks move distance does not exceed dice roll
//     */
//    public void tPlayerInvalidMove1() {
//        ArrayList<Token> tokens = new ArrayList<Token>();
//        Token green = new Token("Mr. Green", 14, 0);
//        tokens.add(green);
//        Board board = new Board(tokens);
//        Player p1 = new Player(green);
//        assertFalse(p1.moveToken(20, 7, board, 12));
//        assertTrue(board.getToken(14,0) == green);
//        assertTrue(board.getToken(20, 7) == null);
//    }
//
//    @Test
//    /**
//     * checks move does not end up on restricted square
//     */
//    public void tPlayerInvalidMove2() {
//        ArrayList<Token> tokens = new ArrayList<Token>();
//        Token green = new Token("Mr. Green", 14, 0);
//        tokens.add(green);
//        Board board = new Board(tokens);
//        Player p1 = new Player(green);
//        assertFalse(p1.moveToken(2, 0, board, 12));
//        assertTrue(board.getToken(14,0) == green);
//        assertTrue(board.getToken(2, 0) == null);
//    }
//
//    @Test
//    /**
//     * checks move does not end up on another character
//     */
//    public void tPlayerInvalidMove3() {
//        ArrayList<Token> tokens = new ArrayList<Token>();
//        Token green = new Token("Mr. Green", 14, 0);
//        Token scarlet = new Token("Miss Scarlet", 19, 7);
//        tokens.add(green);
//        tokens.add(scarlet);
//        Board board = new Board(tokens);
//        Player p1 = new Player(green);
//        assertFalse(p1.moveToken(19, 7, board, 12));
//        assertTrue(board.getToken(14,0) == green);
//        assertTrue(board.getToken(19, 7) == scarlet);
//
//    }
//
//
//
//
//
//}
