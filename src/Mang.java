import maailm.Maailm;
import tegelased.Mangija;
import visuaal.Kuvaja;

import java.util.Scanner;

public class Mang {
    private boolean jookseb = false;
    private long aegMoodunud;
    private long viimaneUuendus;
    private Maailm maailm;
    private final Scanner silm = new Scanner(System.in);

    public void alusta() {
        Mangija mangija = new Mangija(25, 5);
        this.maailm = new Maailm(50, 10, mangija);
        maailm.lisaTegelane(mangija);
        jookseb = true;
        while (jookseb) {
            pohiTsykkel();
        }
    }

    private void pohiTsykkel() {
        Kuvaja.kuva(maailm);
        String n = silm.nextLine();
        haldaSisendit(n);
    }

    private void haldaSisendit(String sisend) {
        System.out.println(sisend.strip() + " kas on d sees: " + sisend.contains("d"));
        if (sisend.contains("d")) {
           maailm.hangiMangija().muudaXPos(1);
        }
    }
}
