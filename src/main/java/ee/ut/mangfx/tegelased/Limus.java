package ee.ut.mangfx.tegelased;

import ee.ut.mangfx.abi.Koordinaat;
import ee.ut.mangfx.abi.Sonumid;
import ee.ut.mangfx.maailm.Maailm;

public class Limus extends Tegelane {
    public Limus(Maailm maailm, int xPos, int yPos) {
        super(maailm, xPos, yPos);
        seaSymbol('o');
    }

    @Override
    public void teeMidagi() {
        if (Math.random() < 0.75)
            return;
        int kuhu = (int) (Math.random() * 3) - 1;
        boolean xVoiY = Math.random() < 0.5;
        Koordinaat suund = new Koordinaat(xVoiY ? kuhu : 0, !xVoiY ? kuhu : 0);
        muudaPos(suund);
    }

    @Override
    protected boolean kasVoibSinnaMinna(Koordinaat kuhu) {
        Tegelane tegelane = maailm.hangiTegelane(kuhu);
        if (tegelane != null && !(tegelane instanceof Limus)) {
            tegelane.muudaElusid(-1);
            Sonumid.lisaSonum("Limus ründas tegelast " + tegelane.hangiSymbol());
        }
        return super.kasVoibSinnaMinna(kuhu);
    }
}
