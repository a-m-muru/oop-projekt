package maailm;

import abi.Koordinaat;

/**
 * Alusklass, mis sisaldab positsioonikoordinaate maailma ruudustikus ja kuvatavat sümbolit.
 */
public class Punkt {

    public Maailm maailm;
    protected int xPos;
    protected int yPos;
    protected char symbol = '?';

    public Punkt(Maailm maailm, int xPos, int yPos, char symbol) {
        this.maailm = maailm;
        this.xPos = xPos;
        this.yPos = yPos;
        this.symbol = symbol;
    }

    public Punkt(Maailm maailm, int xPos, int yPos) {
        this.maailm = maailm;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Punkt(Maailm maailm) {
        this.maailm = maailm;
    }

    public char hangiSymbol() {
        return symbol;
    }

    public void seaSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void seaPos(Koordinaat pos) {
        this.xPos = pos.x;
        this.yPos = pos.y;
    }

    public void muudaPos(Koordinaat vorra) {
        // kasutab "liitmise" konstruktorit
        seaPos(new Koordinaat(hangiKoordinaat(), vorra));
    }

    public Koordinaat hangiKoordinaat() {
        return new Koordinaat(xPos, yPos);
    }
}
