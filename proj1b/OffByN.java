public class OffByN implements CharacterComparator{

    public int postion;
    public OffByN(int N) {
        postion = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) == postion) {
            return true;
        }
        return false;
    }
}