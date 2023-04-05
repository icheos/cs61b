import static org.junit.Assert.*;

import jh61b.junit.In;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void TestArrayDeque() {
        ArrayDequeSolution<Integer> stdArray = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> testArray = new StudentArrayDeque<>();

        String log = "";
        for (int i = 0; i < 1000; i++) {
            if (stdArray.size() == 0) {
                int randNum = StdRandom.uniform(1000);
                int select = StdRandom.uniform(2);
                if (select == 1) {
                    log += "addFirst(" + randNum + ")\n";
                    stdArray.addFirst(randNum);
                    testArray.addFirst(randNum);
                } else {
                    log += "addLast(" + randNum + ")\n";
                    stdArray.addLast(randNum);
                    testArray.addLast(randNum);
                }
            } else {
                int randNum = StdRandom.uniform(1000);
                int select = StdRandom.uniform(4);
                int stdsum = 0;
                int testsum = 0;
                switch (select) {
                    case 1:
                        log += "addFirst(" + randNum + ")\n";
                        stdArray.addFirst(randNum);
                        testArray.addFirst(randNum);
                        break;
                    case 2:
                        log += "addLast(" + randNum + ")\n";
                        stdArray.addLast(randNum);
                        testArray.addLast(randNum);
                        break;
                    case 3:
                        log += "removeFirst()\n";
                        stdsum = stdArray.removeFirst();
                        testsum = testArray.removeFirst();
                        break;
                    case 4:
                        log += "removeLast()\n";
                        stdsum = stdArray.removeFirst();
                        testsum = testArray.removeFirst();

                }
                assertEquals(log, stdsum, testsum);
            }


        }
    }
}
