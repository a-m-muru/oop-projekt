package ee.ut.mangfx.maailm;

import ee.ut.mangfx.abi.Koordinaat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Üks tuba mängu maailma ruudustikus
 */
public class Tuba {
    private Maailm maailm;
    private int xAlgus;
    private int yAlgus;
    private int xSuurus;
    private int ySuurus;
    private List<Koordinaat> uksed = new ArrayList<>();
    private char porand = ' ';

    public Tuba(Maailm maailm, int xAlgus, int yAlgus, int xSuurus, int ySuurus) {
        this.maailm = maailm;
        this.xSuurus = xSuurus;
        this.ySuurus = ySuurus;
        this.xAlgus = xAlgus;
        this.yAlgus = yAlgus;
        int usteArv = (int) (Math.random() * 2) + 1;
        for (int i = 0; i < usteArv; i++) {
            uksed.add(null);
        }
        genereeriUksed(uksed);
        porand = "`'´,".charAt((int) (Math.random() * 4));
    }

    /**
     * Genereerib toa seinad vastavalt isendiväljadele
     */
    public void genereeriTuba() {
        int loppY = Math.min(yAlgus + ySuurus, maailm.hangiSuurusY());
        int loppX = Math.min(xAlgus + xSuurus, maailm.hangiSuurusX());
        for (int i = yAlgus; i < loppY; i++) {
            if (i == yAlgus || i == loppY - 1) {
                for (int j = xAlgus; j < loppX - 1; j++) {
                    maailm.seaMaastikuKoht(j, i, '#');
                }
            }
            maailm.seaMaastikuKoht(xAlgus, i, '#');
            maailm.seaMaastikuKoht(loppX - 1, i, '#');
        }
        // põrand
        if (Math.random() < 0.05)
            for (int i = yAlgus + 1; i < loppY - 1; i++) {
                for (int j = xAlgus + 1; j < loppX - 1; j++) {
                    if (maailm.hangiMaastikuKoht(j, i) != '#')
                        maailm.seaMaastikuKoht(j, i, porand);
                }
            }
        // ese
        Koordinaat esemeAsukoht = new Koordinaat(
                (int) (Math.random() * xSuurus) + xAlgus,
                (int) (Math.random() * ySuurus) + yAlgus
        );
        Ese ese = new Ese(maailm, esemeAsukoht.x, esemeAsukoht.y);
        ese.seaSymbol('$');
        ese.seaVarv(0x008800);
        if (Math.random() < 0.1) {
            ese.seaSymbol('€');
            ese.seaVarv(0x004488);
        }
        if (Math.random() < 0.1) {
            ese.seaSymbol('£');
            ese.seaVarv(0x882200);
        }
        if (Math.random() < 0.2) {
            esemeAsukoht = new Koordinaat(
                    (int) (Math.random() * xSuurus) + xAlgus,
                    (int) (Math.random() * ySuurus) + yAlgus);
            Syda syda = new Syda(maailm, esemeAsukoht.x, esemeAsukoht.y);
        }
        // lõksud
        for (int i = 0; i < (int) (Math.random() * 50) - 3; i++) {
            Koordinaat loksuAsukoht = new Koordinaat((int) (Math.random() * xSuurus) + xAlgus,
                    (int) (Math.random() * ySuurus) + yAlgus);
            Loks loks = new Loks(
                    maailm, maailm.hangiLoksud(),
                    loksuAsukoht.x, loksuAsukoht.y,
                    '^',
                    (int) (Math.random() * 5) + 1);
            maailm.lisaLoks(loks);
        }
    }

    private void genereeriUksed(List<Koordinaat> uksed) {
        for (int i = 0; i < uksed.size(); i++) {
            if (uksed.get(i) != null)
                continue;
            Koordinaat uks = null;
            // üleval-all
            if (Math.random() < 0.5) {
                uks = new Koordinaat(
                        (int) (Math.random() * xSuurus - 2) + 1 + xAlgus,
                        Math.random() < 0.5 ? yAlgus : yAlgus + ySuurus - 1
                );
            } // paremal-vasakul
            else {
                uks = new Koordinaat(
                        Math.random() < 0.5 ? xAlgus : xAlgus + xSuurus - 1,
                        (int) (Math.random() * ySuurus - 2) + 1 + yAlgus
                );
            }
            uksed.set(i, uks);
        }
    }

    public void binaaarneRuumipoolitamine(int sygavusLimiit) {
        uksed.addAll(binaarneRuumipoolitamine(Math.random() < 0.5, 0, sygavusLimiit));
        for (Koordinaat uks : uksed)
            if (uks != null)
                maailm.seaMaastikuKoht(uks.x, uks.y, '_');
    }

    private List<Koordinaat> binaarneRuumipoolitamine(boolean viimaneOliVertikaalne, int sygavus, int sygavLimiit) {
        if (xSuurus <= 16 || ySuurus <= 16 || sygavus >= sygavLimiit)
            return uksed;
        int poolituskoht = 0;
        if (viimaneOliVertikaalne) {
            poolituskoht = (int) (Math.random() * (xSuurus - 8)) + 4;
            Tuba tuba = new Tuba(maailm, xAlgus, yAlgus, poolituskoht + 1, ySuurus);
            Tuba tuba2 = new Tuba(maailm, xAlgus + poolituskoht, yAlgus, xSuurus - poolituskoht, ySuurus);
            tuba.genereeriTuba();
            tuba2.genereeriTuba();
            uksed.addAll(tuba.binaarneRuumipoolitamine(false, sygavus + 1, sygavLimiit));
            uksed.addAll(tuba2.binaarneRuumipoolitamine(false, sygavus + 1, sygavLimiit));
        } else {
            poolituskoht = (int) (Math.random() * (ySuurus - 8)) + 4;
            Tuba tuba = new Tuba(maailm, xAlgus, yAlgus, xSuurus, poolituskoht + 1);
            Tuba tuba2 = new Tuba(maailm, xAlgus, yAlgus + poolituskoht, xSuurus, ySuurus - poolituskoht);
            tuba.genereeriTuba();
            tuba2.genereeriTuba();
            uksed.addAll(tuba.binaarneRuumipoolitamine(true, sygavus + 1, sygavLimiit));
            uksed.addAll(tuba2.binaarneRuumipoolitamine(true, sygavus + 1, sygavLimiit));
        }
        return uksed;
    }
}
