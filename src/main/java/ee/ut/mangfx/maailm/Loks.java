package ee.ut.mangfx.maailm;

import ee.ut.mangfx.abi.Sonumid;
import ee.ut.mangfx.tegelased.Kivi;
import ee.ut.mangfx.tegelased.Mangija;
import ee.ut.mangfx.tegelased.Tegelane;
import ee.ut.mangfx.visuaal.Kuvaja;

import java.util.HashMap;

public class Loks extends Punkt {
    protected int teebHaiget;
    protected boolean teebKivi;
    private boolean kiviTehtud = false;

    public Loks(Maailm maailm, HashMap<Long, Punkt> viideSalvestuskohale, int xPos, int yPos, char symbol, int teebHaiget) {
        super(maailm, viideSalvestuskohale, xPos, yPos, symbol);
        this.teebHaiget = teebHaiget;
        peidetud = true;
        varv = 0x880000;
    }

    public void seaKivi(boolean kivi) {
        this.teebKivi = kivi;
        varv = 0x454400;
    }

    public void teeKivi() {
        if (kiviTehtud)
            return;
        Sonumid.lisaSonum("Lõksust paiskub välja kivimürakas!");
        kiviTehtud = true;
        Kivi kivi = new Kivi(this.maailm,
                this.xPos + (Math.random() < 0.5 ? -1 : 1),
                this.yPos + (Math.random() < 0.5 ? -1 : 1));
        maailm.lisaKivi(kivi);
    }

    @Override
    public void muudaRuuduKohta(boolean pealeKohamuutust) {

    }

    public void astusPeale(Tegelane tegelane) {

        naita();
        if (teebKivi) {
            teeKivi();
            return;
        }
        if (tegelane instanceof Mangija) {
            Sonumid.lisaSonum(  "Jooksid lõksu otsa!");
        }
        tegelane.muudaElusid(-Math.abs(teebHaiget));
    }
}
