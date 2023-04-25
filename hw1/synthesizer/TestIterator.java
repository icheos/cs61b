package synthesizer;

import org.junit.Test;

import java.util.Iterator;

public class TestIterator {
    @Test
    public void TestIter() {
        ArrayRingBuffer<Integer> ar = new ArrayRingBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            ar.enqueue(i);
        }
//        Iterator<Integer> iter = ar.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next());
//        }
        for (int i : ar) {
            System.out.println(i);
        }
    }

}
