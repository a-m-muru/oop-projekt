package ee.ut.mangfx.tegelased;

import ee.ut.mangfx.abi.Koordinaat;
import ee.ut.mangfx.abi.Sonumid;
import ee.ut.mangfx.maailm.Loks;
import ee.ut.mangfx.maailm.Maailm;

public class Luukere extends Tegelane {
    private int liikumiseOotus = 40;
    public Luukere(Maailm maailm, int xPos, int yPos) {
        super(maailm, xPos, yPos);
        symbol = '☠';
    }

    @Override
    public void teeMidagi() {
        liikumiseOotus--;
        if (liikumiseOotus <= 0) {
            if (liikumiseOotus % 2 == 0)
                for (int i = 0; i < 4; i++) {
                    int kuhu = (int) (Math.random() * 3) - 1;
                    boolean xVoiY = Math.random() < 0.5;
                    Koordinaat suund = new Koordinaat(xVoiY ? kuhu : 0, !xVoiY ? kuhu : 0);
                    muudaPos(suund);
                }
            if (liikumiseOotus <= -20)
                liikumiseOotus = (int)(Math.random() * 30) + 30;
        }
    }

    @Override
    protected boolean kasVoibSinnaMinna(Koordinaat kuhu) {
        Tegelane kus = maailm.hangiTegelane(kuhu);
        if (kus instanceof Luukere)
            return false;
        if (kus != null) {
            kus.muudaElusid(-2);
            Sonumid.lisaSonum("Luukere ründas tegelast " + kus.hangiSymbol());
            return false;
        }
        Loks loks = maailm.hangiLoks(kuhu);
        if (loks != null)
            return false;
        return super.kasVoibSinnaMinna(kuhu);
    }
}
