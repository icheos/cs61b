import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;

public class TestOffByN {

    @Test
    public void TestOffByN() {
        OffByN offBy5 = new OffByN(5);
        boolean a = offBy5.equalChars('a', 'f');
        Assert.assertTrue(a);
    }
}
