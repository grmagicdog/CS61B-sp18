/** The equalChars(char x, char y) method of OffByN objects
 *  will say that x and y are equal if they are off by N from each other. */
public class OffByN implements CharacterComparator {
    private int diff;

    public OffByN(int n) {
        diff = n;
    }
    /** Returns true if characters are equal by the rules of the implementing class. */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == diff;
    }
}
