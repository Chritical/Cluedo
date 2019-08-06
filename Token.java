package GamePackage;

import java.awt.*;
import java.util.Objects;

public class Token implements Item {

    private String name;
    private Point point = new Point(0, 0);

    public Token(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Point getPoint() {
        return point;
    }

    /**
     * sets the location of this token on the board
     * @param x
     * @param y
     */
    public void setPoint(int x, int y) {
        point = new Point(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equals(name, token.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Token{" +
                "name='" + name + '\'' +
                '}';
    }
}
