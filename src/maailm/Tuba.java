package maailm;

import abi.Koordinaat;

/**
 * Üks tuba mängu maailma ruudustikus
 */
public class Tuba {
    private Maailm maailm;
    private int xAlgus;
    private int yAlgus;
    private int xSuurus;
    private int ySuurus;
    private Koordinaat uks;

    public Tuba(Maailm maailm, int xAlgus, int yAlgus, int xSuurus, int ySuurus) {
        this.maailm = maailm;
        this.xSuurus = xSuurus;
        this.ySuurus = ySuurus;
        this.xAlgus = xAlgus;
        this.yAlgus = yAlgus;
        this.uks = new Koordinaat((Math.random() < 0.5 ? xAlgus : xAlgus + xSuurus - 1), (int) (Math.random() * (ySuurus - 1) + yAlgus + 1));
    }

    public Tuba(Maailm maailm, int xAlgus, int yAlgus, int xSuurus, int ySuurus, Koordinaat uks) {
        this.maailm = maailm;
        this.xSuurus = xSuurus;
        this.ySuurus = ySuurus;
        this.xAlgus = xAlgus;
        this.yAlgus = yAlgus;
        this.uks = uks;
    }

    /**
     * Genereerib toa vastavalt isendiväljadele
     */
    public void genereeriTuba() {
        System.out.println(xAlgus + " " + xSuurus + " " + (xAlgus + xSuurus));
        System.out.println(yAlgus + " " + ySuurus + " " + (yAlgus + ySuurus));
        System.out.println(uks);
        int loppY = Math.min(yAlgus + ySuurus, maailm.hangiSuurusY());
        int loppX = Math.min(xAlgus + xSuurus, maailm.hangiSuurusX());
        for (int i = yAlgus; i < loppY; i++) {
            if (i == yAlgus || i == loppY - 1) {
                System.out.println(i + " " + yAlgus);
                for (int j = xAlgus; j < loppX - 1; j++) {
                    maailm.seaMaastikuKoht(j, i, '#');
                }
            }
            maailm.seaMaastikuKoht(xAlgus, i, '#');
            maailm.seaMaastikuKoht(loppX - 1, i, '#');
        }
        if (uks != null)
            maailm.seaMaastikuKoht(uks.x, uks.y, ' ');
    }
}
