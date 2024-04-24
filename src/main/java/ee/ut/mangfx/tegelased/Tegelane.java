package ee.ut.mangfx.tegelased;

import ee.ut.mangfx.abi.Koordinaat;
import ee.ut.mangfx.abi.Sonumid;
import ee.ut.mangfx.maailm.Ese;
import ee.ut.mangfx.maailm.Maailm;
import ee.ut.mangfx.maailm.Punkt;
import ee.ut.mangfx.main.Mang;

import java.util.ArrayList;
import java.util.List;

/**
 * Tegelane on maailmas liikuv asi.
 */
public class Tegelane extends Punkt {
    protected List<Ese> esemed = new ArrayList<>();
    protected int elud = 10;

    public Tegelane(Maailm maailm, int xPos, int yPos) {
        super(maailm, maailm.hangiTegelased(), xPos, yPos);
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
        if (!pealeKohamuutust) {
            maailm.kustutaTegelane(this);
        } else {
            maailm.lisaTegelane(this);
        }
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

    public void korjaEse(Ese ese) {
        esemed.add(ese);
        Sonumid.lisaSonum("Tegelane " + symbol + " korjas üles eseme " + ese.hangiSymbol());
    }

    public int hangiElud() {
        return elud;
    }
}
