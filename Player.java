package GamePackage;

import java.util.HashSet;

public class Player {

    private Token token;
    private HashSet<Item> items = new HashSet<>();

    public Player(Token token) {
        this.token = token;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public HashSet<Item> getItems() {
        return items;
    }
}
