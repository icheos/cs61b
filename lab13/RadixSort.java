/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     * @return String[] the sorted array
     */
    final static int R = 256;

    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int max = getMaxStringLength(asciis);
        for (int i = max - 1; i >= 0; i--) {
            sortHelperLSD(asciis, i);
        }
        return asciis;
    }
    private static int getMaxStringLength(String[] arr) {
        int max = 0;
        for (String str : arr) {
            max = Math.max(max, str.length());
        }
        return max;
    }

    private static int getCharAt(String str, int index) {
        return index >= str.length() ? 0 : str.charAt(index);
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     *
     * @param asciis Input array of Strings
     * @param index  The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] Counts = new int[R];
        String[] Aux = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            int digit = getCharAt(asciis[i], index);
            Counts[digit]++;
        }
        for (int i = 1; i <= Counts.length - 1; i++) {
            Counts[i] += Counts[i - 1];
        }
        for (int i = asciis.length - 1; i >= 0; i--) {
            int digit = getCharAt(asciis[i], index);
            Aux[Counts[digit] - 1] = asciis[i];
            Counts[digit]--;
        }
//        for (String s : Aux) {
//            System.out.println(s);
//        }
        System.arraycopy(Aux, 0, asciis, 0, asciis.length);

        return ;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start  int for where to start sorting in this method (includes String at start)
     * @param end    int for where to end sorting in this method (does not include String at end)
     * @param index  the index of the character the method is currently sorting on
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

}
