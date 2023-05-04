package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.*;

public class World {
    public static final int LIMIT = 500;
    public static final int MINSIZE = 3;
    public static final int MAXSIZE = 7;
    private static Random RANDOM;
    private TETile[][] world;
    private int seed;
    private Set<Point> rms;

    public World(long seed, int w, int h) {
        RANDOM = new Random(seed);
        rms = new HashSet<>();
        world = new TETile[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }

    }

    public TETile[][] generateRooms() {
        for (int i = 0; i < LIMIT; i++) {
            Point p = new Point(RANDOM.nextInt(Game.WIDTH), RANDOM.nextInt(Game.HEIGHT));
            int width = RANDOM.nextInt(MINSIZE) + MAXSIZE;
            int height = RANDOM.nextInt(MINSIZE) + MAXSIZE;
            if (p.x + width >= Game.WIDTH || p.y + height >= Game.HEIGHT) {
                continue;
            }
            Room room = new Room(p, width, height);
            if (iswrapped(room)) {
                continue;
            }
            rms.add(room.getCenterPoint());
            print(room);
        }
        return world;
    }

    public void print(Room room) {
        HashSet<Point> points = room.setPoint();
        Iterator<Point> iter = points.iterator();
        while (iter.hasNext()) {
            Point p = iter.next();

            Point roompos = room.position();
            int roomw = room.getWidth();
            int roomh = room.getHeight();
            if (roompos.x + roomw - 1 == p.x || roompos.x == p.x ||
                    roompos.y + roomh - 1 == p.y || roompos.y == p.y) {
                world[p.x][p.y] = Tileset.WALL;
            } else {
                world[p.x][p.y] = Tileset.FLOOR;
            }
        }
    }

    public void generateCorridor() {
        List<int[]> larr = new ArrayList<>();
        Set<Point> sortedSet = new HashSet<>();
        for (Point p : rms) {
            larr.add(new int[]{p.x, p.y});
        }
        Collections.sort(larr, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return ints[0] - t1[0];
            }
        });
        for (int[] c : larr) {
            Point p = new Point(c[0], c[1]);
            sortedSet.add(p);
        }
        Stack<Point> stack = new Stack<>();
        for (Point p : sortedSet) {
            stack.push(p);
        }
        while (!stack.isEmpty()) {
            Set<Point> s = new HashSet<>();
            Point pop = stack.pop();
            if (stack.isEmpty()) {
                break;
            }
            Point peek = stack.peek();
            Corridor c = new Corridor(pop, peek);
            s = c.getPoint();
            printCorridor(s);
        }
    }

    public void printCorridor(Set<Point> point) {
        Set<Point> points = point;
        Iterator<Point> iter = points.iterator();
        while (iter.hasNext()) {
            Point p = iter.next();
            world[p.x][p.y] = Tileset.FLOOR;
            TETile up = world[p.x][p.y + 1];
            TETile down = world[p.x][p.y - 1];
            TETile left = world[p.x - 1][p.y];
            TETile right = world[p.x + 1][p.y];
            if (up == Tileset.NOTHING) {
                world[p.x][p.y + 1] = Tileset.WALL;
            }
            if (down == Tileset.NOTHING) {
                world[p.x][p.y - 1] = Tileset.WALL;
            }
            if (left == Tileset.NOTHING) {
                world[p.x - 1][p.y] = Tileset.WALL;
            }
            if (right == Tileset.NOTHING) {
                world[p.x + 1][p.y] = Tileset.WALL;
            }
        }
    }

    public boolean iswrapped(Room room) {
        HashSet<Point> points = room.setPoint();
        Iterator<Point> iter = points.iterator();
        while (iter.hasNext()) {
            Point p = iter.next();
            if (world[p.x][p.y] == Tileset.WALL) {
                return true;
            }
        }
        return false;
    }

    public int isHorizontal(Point p1, Point p2) {
        if (p1.y == p2.y) {
            return 1;
        } else if (p1.x == p2.x) {
            return 2;
        }
        return 0;
    }

    public TETile[][] generateWorld() {
        generateRooms();
        generateCorridor();
        return world;
    }


    public static void main(String[] args) {
        World w = new World(1130, 80, 30);
        TERenderer ter = new TERenderer();
        ter.initialize(80, 30);
        w.generateWorld();
        ter.renderFrame(w.world);
    }

}
