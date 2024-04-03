package maailm;

import abi.Koordinaat;
import tegelased.Tegelane;

import java.util.HashMap;

/**
 * Ese on (tavaliselt) liikumatu asi maailma ruudustikus, millega tegelased saavad midagi teha.
 */
public class Ese extends Punkt {

    public Ese(Maailm maailm, int xPos, int yPos) {
        super(maailm, xPos, yPos);
        maailm.lisaEse(this);
        this.xPos = xPos;
        this.yPos = yPos;

    }
    public Ese(Maailm maailm) {
        super(maailm);
        maailm.lisaEse(this);
        this.xPos = 0;
        this.yPos = 0;
    }

    public void kontrolliKasKeegiSeisabPeal(Koordinaat olemas) {
        // vaata maailma tegelaste mapist kas keegi istub sellel koordinaadil
        // kui on siis prindi midagi ja kustuta ese praegu
        Tegelane tegelane = maailm.hangiTegelane(xPos, yPos);
        if (tegelane != null) {
            char esemeSümbol = hangiSymbol();
            System.out.println("Ülesvõetud ese " + esemeSümbol);
            maailm.hangiEsemed().put(hangiKoordinaat(), null);
        }
    }

}
