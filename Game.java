import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * The game of Cluedo.
 *
 * Cleudo is a murder mystery game in which the <code>Player</code>s must move
 * around the board, making suggestions in order to determine the murder
 * circumstances. Each player controls a character <code>Token</code> and holds
 * a set of cards. Each card represents an <code>Item</code> in the game. An
 * <code>Item</code> can be a <code>Room</code>, <code>Token</code>, or a
 * <code>Weapon</code>.
 *
 * On a player's turn they roll two six-sided dice to determine the number of
 * squares they can move. Once their <code>Token</code> is in a room, they can
 * make a suggestion. A suggestion consists of three <code>Item</code>s, one of
 * each type. Every other player then reveals a card (refutation) only to the
 * current player. The card must be one of the <code>Item</code>s that the
 * current player suggested. If another player doesn't hold an
 * <code>Item</code> that was suggested, they don't show any cards.
 *
 * At the end of any player's turn, they can make an accusation. An accusation
 * is similar to a suggestion in that they choose one of each
 * <code>Item</code>. The <code>Item</code>s are then compared to the murder
 * circumstances randomly chosen at the beginning of the game. If the player
 * chose all three correctly, they win. Otherwise, they lose and are out of the
 * game, but they can still make refutations.
 *
 * The game is over when one player guesses correctly, or everyone guesses
 * incorrectly.
 */
public class Game {

    private boolean isOver = false;
    private Board board = new Board();
    private int currentPlayer;
    private List<Integer> lostPlayers = new ArrayList<>();
    private List<Room> rooms = new ArrayList<>();
    private List<Token> tokens = new ArrayList<>();
    private List<Weapon> weapons = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private Envelope murderCircumstances;

    public Game() {
        generateItems();
    }

    /**
     * Starts the game.
     */
    private void startGame() {
        printWelcomeMessage();
        initialisePlayers();
        murderCircumstances = generateEnvelope();
        dealItemsToPlayers();

        while (true) {
            run();
        }
//        board.reset();
    }

    private void run() {

        while (!isOver) {
            turn();
        }

    }

    private void turn() {

        int roll = generateDiceRoll(2);
        Player workingPlayer = players.get(currentPlayer);

        System.out.println(board.toString());

        printRoll(roll);
        handlePlayerMove(roll);

        if (board.isInRoom(workingPlayer) && workingPlayer.isMakingSuggestion()) {
            playerRefutations(workingPlayer.makeSuggestion());
        }

        handlePlayerAccusation();
        nextPlayer();
    }

    private void playerRefutations(Envelope suggestion) {

        for (int player = 0; player < players.size(); player++) {

            if (currentPlayer == player) continue;
            players.get(player).makeRefutation(suggestion);
        }

    }

    private void handlePlayerAccusation() {

        // if successful
        //   game over, they won
        // else
        //   player loses

        Player workingPlayer = players.get(currentPlayer);

        if (workingPlayer.isMakingAccusation()) {
            Envelope accusation = workingPlayer.makeAccusation();



            if (murderCircumstances.equals(accusation)) {
                isOver = true;
                printGameOver();
            }
        }

    }

    private void printGameOver() {
        System.out.println("Congratulations!\nPlayer " + (currentPlayer + 1) + " has won the game!\n");
    }

    private void nextPlayer() {
        currentPlayer++;
        if (currentPlayer >= players.size()) currentPlayer = 0;
    }

    private int generateDiceRoll(int numDice) {
        Random random = new Random();
        int total = numDice; // to account for r.nextInt() having range [0,5]
        for (int i = 0; i < numDice; i++) total += random.nextInt(6);
        return total;
    }

    private void printRoll(int roll) {
        System.out.println("You rolled: " + roll);
    }

    private void handlePlayerMove(int roll) {

        Scanner input = new Scanner(System.in);
        Point newPos;
        String regex = "[A-Z][a-z]";
        Player workingPlayer = players.get(currentPlayer);

        printAskMove();

        while (true) {

            if (input.hasNext()) {
                String m = input.next();

                if (m.matches(regex)) {
                    newPos = toPoint(m);

                    if (workingPlayer.isValidMove(newPos, roll, board)) {
                        break;
                    } else {
                        System.out.println("Invalid move.");
                    }
                }
            }
        }

        workingPlayer.move(newPos, board);
        input.close();
    }

    private Point toPoint(String pos) {
        char[] coords = pos.toCharArray();
        return new Point(coords[0] - 'A', coords[1] - 'A');
    }

    private void printAskMove() {
        System.out.print("Enter a move: ");
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
            if (murderCircumstances.contains(items.get(i))) continue;
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
        currentPlayer = 0;
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
            }
            input.nextLine(); // clear scanner buffer
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
