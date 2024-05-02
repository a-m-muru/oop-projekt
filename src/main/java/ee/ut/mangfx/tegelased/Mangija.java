package ee.ut.mangfx.tegelased;

import ee.ut.mangfx.abi.Koordinaat;
import ee.ut.mangfx.abi.Sonumid;
import ee.ut.mangfx.maailm.Ese;
import ee.ut.mangfx.maailm.Maailm;
import ee.ut.mangfx.maailm.Tuba;

/**
 * Mängija kontrollitav tegelane
 */
public class Mangija extends Tegelane {

    private int algustoaSuurus = 400;
    private int skoor;


    public Mangija(Maailm maailm, int xPos, int yPos) {
        super(maailm, xPos, yPos);
        looAlgusTuba();
        this.symbol = '@';
        noclip = false;
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
        tuba.genereeriTuba();
        tuba.binaaarneRuumipoolitamine(32);
    }

    @Override
    public void korjaEse(Ese ese) {
        super.korjaEse(ese);
        lisaSkoori(1);
    }

    @Override
    protected boolean kasVoibSinnaMinna(Koordinaat kuhu) {
        Tegelane tegelane = maailm.hangiTegelane(kuhu);
        if (tegelane != null && tegelane.getClass() != this.getClass()) {
            tegelane.muudaElusid(-1);
            Sonumid.lisaSonum("Mängija ründas tegelast " + tegelane.hangiSymbol());
            if (tegelane.hangiElud() <= 0) {
                lisaSkoori(5);
            }
        }
        return super.kasVoibSinnaMinna(kuhu);
    }

    public int hangiSkoor() {
        return skoor;
    }

    public void lisaSkoori(int kuiPalju) {
        skoor += kuiPalju;
        Sonumid.lisaSonum("Uus skoor on " + skoor);
    }
}
