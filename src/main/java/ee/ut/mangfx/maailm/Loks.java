package ee.ut.mangfx.maailm;

import ee.ut.mangfx.abi.Sonumid;
import ee.ut.mangfx.tegelased.Mangija;
import ee.ut.mangfx.tegelased.Tegelane;
import ee.ut.mangfx.visuaal.Kuvaja;

import java.util.HashMap;

public class Loks extends Punkt {
    protected int teebHaiget;
    public Loks(Maailm maailm, HashMap<Long, Punkt> viideSalvestuskohale, int xPos, int yPos, char symbol, int teebHaiget) {
        super(maailm, viideSalvestuskohale, xPos, yPos, symbol);
        this.teebHaiget = teebHaiget;
        peidetud = true;
        varv = 0x880000;
    }



    @Override
    public void muudaRuuduKohta(boolean pealeKohamuutust) {

    }

    public void astusPeale(Tegelane tegelane) {
        tegelane.muudaElusid(-Math.abs(teebHaiget));
        if (tegelane instanceof Mangija)
            Sonumid.lisaSonum("Jooksid lõksu otsa!");
        naita();
    }
}
