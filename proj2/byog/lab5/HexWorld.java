package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public static void addHexagon(int length) {
        int longest = length + 2 * (length - 1);
        int shorter = length;
        int longer = length;
        halfhexagon1(length, longer, shorter);
        int[] a = longhsort(length);
        shorter = a[0];
        longer = a[1];
        halfhexagon2(length, longer, shorter);

    }
    private static int[] longhsort(int length) {
        int[] a = new int[2];
        a[1] = length + (length-1) * 2;
        length -= 1;
        a[0] = 1;
        return a;
    }

    private static void halfhexagon1(int length, int longer, int shorter) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < shorter; j++) {
                System.out.printf(" ");
            }
            shorter -= 1;
            for (int j = 0; j < longer; j++) {
                System.out.printf("a");
            }
            longer += 2;
            System.out.println();
        }
        shorter += 1;
        longer -= 2;
    }
    private static void halfhexagon2(int length, int longer, int shorter) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < shorter; j++) {
                System.out.printf(" ");
            }
            shorter += 1;
            for (int j = 0; j < longer; j++) {
                System.out.printf("a");
            }
            longer -= 2;
            System.out.println();
        }
    }


    public static void main(String[] args) {
       addHexagon(3);
    }
}
