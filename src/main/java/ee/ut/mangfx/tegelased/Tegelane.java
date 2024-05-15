package ee.ut.mangfx.tegelased;

import ee.ut.mangfx.abi.Koordinaat;
import ee.ut.mangfx.abi.Sonumid;
import ee.ut.mangfx.maailm.*;

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
        elud = Math.max(elud, 0);
        if (elud == 0) {
            maailm.kustutaTegelane(this);
        }
    }

    @Override
    public void muudaRuuduKohta(boolean pealeKohamuutust) {
        if (!pealeKohamuutust) {
            maailm.kustutaTegelane(this);
        } else {
            maailm.lisaTegelane(this);
            Ese ese = maailm.hangiEse(xPos, yPos);
            if (ese != null) {
                korjaEse(ese);
                maailm.kustutaEse(ese);
            }
            Loks loks = maailm.hangiLoks(xPos, yPos);
            if (loks != null) {

                loks.astusPeale(this);
            }
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
        if (ese instanceof Syda) {
            muudaElusid(3);
            esemed.remove(ese);
        }
    }

    public void eemaldaEse(Ese ese) {
        esemed.remove(ese);
    }

    public void varastaEsemed(Tegelane kellelt) {
        for (Ese ese : kellelt.hangiEsemed()) {
            korjaEse(ese);
            kellelt.eemaldaEse(ese);
        }
    }

    public int korjatudEsemeteArv() {
        return esemed.size();
    }

    public List<Ese> hangiEsemed() {
        return esemed;
    }

    public int hangiElud() {
        return elud;
    }

    public void teeMidagi() {

    }

    public String esemeteNimekiri() {
        StringBuilder sb = new StringBuilder();
        for (Ese ese : esemed) {
            sb.append(ese.hangiSymbol());
            sb.append('\n');
        }
        return sb.toString();
    }
}
