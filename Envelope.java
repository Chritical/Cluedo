import java.util.Objects;


/**
 * Stores a <code>Room</code>, character <code>Token</code>, and
 * <code>Weapon</code>.
 *
 * This simulates the envelope in a real game of Cleudo that stores the murder
 * circumstances. It is used for more purposes, however, as it is useful for
 * suggestions and accusations also.
 */
public class Envelope {

    private Room room;
    private Token token;
    private Weapon weapon;

    /**
     * Creates and <code>Envelope</code> that stores three <code>Item</code>s.
     *
     * @param room
     * @param token
     * @param weapon
     */
    public Envelope(Room room, Token token, Weapon weapon) {
        this.room = room;
        this.token = token;
        this.weapon = weapon;
    }

    public boolean contains(Item item) {
        return room.equals(item) || token.equals(item) || weapon.equals(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Envelope envelope = (Envelope) o;
        return Objects.equals(token, envelope.token) &&
                Objects.equals(room, envelope.room) &&
                Objects.equals(weapon, envelope.weapon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, room, weapon);
    }
}
