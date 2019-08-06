package Tests;

import GamePackage.*;
import org.junit.Test;


import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTests {

    @Test
    public void tGetItems(){
        Player p = new Player(new Token(""));
        Item revolver = new Weapon("Revolver");
        p.addItem(revolver);
        for(Item i:p.getItems()){
            assertTrue(i== revolver);
        }
        assertTrue(p.getItems().size() == 1);
    }



    @Test
    public void tGetToken(){
        Player p = new Player(new Token("Professor Plum"));
        assertEquals(p.getToken(), new Token("Professor Plum"));
    }

//    @Test
//    public void tRollDice(){
//        Player p = new Player(new Token("Professor Plum"));
//        ArrayList<Integer> possibleRolls= new ArrayList<>();
//        for(int i =1; i<13;i++){
//            possibleRolls.add(i);
//        }
//        for(int i = 0; i<1000;i++) {
//            assertTrue(possibleRolls.contains(p.roll()));
//        }
//    }

//    @Test
//    public void tAllowedSuggestion(){
//        Player p = new Player(new Token("Professor Plum"));
//        Weapon dagger = new Weapon("Dagger");
//        Token scarlett = new Token("Miss Scarlett");
//        Room kitchen = new Room("Kitchen");
//        Envelope env = new Envelope(kitchen, scarlett, dagger);
//        assertTrue(
//p.makeSuggestion(new Board(), new Game("Test")).getRoom() == kitchen &&
//        p.makeSuggestion(new Board(), new Game("Test")).getWeapon() == dagger &&
//        p.makeSuggestion(new Board(), new Game("Test")).getToken() == scarlett
//        );
//    }



//    @Test
//    public void tAllowedRefutation() {
//        Token scarlet = new Token("Miss scarlet");
//        Player p1 = new Player(new Token("Professor Plum"));
//        Player p2 = new Player(scarlet);
//        Weapon dagger = new Weapon("Dagger");
//        Room kitchen = new Room("Kitchen");
//        p2.addItem(dagger);
//        //dagger is the only option available to p2
//        assertTrue(p2.refute(p1.suggest(new Envelope(kitchen,scarlet, dagger))) == dagger);
//    }

//    @Test
//    public void tNoRevealRefute() {
//        Token scarlet = new Token("Miss scarlet");
//        Player p1 = new Player(new Token("Professor Plum"));
//        Player p2 = new Player(scarlet);
//        Weapon dagger = new Weapon("Dagger");
//
//        Room kitchen = new Room("Kitchen");
//        //p2 does not have to/ cannot reveal a card because they have none
//        assertTrue(p2.refute(p1.suggest(kitchen, scarlet,dagger )) == empty);
//    }

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

//    @Test
//    public void tValidAccusation() {
//        Item dagger = new Weapon("Dagger");
//        Item scarlet = new Token("Miss scarlet", 0, 0);
//        Item kitchen = new Room("Kitchen");
//        Player p1 = new Player(new Envelope(kitchen, dagger, scarlet),(new Token("Professor Plum", 0, 0)));
//
//        assertTrue(p1.accuse(kitchen, dagger, scarlet));
//    }

//    @Test
//    public void tWrongAccusation() {
//        Weapon dagger = new Weapon("Dagger");
//        Token scarlett = new Token("Miss scarlet");
//        Room kitchen = new Room("Kitchen");
//        Weapon revolver = new Weapon("Revolver");
//        Player p1 = new Player(scarlett);
//
//        assertFalse(p1.makeAccusation(new Envelope(kitchen, scarlett, revolver));
//    }



    @Test
    /**
     * tests player moving to a hallway space
     */
    public void tPlayerMove() {
        Token green = new Token("Mr. Green");
        Board board = new Board();
        Player p1 = new Player(green);
        board.addToken(green);
        p1.move(new Point(19,7), board);
        assertEquals(
                "   a b c d e f g h i j k l m n o p q r s t u v w x\n" +
                        "A |X|X|X|X|X|X|X|X|X| |X|X|X|X| |X|X|X|X|X|X|X|X|X|\n"+
                        "B |K|K|K|K|K|K|X| | | |B|B|B|B| | | |X|C|C|C|C|C|C|\n"+
                        "C |K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | |C|C|C|C|C|C|\n"+
                        "D |K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | |C|C|C|C|C|C|\n"+
                        "E |K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | |C|C|C|C|C|C|\n"+
                        "F |K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | | |C|C|C|C|X|\n"+
                        "G |X|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | | | | | | | |\n"+
                        "H | | | | | | | | |B|B|B|B|B|B|B|B| | | |g| | | |X|\n"+
                        "I |X| | | | | | | | | | | | | | | | | |b|b|b|b|b|b|\n"+
                        "J |D|D|D|D|D| | | | | | | | | | | | | |b|b|b|b|b|b|\n"+
                        "K |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |b|b|b|b|b|b|\n"+
                        "L |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |b|b|b|b|b|b|\n"+
                        "M |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |b|b|b|b|b|b|\n"+
                        "N |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | | | | | | |X|\n"+
                        "O |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |L|L|L|L|L|X|\n"+
                        "P |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | |L|L|L|L|L|L|L|\n"+
                        "Q |X| | | | | | | | | |X|X|X|X|X| | |L|L|L|L|L|L|L|\n"+
                        "R | | | | | | | | | | | | | | | | | |L|L|L|L|L|L|L|\n"+
                        "S |X| | | | | | | | |H|H|H|H|H|H| | | |L|L|L|L|L|X|\n"+
                        "T |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | | | | | | | | |\n"+
                        "U |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | | | | | | | |X|\n"+
                        "V |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | |S|S|S|S|S|S|S|\n"+
                        "W |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | |S|S|S|S|S|S|S|\n"+
                        "X |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | |S|S|S|S|S|S|S|\n"+
                        "Y |l|l|l|l|l|l|X| |X|H|H|H|H|H|H|X| |X|S|S|S|S|S|S|\n"+
                        "\n"+
                        "K = Kitchen, B = Ball Room, C = Conservatory,\n"+
                        "L = Library, S = Study, H = Hall, l = Lounge, \n"+
                        "D = Dining Room, X = Restricted, s = Scarlett, \n"+
                        "e = Peacock, m = Mustard, g = Green, p = Plum, \n"+
                        "w = White",board.toString()
        );
    }

    @Test
    /**
     * checks multiple moves
     */
    public void tMoveAfterFail() {
        Token green = new Token("Mr. Green");
        Board board = new Board();
        Player p1 = new Player(green);
        board.addToken(green);
        p1.move(new Point(19,7), board);
        assertEquals(
                "   a b c d e f g h i j k l m n o p q r s t u v w x\n" +
                        "A |X|X|X|X|X|X|X|X|X| |X|X|X|X| |X|X|X|X|X|X|X|X|X|\n"+
                        "B |K|K|K|K|K|K|X| | | |B|B|B|B| | | |X|C|C|C|C|C|C|\n"+
                        "C |K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | |C|C|C|C|C|C|\n"+
                        "D |K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | |C|C|C|C|C|C|\n"+
                        "E |K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | |C|C|C|C|C|C|\n"+
                        "F |K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | | |C|C|C|C|X|\n"+
                        "G |X|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | | | | | | | |\n"+
                        "H | | | | | | | | |B|B|B|B|B|B|B|B| | | |g| | | |X|\n"+
                        "I |X| | | | | | | | | | | | | | | | | |b|b|b|b|b|b|\n"+
                        "J |D|D|D|D|D| | | | | | | | | | | | | |b|b|b|b|b|b|\n"+
                        "K |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |b|b|b|b|b|b|\n"+
                        "L |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |b|b|b|b|b|b|\n"+
                        "M |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |b|b|b|b|b|b|\n"+
                        "N |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | | | | | | |X|\n"+
                        "O |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |L|L|L|L|L|X|\n"+
                        "P |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | |L|L|L|L|L|L|L|\n"+
                        "Q |X| | | | | | | | | |X|X|X|X|X| | |L|L|L|L|L|L|L|\n"+
                        "R | | | | | | | | | | | | | | | | | |L|L|L|L|L|L|L|\n"+
                        "S |X| | | | | | | | |H|H|H|H|H|H| | | |L|L|L|L|L|X|\n"+
                        "T |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | | | | | | | | |\n"+
                        "U |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | | | | | | | |X|\n"+
                        "V |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | |S|S|S|S|S|S|S|\n"+
                        "W |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | |S|S|S|S|S|S|S|\n"+
                        "X |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | |S|S|S|S|S|S|S|\n"+
                        "Y |l|l|l|l|l|l|X| |X|H|H|H|H|H|H|X| |X|S|S|S|S|S|S|\n"+
                        "\n"+
                        "K = Kitchen, B = Ball Room, C = Conservatory,\n"+
                        "L = Library, S = Study, H = Hall, l = Lounge, \n"+
                        "D = Dining Room, X = Restricted, s = Scarlett, \n"+
                        "e = Peacock, m = Mustard, g = Green, p = Plum, \n"+
                        "w = White",board.toString()
        );

        p1.move(new Point(19,10), board);
        assertEquals(
                "   a b c d e f g h i j k l m n o p q r s t u v w x\n" +
                        "A |X|X|X|X|X|X|X|X|X| |X|X|X|X| |X|X|X|X|X|X|X|X|X|\n"+
                        "B |K|K|K|K|K|K|X| | | |B|B|B|B| | | |X|C|C|C|C|C|C|\n"+
                        "C |K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | |C|C|C|C|C|C|\n"+
                        "D |K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | |C|C|C|C|C|C|\n"+
                        "E |K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | |C|C|C|C|C|C|\n"+
                        "F |K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | | |C|C|C|C|X|\n"+
                        "G |X|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | | | | | | | |\n"+
                        "H | | | | | | | | |B|B|B|B|B|B|B|B| | | | | | | |X|\n"+
                        "I |X| | | | | | | | | | | | | | | | | |b|b|b|b|b|b|\n"+
                        "J |D|D|D|D|D| | | | | | | | | | | | | |b|b|b|b|b|b|\n"+
                        "K |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |b|g|b|b|b|b|\n"+
                        "L |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |b|b|b|b|b|b|\n"+
                        "M |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |b|b|b|b|b|b|\n"+
                        "N |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | | | | | | |X|\n"+
                        "O |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |L|L|L|L|L|X|\n"+
                        "P |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | |L|L|L|L|L|L|L|\n"+
                        "Q |X| | | | | | | | | |X|X|X|X|X| | |L|L|L|L|L|L|L|\n"+
                        "R | | | | | | | | | | | | | | | | | |L|L|L|L|L|L|L|\n"+
                        "S |X| | | | | | | | |H|H|H|H|H|H| | | |L|L|L|L|L|X|\n"+
                        "T |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | | | | | | | | |\n"+
                        "U |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | | | | | | | |X|\n"+
                        "V |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | |S|S|S|S|S|S|S|\n"+
                        "W |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | |S|S|S|S|S|S|S|\n"+
                        "X |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | |S|S|S|S|S|S|S|\n"+
                        "Y |l|l|l|l|l|l|X| |X|H|H|H|H|H|H|X| |X|S|S|S|S|S|S|\n"+
                        "\n"+
                        "K = Kitchen, B = Ball Room, C = Conservatory,\n"+
                        "L = Library, S = Study, H = Hall, l = Lounge, \n"+
                        "D = Dining Room, X = Restricted, s = Scarlett, \n"+
                        "e = Peacock, m = Mustard, g = Green, p = Plum, \n"+
                        "w = White",board.toString()
        );
        assertTrue(p1.isValidMove(new Point(19,11),1,board));
    }

    @Test
    /**
     * tests player moving to a room space
     */
    public void tPlayerMove2() {
        Token green = new Token("Mr. Green");
        Board board = new Board();
        Player p1 = new Player(green);
        board.addToken(green);
        p1.move(new Point(8,6), board);
        assertEquals("   a b c d e f g h i j k l m n o p q r s t u v w x\n" +
                "A |X|X|X|X|X|X|X|X|X| |X|X|X|X| |X|X|X|X|X|X|X|X|X|\n"+
                "B |K|K|K|K|K|K|X| | | |B|B|B|B| | | |X|C|C|C|C|C|C|\n"+
                "C |K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | |C|C|C|C|C|C|\n"+
                "D |K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | |C|C|C|C|C|C|\n"+
                "E |K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | |C|C|C|C|C|C|\n"+
                "F |K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | | |C|C|C|C|X|\n"+
                "G |X|K|K|K|K|K| | |g|B|B|B|B|B|B|B| | | | | | | | |\n"+
                "H | | | | | | | | |B|B|B|B|B|B|B|B| | | | | | | |X|\n"+
                "I |X| | | | | | | | | | | | | | | | | |b|b|b|b|b|b|\n"+
                "J |D|D|D|D|D| | | | | | | | | | | | | |b|b|b|b|b|b|\n"+
                "K |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |b|b|b|b|b|b|\n"+
                "L |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |b|b|b|b|b|b|\n"+
                "M |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |b|b|b|b|b|b|\n"+
                "N |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | | | | | | |X|\n"+
                "O |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |L|L|L|L|L|X|\n"+
                "P |D|D|D|D|D|D|D|D| | |X|X|X|X|X| | |L|L|L|L|L|L|L|\n"+
                "Q |X| | | | | | | | | |X|X|X|X|X| | |L|L|L|L|L|L|L|\n"+
                "R | | | | | | | | | | | | | | | | | |L|L|L|L|L|L|L|\n"+
                "S |X| | | | | | | | |H|H|H|H|H|H| | | |L|L|L|L|L|X|\n"+
                "T |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | | | | | | | | |\n"+
                "U |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | | | | | | | |X|\n"+
                "V |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | |S|S|S|S|S|S|S|\n"+
                "W |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | |S|S|S|S|S|S|S|\n"+
                "X |l|l|l|l|l|l|l| | |H|H|H|H|H|H| | |S|S|S|S|S|S|S|\n"+
                "Y |l|l|l|l|l|l|X| |X|H|H|H|H|H|H|X| |X|S|S|S|S|S|S|\n"+
                "\n"+
                "K = Kitchen, B = Ball Room, C = Conservatory,\n"+
                "L = Library, S = Study, H = Hall, l = Lounge, \n"+
                "D = Dining Room, X = Restricted, s = Scarlett, \n"+
                "e = Peacock, m = Mustard, g = Green, p = Plum, \n"+
                "w = White"
                ,board.toString());
    }
    @Test
    /**
     * Tests checkValid move works for a normal move
     */
    public void tCheckValidMove(){
        Token green = new Token("Mr. Green");
        Board board = new Board();
        Player p1 = new Player(green);
        assertFalse(p1.isValidMove(new Point(19,7),12, board));
    }

    @Test
    /**
     * checks player cannot move farther than dice roll
     */
    public void tCheckInvalidMove1() {
        Token green = new Token("Mr. Green");
        Board board = new Board();
        Player p1 = new Player(green);
        assertFalse(p1.isValidMove(new Point(20,7),12, board));
    }


    @Test
    /**
     * checks move does not end up on restricted square
     */
    public void tCheckInvalidMove2() {
        Token green = new Token("Mr. Green");
        Board board = new Board();
        Player p1 = new Player(green);
        assertFalse(p1.isValidMove(new Point(2,0),12, board));
    }

    @Test
    /**
     * checks move does not end up on another character
     */
    public void tCheckInvalidMove3() {
        Token green = new Token("Mr. Green");
        Token white = new Token("Mrs. White");
        Board board = new Board();
        board.addToken(green);
        board.addToken(white);
        Player p1 = new Player(green);
        assertFalse(p1.isValidMove(new Point(9,0),12, board));
    }

    @Test
    /**
     * checks move does not end up off the map
     */
    public void tCheckInvalidMove4() {
        Token green = new Token("Mr. Green");
        Board board = new Board();
        board.addToken(green);
        Player p1 = new Player(green);
        assertFalse(p1.isValidMove(new Point(14,-1),12, board));
    }
    }






