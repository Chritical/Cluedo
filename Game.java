import java.util.*;

public class Game {

    private boolean isOver = true;
    private Board board = new Board();
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Token> tokens = new ArrayList<>();
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();

    private void startGame() {
        printWelcomeMessage();
        initialisePlayers();
//        board.reset();
    }

    public Envelope generateEnvelope() {
        return new Envelope(new Room(), new Token(), new Weapon());
    }

    public void dealItemsToPlayers() {

    }

    private void gameOver() {

    }

    public void initialisePlayers() {
        Collections.shuffle(tokens); // for random character token allocation
        int numPlayers = askNumPlayers();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(/*tokens.get(i)*/));
        }
    }

    public int askNumPlayers() {
        Scanner input = new Scanner(System.in);
        int numPlayers = 0;

        do {
            printAskPlayerCount();
            try {
                numPlayers = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine();
            }
        } while (invalidNumPlayers(numPlayers));

        input.close();
        return numPlayers;
    }

    private boolean invalidNumPlayers(int numPlayers) {
        return !(1 < numPlayers && numPlayers < 7);
    }

    private void printWelcomeMessage() {
        System.out.println("Welcome to Cluedo!\n\n");
    }

    private void printAskPlayerCount() {
        System.out.print("How many players are playing? (2 to 6): ");
    }

    /**
     * Generates all items which are used in the game and stores them in the
     * set of all items.
     */
//    private void generateItems() {
//        tokens.add(new Token("Miss Scarlett"));
//        tokens.add(new Token("Colonel Mustard"));
//        tokens.add(new Token("Mrs. White"));
//        tokens.add(new Token("Mr. Green"));
//        tokens.add(new Token("Mrs. Peacock"));
//        tokens.add(new Token("Professor Plum"));
//
//        weapons.add(new Weapon("Candlestick"));
//        weapons.add(new Weapon("Dagger"));
//        weapons.add(new Weapon("Lead Pipe"));
//        weapons.add(new Weapon("Revolver"));
//        weapons.add(new Weapon("Rope"));
//        weapons.add(new Weapon("Spanner"));
//
//        rooms.add(new Room("Ball Room"));
//        rooms.add(new Room("Conservatory"));
//        rooms.add(new Room("Billiard Room"));
//        rooms.add(new Room("Library"));
//        rooms.add(new Room("Study");
//        rooms.add(new Room("Hall"));
//        rooms.add(new Room("Lounge"));
//        rooms.add(new Room("Dining Room"));
//        rooms.add(new Room("Kitchen"));
//    }

    public HashSet<Item> getItems() {
        HashSet<Item> items = new HashSet<>();
        items.addAll(weapons);
        items.addAll(tokens);
        items.addAll(rooms);
        return items;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public static void main(String[] args) {
        new Game().startGame();
    }
}
