import javax.management.openmbean.CompositeDataSupport;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Player {

    private Token token;
    private HashSet<Item> items = new HashSet<>();

    public Player(Token token) {
        this.token = token;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean isValidMove(Point newPos, int roll, Board board) {
        return true;
    }

    public boolean move(Point newPos, Board board) {
        return false;
    }

    public boolean isMakingSuggestion() {
        System.out.print("Do you want to make a suggestion? (y/n): ");
        return Game.askYesNo();
    }

    public boolean isMakingAccusation() {
        System.out.print("Do you want to make an accusation? (y/n): ");
        return Game.askYesNo();
    }

    public Envelope makeSuggestion() {
        return null;
    }

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

    public Item makeRefutation(Envelope suggestion) {

        ArrayList<Item> possibleItems = new ArrayList<>();

        for (Item item : items) {
            if (suggestion.contains(item)) {
                possibleItems.add(item);
            }
        }

        if (possibleItems.size() == 0) return null;

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

    public HashSet<Item> getItems() {
        return items;
    }

    public Token getToken() {
        return token;
    }

    public void printItems() {
        System.out.println("You have the following items:");

        for (Item item : items) {
            System.out.println("  - " + item.getName());
        }
    }
}
