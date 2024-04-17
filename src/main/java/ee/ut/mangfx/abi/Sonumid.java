package ee.ut.mangfx.abi;

import java.util.ArrayList;
import java.util.List;

public class Sonumid {
    private static List<Sonum> sonumid = new ArrayList<>();

    public static void lisaSonum(String tekst) {
        Sonum sonum = new Sonum(tekst);
        sonumid.add(sonum);
    }

    public static void eluaeg() {
        for (Sonum sonum : sonumid) {
            if (sonum.vahendaAega() <= 0) {
                sonumid.remove(sonum);
            }
        }
    }

    public static String sonumiteSone() {
        StringBuilder ss = new StringBuilder();
        for (Sonum sonum : sonumid) {
            ss.append(sonum.hangiTekst());
            ss.append('\n');
        }
        return ss.toString();
    }

}
