public class OffByN implements CharacterComparator {

    private int postion;

    public OffByN(int N) {
        postion = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == postion;
    }
}