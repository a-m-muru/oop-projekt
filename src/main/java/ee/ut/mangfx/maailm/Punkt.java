package ee.ut.mangfx.maailm;

import ee.ut.mangfx.abi.Koordinaat;

import java.util.HashMap;

/**
 * Alusklass, mis sisaldab positsioonikoordinaate maailma ruudustikus ja kuvatavat sümbolit.
 */
public abstract class Punkt {

    public Maailm maailm;
    protected int xPos;
    protected int yPos;
    protected char symbol = '?';
    protected HashMap<Long, Punkt> viideSalvestuskohale;

    public Punkt(
            Maailm maailm,
            HashMap<Long, Punkt> viideSalvestuskohale,
            int xPos, int yPos,
            char symbol) {
        this.maailm = maailm;
        this.viideSalvestuskohale = viideSalvestuskohale;
        this.xPos = xPos;
        this.yPos = yPos;
        this.symbol = symbol;
    }

    public Punkt(
            Maailm maailm,
            HashMap<Long, Punkt> viideSalvestuskohale,
            int xPos, int yPos) {
        this.maailm = maailm;
        this.viideSalvestuskohale = viideSalvestuskohale;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Punkt(Maailm maailm, HashMap<Long, Punkt> viideSalvestuskohale) {
        this.viideSalvestuskohale = viideSalvestuskohale;
        this.maailm = maailm;
    }

    public char hangiSymbol() {
        return symbol;
    }

    public void seaSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void seaPos(Koordinaat pos) {
        muudaRuuduKohta(false);
        this.xPos = pos.x;
        this.yPos = pos.y;
        muudaRuuduKohta(true);
    }

    public abstract void muudaRuuduKohta(boolean pealeKohamuutust);

    public void muudaPos(Koordinaat vorra) {
        // kasutab "liitmise" konstruktorit
        seaPos(new Koordinaat(hangiKoordinaat(), vorra));
    }

    public Koordinaat hangiKoordinaat() {
        return new Koordinaat(xPos, yPos);
    }
}
