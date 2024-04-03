package maailm;

import abi.Koordinaat;
import tegelased.Tegelane;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Ese on (tavaliselt) liikumatu asi maailma ruudustikus, millega tegelased saavad midagi teha.
 */
public class Ese extends Punkt {

    public int saadudPunktid = 0;
    public ArrayList<Character> kustutatudEse = new ArrayList<>();
    public Ese(Maailm maailm, int xPos, int yPos) {
        super(maailm, maailm.hangiEsemed(), xPos, yPos);
        maailm.lisaEse(this);
    }

    public void kontrolliKasKeegiSeisabPeal() {
        // vaata maailma tegelaste mapist kas keegi istub sellel koordinaadil
        // kui on siis prindi midagi ja kustuta ese praegu
        Tegelane tegelane = maailm.hangiTegelane(hangiKoordinaat());
        if (tegelane != null) {
            char esemeSümbol = hangiSymbol();
            System.out.println("Viimati hangitud ese " + esemeSümbol);
            maailm.kustutaEse(this);
            saadudPunktid++;
            kustutatudEse.add(esemeSümbol);
            System.out.println("Punktiseis: " + saadudPunktid);
            System.out.println("Kõik ülesvõetud esemed: " + kustutatudEse);
        }
    }

    @Override
    public void muudaRuuduKohta(boolean pealeKohamuutust) {

    }
}
