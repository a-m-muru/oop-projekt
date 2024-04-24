package ee.ut.mangfx.tegelased;

import ee.ut.mangfx.abi.Koordinaat;
import ee.ut.mangfx.abi.Sonumid;
import ee.ut.mangfx.maailm.Ese;
import ee.ut.mangfx.maailm.Maailm;
import ee.ut.mangfx.maailm.Punkt;

import java.util.ArrayList;
import java.util.List;

/**
 * Tegelane on maailmas liikuv asi.
 */
public class Tegelane extends Punkt {
    protected List<Ese> esemed = new ArrayList<>();
    protected int elud = 10;
    protected boolean noclip;

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

    public void muudaElusid(int vorra) {
        elud += vorra;
        if (elud <= 0) {
            Sonumid.lisaSonum("Tegelane " + hangiSymbol() + " suri ära");
            maailm.kustutaTegelane(this);
        }
    }

    @Override
    public void muudaRuuduKohta(boolean pealeKohamuutust) {
        if (!pealeKohamuutust) {
            maailm.kustutaTegelane(this);
        } else {
            maailm.lisaTegelane(this);
        }
    }

    protected boolean kasVoibSinnaMinna(Koordinaat kuhu) {
        if (maailm.hangiTegelane(kuhu.x, kuhu.y) != null)
            return false;
        return noclip || (maailm.onMootmetes(kuhu.x, kuhu.y)
                && maailm.hangiMaastikuKoht(kuhu.x, kuhu.y) != '#');
    }

    @Override
    public void muudaPos(Koordinaat vorra) {
        //System.out.println("vana: " + hangiKoordinaat());
        //System.out.println("vorra: " + vorra);
        Koordinaat uus = new Koordinaat(hangiKoordinaat(), vorra);
        // kokkupõrked seintega
        if (!kasVoibSinnaMinna(uus))
            return;
        super.muudaPos(vorra);
        //System.out.println("uus pos: " + hangiKoordinaat());
    }

    public void korjaEse(Ese ese) {
        esemed.add(ese);
        Sonumid.lisaSonum("Tegelane " + symbol + " korjas üles eseme " + ese.hangiSymbol());
    }

    public int korjatudEsemeteArv() {
        return esemed.toArray().length;
    }

    public List<Ese> hangiEsemed() {
        return esemed;
    }

    public int hangiElud() {
        return elud;
    }

    public void teeMidagi() {

    }
}
