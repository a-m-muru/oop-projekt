package maailm;

public class Tuba {
    private Maailm maailm;
    private int xAlgus;
    private int yAlgus;
    private int xSuurus;
    private int ySuurus;

    public Tuba(Maailm maailm, int xAlgus, int yAlgus, int xSuurus, int ySuurus) {
        this.maailm = maailm;
        this.xSuurus = xSuurus;
        this.ySuurus = ySuurus;
        this.xAlgus = xAlgus;
        this.yAlgus = yAlgus;
    }

    public void genereeriTuba() {
        int loppY = Math.min(yAlgus + ySuurus, maailm.hangiSuurusY());
        int loppX = Math.min(xAlgus + xSuurus, maailm.hangiSuurusX());
        for (int i = yAlgus; i < loppY; i++) {
            if (i == yAlgus || i >= loppY - 1)
                for (int j = 1; j < loppX - 1; j++) {
                    maailm.seaMaastikuKoht(j, i, '#');
                }
            maailm.seaMaastikuKoht(0, i, '#');
            maailm.seaMaastikuKoht(loppX - 1, i, '#');
        }
    }
}
