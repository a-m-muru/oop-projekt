package tegelased;

import abi.Koordinaat;
import maailm.Maailm;
import maailm.Punkt;

public class Tegelane extends Punkt {
    public int id;

    public Tegelane(Maailm maailm, int xPos, int yPos) {
        super(maailm, xPos, yPos);
        maailm.lisaTegelane(this);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Tegelane(Maailm maailm) {
        super(maailm);
        maailm.lisaTegelane(this);
        this.xPos = 0;
        this.yPos = 0;
    }

    @Override
    public void muudaPos(Koordinaat vorra) {
        //System.out.println("vana: " + hangiKoordinaat());
        //System.out.println("vorra: " + vorra);
        Koordinaat uus = new Koordinaat(hangiKoordinaat(), vorra);
        //System.out.println("uus: " + uus);
        if (maailm.hangiTegelane(uus) != null
                || !maailm.onMootmetes(uus.x, uus.y)
                || maailm.hangiMaastikuKoht(uus.x, uus.y) == '#') {
            return;
        }
        super.muudaPos(vorra);
        //System.out.println("uus pos: " + hangiKoordinaat());
    }
}
