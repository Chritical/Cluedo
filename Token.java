import java.util.Objects;

public class Token implements Item {

    private String name;

    public Token(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
}
