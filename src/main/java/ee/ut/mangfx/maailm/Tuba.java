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

    public void binaaarneRuumipoolitamine() {
        uksed.addAll(binaarneRuumipoolitamine(Math.random() < 0.5));
        for (Koordinaat uks : uksed)
            if (uks != null)
                maailm.seaMaastikuKoht(uks.x, uks.y, '_');
    }

    private List<Koordinaat> binaarneRuumipoolitamine(boolean viimaneOliVertikaalne) {
        if (xSuurus < 15 || ySuurus < 15)
            return uksed;
        if (viimaneOliVertikaalne) {
            int poolituskoht = (int) (Math.random() * xSuurus - 4) + 2;
            Tuba tuba = new Tuba(maailm, xAlgus, yAlgus, poolituskoht + 1, ySuurus);
            Tuba tuba2 = new Tuba(maailm, xAlgus + poolituskoht, yAlgus, xSuurus - poolituskoht, ySuurus);
            uksed.addAll(tuba.binaarneRuumipoolitamine(false));
            uksed.addAll(tuba2.binaarneRuumipoolitamine(false));
            tuba.genereeriTuba();
            tuba2.genereeriTuba();
        } else {
            int poolituskoht = (int) (Math.random() * ySuurus - 4) + 2;
            Tuba tuba = new Tuba(maailm, xAlgus, yAlgus, xSuurus, poolituskoht + 1);
            Tuba tuba2 = new Tuba(maailm, xAlgus, yAlgus + poolituskoht, xSuurus, ySuurus - poolituskoht);
            uksed.addAll(tuba.binaarneRuumipoolitamine(true));
            uksed.addAll(tuba2.binaarneRuumipoolitamine(true));
            tuba.genereeriTuba();
            tuba2.genereeriTuba();
        }
        return uksed;
    }
}
