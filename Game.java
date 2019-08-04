package GamePackage;

import java.util.*;

public class Game {

    private boolean isOver = true;
    private Board board = new Board();
    private Player currentPlayer;
    private List<Room> rooms = new ArrayList<>();
    private List<Token> tokens = new ArrayList<>();
    private List<Weapon> weapons = new ArrayList<>();
    private List<Player> players = new ArrayList<>();

    public Game() {
        generateItems();
    }

    /**
     * Starts the game.
     */
    private void startGame() {
        printWelcomeMessage();
        initialisePlayers();
        dealItemsToPlayers();
//        board.reset();
    }

    private void run() {

    }

    /**
     * Generates the envelope that contains the randomised murder circumstances.
     *
     * @return Envelope with the murder circumstances.
     */
    public Envelope generateEnvelope() {
        Collections.shuffle(rooms);
        Collections.shuffle(tokens);
        Collections.shuffle(weapons);

        return new Envelope(rooms.get(0), tokens.get(0), weapons.get(0));
    }

    /**
     * Deals all items in the game to the players.
     */
    public void dealItemsToPlayers() {
        List<Item> items = getItems();
        Collections.shuffle(items);

        for (int i = 0; i < items.size(); i++) {
            players.get(i % players.size()).addItem(items.get(i));
        }
    }

    private void gameOver() {

    }

    /**
     * Intialise the players of the game.
     *
     * Asks the user to input the number of players, then randomly assigns each
     * player a character token. It sets the current player.
     */
    public void initialisePlayers() {
        players.clear();
        Collections.shuffle(tokens); // for random character token allocation
        int numPlayers = askNumPlayers();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(tokens.get(i)));
        }
        currentPlayer = players.get(0);
    }

    /**
     * Asks the user to input the number of players.
     *
     * @return Number of players.
     */
    public int askNumPlayers() {
        Scanner input = new Scanner(System.in);
        int numPlayers = 0;

        do {
            printAskPlayerCount();
            try {
                numPlayers = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine(); // clear scanner buffer
            }
        } while (!isValidNumPlayers(numPlayers));

        input.close();
        return numPlayers;
    }

    /**
     * Check to see if the number of players is valid.
     *
     * @param numPlayers Number of players.
     * @return Is valid number of players.
     */
    private boolean isValidNumPlayers(int numPlayers) {
        return 2 <= numPlayers && numPlayers <= 6;
    }

    /**
     * Prints a welcome message at the start of the game.
     */
    private void printWelcomeMessage() {
        System.out.println("Welcome to Cluedo!\n\n");
    }

    /**
     * Prints a query asking the user how many players are playing.
     */
    private void printAskPlayerCount() {
        System.out.print("How many players are playing? (2 to 6): ");
    }

    /**
     * Generates all items which are used in the game and stores them in the
     * set of all items.
     */
    public void generateItems() {
        tokens.add(new Token("Miss Scarlett"));
        tokens.add(new Token("Colonel Mustard"));
        tokens.add(new Token("Mrs. White"));
        tokens.add(new Token("Mr. Green"));
        tokens.add(new Token("Mrs. Peacock"));
        tokens.add(new Token("Professor Plum"));

        weapons.add(new Weapon("Candlestick"));
        weapons.add(new Weapon("Dagger"));
        weapons.add(new Weapon("Lead Pipe"));
        weapons.add(new Weapon("Revolver"));
        weapons.add(new Weapon("Rope"));
        weapons.add(new Weapon("Spanner"));

        rooms.add(new Room("Ball Room"));
        rooms.add(new Room("Conservatory"));
        rooms.add(new Room("Billiard Room"));
        rooms.add(new Room("Library"));
        rooms.add(new Room("Study"));
        rooms.add(new Room("Hall"));
        rooms.add(new Room("Lounge"));
        rooms.add(new Room("Dining Room"));
        rooms.add(new Room("Kitchen"));
    }

    /**
     * Gets all items in the game.
     *
     * @return List of all items.
     */
    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        items.addAll(weapons);
        items.addAll(tokens);
        items.addAll(rooms);
        return items;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public static void main(String[] args) {
        new Game().startGame();
    }
}
