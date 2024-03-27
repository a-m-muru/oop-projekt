package tegelased;

import maailm.Maailm;
import maailm.Tuba;

public class Mangija extends Tegelane {

    private int algustoaSuurus = 16;


    public Mangija(Maailm maailm, int xPos, int yPos) {
        super(maailm, xPos, yPos);
        looAlgusTuba();
        this.symbol = '@';
    }

    public Mangija(Maailm maailm) {
        super(maailm);
        this.xPos = 0;
        this.yPos = 0;
        looAlgusTuba();
        this.symbol = '@';
    }

    public void looAlgusTuba() {
        Tuba tuba = new Tuba(
                this.maailm,
                xPos - algustoaSuurus / 2,
                yPos - algustoaSuurus / 2,
                algustoaSuurus,
                algustoaSuurus
                );
        tuba.genereeriTuba();
    }
}
