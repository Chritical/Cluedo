package GamePackage;


/**
 * super class for Game, Room and weapon
 */
public interface Item {
    String getName();
    boolean equals(Object o);
    String toString();
}
