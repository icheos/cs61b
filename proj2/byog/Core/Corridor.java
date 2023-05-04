package byog.Core;

import com.sun.source.doctree.SeeTree;
import jh61b.junit.In;

import java.nio.channels.Pipe;
import java.util.*;

public class Corridor {
    private Point p1;

    private Point p2;


    public Corridor(Point point1, Point point2) {
        p1 = new Point(point1.x, point1.y);
        p2 = new Point(point2.x, point2.y);
    }

    public Set<Point> getPoint() {
        int disy = p2.y - p1.y;
        Set<Point> s = new HashSet<>();
        // p2 below or above p1
        for (int i = p1.x; i <= p2.x; i++) {
            Point p = new Point(i, p1.y);
            s.add(p);

        }
        if (disy < 0) {
            for (int i = p2.y; i <= p1.y; i++) {
                Point p = new Point(p1.x, i);
                s.add(p);

            }
        } else if (disy > 0) {
            for (int i = p1.y; i <= p2.y; i++) {
                Point p = new Point(p2.x, i);
                s.add(p);
            }
        }
        return s;
    }

}