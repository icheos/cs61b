package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
public class Room {
    private Point point;
    private Point centerPoint;
    private int width;
    private int height;

    public Room( Point p, int w, int h) {
        width = w;
        height = h;
        point = p;
        centerPoint = getCenterPoint();
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Point position() {
        return point;
    }
    public Point getCenterPoint() {
        int dx = point.x;
        int dy = point.y;
        return new Point(dx + width / 2, dy + height / 2);
    }
    public HashSet<Point> setPoint() {
        HashSet<Point> points = new HashSet<>();
        for (int i = 0; i < width; i++)  {
            for (int j = 0; j < height; j++) {
                points.add(new Point(point.x + i,point.y + j));
            }
        }
        return points;
    }
}
