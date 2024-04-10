package ee.ut.mangfx.abi;

/**
 * Salvestab asukohta maailma ruudustikus
 */
public class Koordinaat {
    public int x;
    public int y;

    public Koordinaat(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Koordinaat(Koordinaat a, Koordinaat b) {
        this.x = a.x + b.x;
        this.y = a.y + b.y;
    }

    @Override
    public String toString() {
        return "Koordinaat{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
