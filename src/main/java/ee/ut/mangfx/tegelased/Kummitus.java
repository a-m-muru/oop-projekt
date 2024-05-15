package ee.ut.mangfx.tegelased;

import ee.ut.mangfx.abi.Koordinaat;
import ee.ut.mangfx.abi.Sonumid;
import ee.ut.mangfx.maailm.Loks;
import ee.ut.mangfx.maailm.Maailm;

public class Kummitus extends Tegelane {
    private int liikumiseOotus = 40;

    public Kummitus(Maailm maailm, int xPos, int yPos) {
        super(maailm, xPos, yPos);
        noclip = true;
        symbol = '☃';
        varv = 0xaaaaaa;
    }

    @Override
    public void teeMidagi() {
        liikumiseOotus--;
        if (liikumiseOotus <= 0) {
            if (liikumiseOotus % 4 == 0) {
                int kuhu = (int) (Math.random() * 3) - 1;
                boolean xVoiY = Math.random() < 0.5;
                Koordinaat suund = new Koordinaat(xVoiY ? kuhu : 0, !xVoiY ? kuhu : 0);
                muudaPos(suund);
            }
            if (liikumiseOotus <= -20)
                liikumiseOotus = (int) (Math.random() * 30) + 30;
        }
    }

    @Override
    protected boolean kasVoibSinnaMinna(Koordinaat kuhu) {
        Tegelane kus = maailm.hangiTegelane(kuhu);
        if (kus instanceof Kummitus)
            return false;
        if (kus != null) {
            kus.muudaElusid(-2);
            Sonumid.lisaSonum("Kummitus ründas tegelast " + kus.hangiSymbol());
            return false;
        }
        Loks loks = maailm.hangiLoks(kuhu);
        if (loks != null) {
            loks.naita();
            return false;
        }
        return true;
    }
}
