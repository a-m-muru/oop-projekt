package ee.ut.mangfx.tegelased;

import ee.ut.mangfx.abi.Koordinaat;
import ee.ut.mangfx.abi.Sonumid;
import ee.ut.mangfx.maailm.Maailm;

public class Kivi extends Tegelane {
    private double kiirus = 9;
    private int ootus = 1;
    private Koordinaat suund;
    public Kivi(Maailm maailm, int xPos, int yPos) {
        super(maailm, xPos, yPos);
        this.varv = 0x454400;
        this.symbol = 'O';
        this.suund = new Koordinaat(
                Math.random() < 0.5 ? -1 : 1,
                Math.random() < 0.5 ? -1 : 1
        );
        this.elud = 100;
    }

    @Override
    public void teeMidagi() {
        kiirus -= 0.2 * Math.random();
        if (--ootus < kiirus) {
            ootus = 7;
            Koordinaat kuhuX = new Koordinaat(hangiKoordinaat(), new Koordinaat(suund.x, 0));
            Koordinaat kuhuY = new Koordinaat(hangiKoordinaat(), new Koordinaat(0, suund.y));
            if (!kasVoibSinnaMinna(kuhuX)) {
                suund.x *= -1;
                kiirus -= 1.0 / kiirus;
            }
            if (!kasVoibSinnaMinna(kuhuY)) {
                suund.y *= -1;
                kiirus -= 1.0 / kiirus;
            }
            if (!kasVoibSinnaMinna(new Koordinaat(hangiKoordinaat(), suund))) {
                suund.x *= -1;
                suund.y *= -1;
                kiirus -= 1.0 / kiirus;
            }
            muudaPos(suund);
        }
    }

    @Override
    protected boolean kasVoibSinnaMinna(Koordinaat kuhu) {
        boolean kas = super.kasVoibSinnaMinna(kuhu);
        Tegelane tegelane = maailm.hangiTegelane(kuhu);
        if (tegelane != null && !(tegelane instanceof Kivi)) {
            Sonumid.lisaSonum("Kivi lömastas tegelase " + tegelane.hangiSymbol());
            tegelane.muudaElusid(-1000);
            return true;
        } else if (tegelane != null) {
            kiirus = kiirus + ((Kivi) tegelane).kiirus;
            kiirus /= 2.0;
        }
        return kas;
    }
}
