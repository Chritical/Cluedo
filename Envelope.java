package GamePackage;

import java.util.Objects;

public class Envelope {

    private Room room;
    private Token token;
    private Weapon weapon;

    public Envelope(Room room, Token token, Weapon weapon) {
        this.room = room;
        this.token = token;
        this.weapon = weapon;
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
