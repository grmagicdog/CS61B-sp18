import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void randomTestAddRemove() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        String message = "";

        for (Integer i = 0; i < 1000; i++) {
            int random;
            if (ads.size() > 0) {
                random = StdRandom.uniform(4);
            } else {
                random = StdRandom.uniform(2);
            }
            if (random == 3) {
                message += "\nremoveFirst()";
                assertEquals(message, ads.removeFirst(), sad.removeFirst());
            } else if (random == 2) {
                message += "\nremoveLast()";
                assertEquals(message, ads.removeLast(), sad.removeLast());
            } else if (random == 1) {
                message += String.format("\naddFirst(%d)", i);
                sad.addFirst(i);
                ads.addFirst(i);
                message += "\nsize()";
                assertEquals(message, ads.size(), sad.size());
            } else {
                message += String.format("\naddLast(%d)", i);
                sad.addLast(i);
                ads.addLast(i);
                message += "\nsize()";
                assertEquals(message, ads.size(), sad.size());
            }
        }
    }
}
