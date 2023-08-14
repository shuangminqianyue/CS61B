public class OffByN implements CharacterComparator {
    public int n;

    /** OffByN constructor. */
    public OffByN(int N) {
        this.n = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == n;
    }
}
