package tegelased;

import abi.Koordinaat;
import maailm.Maailm;
import maailm.Punkt;

/**
 * Tegelane on maailmas liikuv asi.
 */
public class Tegelane extends Punkt {
    public int id;

    public Tegelane(Maailm maailm, int xPos, int yPos) {
        super(maailm, maailm.hangiTegelased(), xPos, yPos);
        maailm.lisaTegelane(this);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Tegelane(Maailm maailm) {
        super(maailm, maailm.hangiTegelased());
        maailm.lisaTegelane(this);
        this.xPos = 0;
        this.yPos = 0;
    }

    @Override
    public void muudaRuuduKohta(boolean pealeKohamuutust) {
        if (!pealeKohamuutust)
            viideSalvestuskohale.put(hangiKoordinaat(), null);
        else
            viideSalvestuskohale.put(hangiKoordinaat(), this);
    }

    @Override
    public void muudaPos(Koordinaat vorra) {
        //System.out.println("vana: " + hangiKoordinaat());
        //System.out.println("vorra: " + vorra);
        Koordinaat uus = new Koordinaat(hangiKoordinaat(), vorra);
        //System.out.println("uus: " + uus);
        if (maailm.hangiTegelane(uus) != null
                || !maailm.onMootmetes(uus.x, uus.y)
                || maailm.hangiMaastikuKoht(uus.x, uus.y) == '#') {
            return;
        }
        super.muudaPos(vorra);
        //System.out.println("uus pos: " + hangiKoordinaat());
    }
}
