package ee.ut.mangfx.tegelased;

import ee.ut.mangfx.abi.Sonumid;
import ee.ut.mangfx.maailm.Ese;
import ee.ut.mangfx.maailm.Maailm;
import ee.ut.mangfx.maailm.Tuba;
import ee.ut.mangfx.main.Mang;

/**
 * Mängija kontrollitav tegelane
 */
public class Mangija extends Tegelane {

    private int algustoaSuurus = 128;
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
                xPos - 30,
                yPos - 30,
                algustoaSuurus,
                algustoaSuurus
                );
        tuba.binaaarneRuumipoolitamine();
        tuba.genereeriTuba();
    }

    @Override
    public void korjaEse(Ese ese) {
        super.korjaEse(ese);
        skoor++;
        Sonumid.lisaSonum("Uus skoor on " + skoor);
    }
}
