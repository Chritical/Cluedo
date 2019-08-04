import java.awt.*;
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
        System.out.println("Do you want to make a suggestion? (y/n): ");
        return askYesNo();
    }

    public boolean isMakingAccusation() {
        System.out.println("Do you want to make an accusation? (y/n): ");
        return askYesNo();
    }

    private boolean askYesNo() {
        Scanner input = new Scanner(System.in);
        String response = input.next();
        input.close();
        return response.equals("y");
    }

    public Envelope makeSuggestion() {
        return null;
    }

    public Envelope makeAccusation() {
        return null;
    }

    public Item makeRefutation(Envelope suggestion) {
        // print out items
        return null;
    }

    public HashSet<Item> getItems() {
        return items;
    }
}
