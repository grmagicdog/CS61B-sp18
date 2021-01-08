import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.

    // Your tests go here.
    @Test
    public void testEqualChars() {
        CharacterComparator offByOne = new OffByN(1);
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('r', 'q'));
        assertTrue(offByOne.equalChars('%', '&'));
        assertFalse(offByOne.equalChars('a', 'e'));
        assertFalse(offByOne.equalChars('z', 'a'));
        assertFalse(offByOne.equalChars('a', 'a'));

        CharacterComparator offByFive = new OffByN(5);
        assertTrue(offByFive.equalChars('a', 'f'));
        assertTrue(offByFive.equalChars('r', 'w'));
        assertFalse(offByFive.equalChars('%', '&'));
        assertFalse(offByFive.equalChars('a', 'b'));
        assertFalse(offByFive.equalChars('r', 'q'));
        assertFalse(offByFive.equalChars('a', 'a'));
    }
}
