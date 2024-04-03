Tere :)
Oleme Aksel Martin ja Liina.
Praegu on meil kavas teha konsoolimängu, eeskujuks 1980. aasta mäng Rogue.

## Mängu praegune seis
Mängija saab sisendina anda tähti w a s d, et mänguväljal liikuda. Seintest läbi minna ei saa. Eksisteerivad teised tegelased, kes hetkel suvaliselt mööda maailma liiguvad (nendega suhelda veel ei saa).

## Põhiline plaan 
Hetkel on kavas, et Aksel Martinil kirjutada andmete interpretaator
(so. asjad nagu nt relvad, tegelased, muud on tekstifailidena mingis formaadis kirjas
ning mäng alustades loeb need andmed sisse ning valmistab vastavad klasside isendid)
ning Liinal kirjutada hulk interpreteeritavaid andmeid (ehk kunstniku rollis justkui).

## Kasutusjuhend
Käivitada klass Main, soovitatavalt arvuti terminalis (out/production/main-kaustas jooksutada `java Main`). Mängu sulgemiseks nagu ikka Ctrl+C.

## Klassid
(iga klassi kohta eraldi selle eesmärk ja olulisemad meetodid on Javadocina klasside sees.)

## Projekti tegemise protsessi kirjeldus
((erinevad etapid ja rühmaliikmete osalemine neis);)
```
Projektiga alustasime 13.03.2024, saime kokku ja asusime tööle. Tegime valmis põhiklassid, mõned meetodid ja lahendasime sisendi probleemi- programm ei tahtnud meie sisendist aru saada ja tegelast liigutada ilma, et ta uuesti terve maailma väljastaks.
Klasside ja meetodite tegemine oli üsna lihtne, kuid sisendi probleemi lahendamine võttis aega.
20.03.2024 oli meie teine kohtumine, kus Aksel Martin arendas edasi maastikku ja maailma ning Liina tegeles mängu reeglite ja omaduste paikapanemisega.
27.03.2024 kohtusime uuesti ja tegelesime ruumidega, tegelaste positsioonidega ja punktisüsteemi genereerimisega. Leppisime reaalsusega, et hetkel me mängu tegevust blokeerimata ei saa sisendit lugeda.
```

### Iga rühmaliikme panus (sh tehtud klassid/meetodid) ja ajakulu (orienteeruvalt);

### Tegemise mured (nt millistest teadmistest/oskustest tundsite projekti tegemisel puudust);
tegemise mured (nt millistest teadmistest/oskustest tundsite projekti tegemisel puudust);
Üks probleemidest, millega puutusime kokku oli non blocking input ehk me ei saanud teha nii, et mäng loeks mängija inputi kohe, ilma enter-klahvi vajutamata.

### Hinnang oma töö lõpptulemusele (millega saite hästi hakkama ja mis vajab arendamist);

### Selgitus ja/või näited, kuidas programmi osi eraldi ja programmi tervikuna testisite ehk kuidas veendusite, et programm töötab korrektselt.

## Mängust:
(kaldkirjas kirjed on implementeerimata.)
Tegelased:
* on olemas peategelane, kes liigub ringi mängumaailmas, peab läbima _erineva raskusastmega_ tube. Peategelase tähis on "@". 
* tegelased, kes liiguvad ringi mängumaailmas ning _takistavad peategelasel tubade läbimist_. Tegelaste tähis on (hetkel) "Ö".
* _peategelasel on võimalik koguda punkte_
* ...
Maailm: 
* Mänguväli, kus tegelane liigub ringi. Koosneb erinevatest tubadest.
* _Erinevatel tubadel võib olla erinev läbimise raskusaste_. 
* Maailmal on kindel suurus, millest välja tegelane ei pääse.
* Tegelane ei pääse välja ka toast kus ta parasjagu asub, kui ta ei ole teinud ära pääsemiseks vajalikku ülesannet
* ...
_Takistused_:
* _Takistused on mõeldud selleks, et teha mängu mängimise põnevamaks ja tubade läbimise keerulisemaks._
* _Takistustega kokkupuutel on võimalik kaotada elusid._

