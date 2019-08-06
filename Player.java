package GamePackage;

import GamePackage.Board;
import GamePackage.Item;
import GamePackage.Token;

import javax.management.openmbean.CompositeDataSupport;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Player {

    private Token token;
    private HashSet<Item> items = new HashSet<>();

    /**
     * creates a new player object that stores a character token
     * @param token
     */
    public Player(Token token) {
        this.token = token;
    }

    /**
     * Adds an item to the player's hand
     * @param item
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * checks that the move to the given new position is valid.
     * for a move to be valid it must not:
     *  End on a restricted square.
     *  End on another Player.
     *  End off the board.
     *  Exceed the total number of squares given by the dice roll.
     * @param newPos
     * @param roll
     * @param board
     * @return
     */
    public boolean isValidMove(Point newPos, int roll, Board board) {
        if(newPos.x>23||newPos.x<0||newPos.y>24||newPos.y<0){
            return false;
        }
        int xDist = Math.abs(newPos.x-token.getPoint().x);
        int yDist = Math.abs(newPos.y-token.getPoint().y);
        if(xDist+yDist>roll){
            return false;
        }
        if(board.getRoom(newPos) != null && board.getRoom(newPos).equals(new Room("Restricted"))){
            return false;
        }
        if(board.getToken(newPos) != null){
            return false;
        }
        return true;
    }

    /**
     * Tells the board where to move this players character token
     * @param newPos
     * @param board
     */
    public void move(Point newPos, Board board) {
        board.move(token.getPoint(), newPos);
    }

    /**
     * asks the user if they would like to make a suggestion
     * @return
     */
    public boolean isMakingSuggestion() {
        System.out.print("Do you want to make a suggestion? (y/n): ");
        return Game.askYesNo();
    }

    /**
     * asks the user if they would like to make an accusation
     * @return
     */
    public boolean isMakingAccusation() {
        System.out.print("Do you want to make an accusation? (y/n): ");
        return Game.askYesNo();
    }

    /**
     * asks the user for the room, character and weapon they would like to
     * propose as the murder circumstances
     * For the suggestion to be valid the proposed room must be the room they are currently
     * in
     * @param board
     * @param game
     * @return
     */
    public Envelope makeSuggestion(Board board, Game game) {
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("please enter your suggestion:\n");
            System.out.print("Room: ");
            String room = input.nextLine();
            Room suggestedRoom = new Room(room);
            if (board.isInRoom(token.getPoint(), suggestedRoom)) {
                System.out.println("you must be in a room to suggest that room");
                continue;
            }
            if(!game.getItems().contains(suggestedRoom)){
                System.out.println("invalid room");
                continue;
            }


            System.out.print("Character: ");
            String character = input.nextLine();
            Token suggestedChar = new Token(character);
            if(!game.getItems().contains(suggestedChar)){
                System.out.println("invalid token");
                continue;
            }
            System.out.print("Weapon: ");

            String weapon = input.nextLine();
            Weapon suggestedWeapon = new Weapon(weapon);
            if(!game.getItems().contains(suggestedWeapon)){
                System.out.println("invalid weapon");
                continue;
            }
            return new Envelope(suggestedRoom, suggestedChar, suggestedWeapon);
        }

    }

    /**
     * asks the user for the room character and weapon they think committed the murder
     * @return
     */
    public Envelope makeAccusation() {

        Scanner input = new Scanner(System.in);

        System.out.print("Room: ");
        String room = input.nextLine();
        System.out.print("Character: ");
        String character = input.nextLine();
        System.out.print("Weapon: ");
        String weapon = input.nextLine();

        input.reset();

        return new Envelope(new Room(room),
                new Token(character),
                new Weapon(weapon));
    }

    /**
     * ask the user to present a card matching one of the three
     * items in the given suggestion.
     * @param suggestion
     * @return
     */
    public Item makeRefutation(Envelope suggestion) {

        ArrayList<Item> possibleItems = new ArrayList<>();

        for (Item item : items) {
            if (suggestion.contains(item)) {
                possibleItems.add(item);
            }
        }

        if (possibleItems.size() == 0) {
            System.out.println(token.getName() + " has none of those items");
            return null;
        }

        System.out.println(token.getName() + "'s items:");

        for (Item item : possibleItems) {
            System.out.println("  - " + item.getName());
        }

        Scanner input = new Scanner(System.in);

        String response;

        while (true) {
            System.out.print("Choose an item from the list above: ");

            response = input.nextLine();

            for (Item item : possibleItems) {
                if (item.getName().equals(response)) {
                    return item;
                }
            }

            System.out.println("Invalid item!");
        }
    }

    /**
     * returns the set of items held by the player
     * @return
     */
    public HashSet<Item> getItems() {
        return items;
    }


    public Token getToken() {
        return token;
    }

    /**
     * prints a formatted list of the players items
     */
    public void printItems() {
        System.out.println("You have the following items:");

        for (Item item : items) {
            System.out.println("  - " + item.getName());
        }
    }

}
