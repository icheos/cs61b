package synthesizer;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void Testfull() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        arb.enqueue(10);
        arb.dequeue();
        arb.enqueue(30);
        arb.enqueue(30);
        arb.enqueue(30);
        arb.enqueue(30);
        arb.enqueue(30);
    }
    @Test
    public void TestEmpty() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        arb.dequeue();
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
