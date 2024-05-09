package ee.ut.mangfx.maailm;

import ee.ut.mangfx.abi.Koordinaat;
import ee.ut.mangfx.tegelased.Tegelane;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Ese on (tavaliselt) liikumatu asi maailma ruudustikus, millega tegelased saavad midagi teha.
 */
public class Ese extends Punkt {

    public Ese(Maailm maailm, int xPos, int yPos) {
        super(maailm, maailm.hangiEsemed(), xPos, yPos);
        maailm.lisaEse(this);
    }

    @Override
    public void muudaRuuduKohta(boolean pealeKohamuutust) {

    }
}
