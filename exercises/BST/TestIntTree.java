import org.junit.Test;
import static org.junit.Assert.*;

public class TestIntTree {
    @Test
    public void testMergeRight() {
        IntTree l = new IntTree(14, null, null);
        IntTree r = new IntTree(27, null, null);
        r = new IntTree(16, l, r);
        l = new IntTree(8, null, null);
        l = new IntTree(10, l, null);
        l = new IntTree(3, null, l);
        IntTree T = new IntTree(12, l, r);
        T = new IntTree(42, T, null);
        r = new IntTree(26, null, null);
        r = new IntTree(11, null, r);
        r = new IntTree(1, null, r);
        IntTree L = new IntTree(42, r, null);
        IntTree TL = IntTree.mergeRight(T, L);
    }
}
