package visuaal;

import abi.Koordinaat;
import maailm.Ese;
import maailm.Maailm;
import maailm.Punkt;
import tegelased.Tegelane;

import java.util.HashMap;
import java.util.List;

public class Kuvaja {
    private static int xAknaSuurus = 60;
    private static int yAknaSuurus = 30;

    /**
     * Genereerib maailma pildi, kombineerides sõneks kõik nähtavad ruudud
     *
     * @param maailm sisendmaailm
     * @return sõne kujul pilt
     */
    private static char[][] hangiPilt(Maailm maailm) {
        char[][] maastik = maailm.hangiMaastik();
        char[][] pilt = new char[yAknaSuurus][xAknaSuurus];
        for (int y = 0; y < yAknaSuurus; y++) {
            for (int x = 0; x < xAknaSuurus; x++) {
                int pildiY = y + maailm.hangiMangija().hangiKoordinaat().y - yAknaSuurus / 2;
                int pildiX = x + maailm.hangiMangija().hangiKoordinaat().x - xAknaSuurus / 2;
                pilt[y][x] = (maailm.hangiMaastikuKohtVoiNull(pildiX, pildiY) == Character.MIN_VALUE)
                        ? ' ' : maailm.hangiMaastikuKoht(pildiX, pildiY);
            }
        }
        HashMap<Long, Punkt> tegelased = maailm.hangiTegelased();
        for (Punkt p : tegelased.values()) {
            Tegelane tegelane = (Tegelane) p;
            if (tegelane == null) continue;
            int pildiY = tegelane.hangiKoordinaat().y - maailm.hangiMangija().hangiKoordinaat().y + yAknaSuurus / 2;
            int pildiX = tegelane.hangiKoordinaat().x - maailm.hangiMangija().hangiKoordinaat().x + xAknaSuurus / 2;
            if (pildiX >= xAknaSuurus || pildiY >= yAknaSuurus || pildiX < 0 || pildiY < 0)
                continue;
            pilt[pildiY][pildiX] = tegelane.hangiSymbol();
        }

        HashMap<Long, Punkt> esemed = maailm.hangiEsemed();
        for (Punkt k : esemed.values()) {
            Ese ese = (Ese) k;
            if (ese == null) continue;
            int pildiY = ese.hangiKoordinaat().y - maailm.hangiMangija().hangiKoordinaat().y + yAknaSuurus / 2;
            int pildiX = ese.hangiKoordinaat().x - maailm.hangiMangija().hangiKoordinaat().x + xAknaSuurus / 2;
            if (pildiX >= xAknaSuurus || pildiY >= yAknaSuurus || pildiX < 0 || pildiY < 0)
                continue;
            pilt[pildiY][pildiX] = ese.hangiSymbol();
        }

        return pilt;
    }

    /**
     * Tühjendab ekraani
     */
    public static void kustuta() {
        // töötab linuxis
        System.out.print("\033[H\033[2J");
        System.out.flush();
        // "töötab" mujal ka (nt Windows)
        //System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public static void kuva(Maailm maailm) {
        char[][] pilt = hangiPilt(maailm);
        for (int i = 0; i < pilt.length; i++) {
            System.out.println(pilt[i]);
        }
        System.out.println();
    }

    /**
     * Prindib mängu seisu
     *
     * @param sonumid sõnumid, mida kuvada
     */
    public static void kuvaSeis(List<String> sonumid) {
        for (int i = 0; i < sonumid.size(); i++) {
            System.out.printf("%s ", sonumid.get(i));
        }
        System.out.println();
        sonumid.clear();
    }
}
