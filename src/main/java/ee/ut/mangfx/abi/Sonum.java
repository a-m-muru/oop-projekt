package ee.ut.mangfx.abi;

public class Sonum {
    private int aeg = 200;
    private String tekst;

    public Sonum(String tekst) {
        this.tekst = tekst;
    }

    public int vahendaAega() {
        aeg--;
        return aeg;
    }

    public String hangiTekst() {
        return tekst;
    }
}
