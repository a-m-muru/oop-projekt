package maailm;

/**
 * Ese on (tavaliselt) liikumatu asi maailma ruudustikus, millega tegelased saavad midagi teha.
 */
public class Ese extends Punkt {

    public Ese(Maailm maailm, int xPos, int yPos) {
        super(maailm, xPos, yPos);
    }

    public void kontrolliKasKeegiSeisabPeal() {
        // vaata maailma tegelaste mapist kas keegi istub sellel koordinaadil
        // kui on siis prindi midagi ja kustuta ese praegu
    }

}
