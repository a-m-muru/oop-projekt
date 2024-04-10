package tegelased;

import maailm.Ese;
import maailm.Maailm;
import maailm.Tuba;
import main.Mang;

/**
 * Mängija kontrollitav tegelane
 */
public class Mangija extends Tegelane {

    private int algustoaSuurus = 16;
    private int skoor;


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

    /**
     * Mängija genereerib ise esimese toa
     */
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

    @Override
    public void korjaEse(Ese ese) {
        super.korjaEse(ese);
        skoor++;
        Mang.lisaSonum("Praegune skoor: " + skoor);
    }
}
