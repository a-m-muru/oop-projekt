Tere :)
Oleme Aksel Martin ja Liina.
Praegu on meil kavas teha konsoolimängu, eeskujuks 1980. aasta mäng Rogue.

## Mängu praegune seis
Mängija saab sisendina anda tähti w a s d, et mänguväljal liikuda. Seintest läbi minna ei saa. 
Eksisteerivad teised tegelased, kes hetkel suvaliselt mööda maailma liiguvad (nendega suhelda veel ei saa).
Samuti eksisteerivad esemed, mida saab (teoorias) võtta üles ja (teostus hiljem) selle eest punkte saada.

## Põhiline plaan 
Hetkel on kavas, et Aksel Martinil kirjutada andmete interpretaator
(so. asjad nagu nt relvad, tegelased, muud on tekstifailidena mingis formaadis kirjas
ning mäng alustades loeb need andmed sisse ning valmistab vastavad klasside isendid)
ning Liinal kirjutada hulk interpreteeritavaid andmeid (ehk kunstniku rollis justkui).

## Kasutusjuhend
Käivitada klass Main, soovitatavalt arvuti terminalis (out/production/main-kaustas jooksutada `java Main`). Mängu sulgemiseks nagu ikka Ctrl+C.
Selleks, et liikuda...
...paremale vajutada d ning ENTER
...vasakule vajutada a ning ENTER
...üles vajutada w ning ENTER
...alla vajutada s ning ENTER

## Klassid
* Main- peaklass, kust saab mängu käivitada.
* Mäng- põhiline klass, kus määratakse peategelane, tegelased, algkoordinaadid ning hallatakse sisendit.

- PACKAGE maailm, kus sees on klassid:
* Maailm- klass, mis sisaldab mänguala andmeid, tegelaste positsioonid, tubade positsioonid ja suurused.
* Tuba- klass, mis genereerib toa mängumaastikule
* Punkt- klass, milles on positsoiinikoordinaadid maailma ruudustikus ning kuvatavat sümbolit.
* Ese- klass, mis kirjeldab ülesvõetavat eset ning selle omadusi.

- PACKAGE visuaal, kus sees on
* Kuvaja- klass, mis genereerib maailma pildi ning kuvab seda, uuendab mängu seisu andes teada vahepealset statistikat ning võimaldab taasluua kuvari pärast iga liigutuse tegemist.

- PACKAGE tegelased, kus sees on klassid:
* Mangija- klass, mis kontrollib peategelast
* Tegelane- klass, mis kontrollib mängu teisi tegelasi

## Projekti tegemise protsessi kirjeldus
```
Projektiga alustasime 13.03.2024, saime kokku ja asusime tööle. Tegime valmis põhiklassid, mõned meetodid ja lahendasime sisendi probleemi- programm ei tahtnud meie sisendist aru saada ja tegelast liigutada ilma, et ta uuesti terve maailma väljastaks.
Klasside ja meetodite tegemine oli üsna lihtne, kuid sisendi probleemi lahendamine võttis aega.
20.03.2024 oli meie teine kohtumine, kus Aksel Martin arendas edasi maastikku ja maailma ning Liina tegeles mängu reeglite ja omaduste paikapanemisega.
27.03.2024 kohtusime uuesti ja tegelesime ruumidega, tegelaste positsioonidega ja punktisüsteemi genereerimisega. Leppisime reaalsusega, et hetkel me mängu tegevust blokeerimata ei saa sisendit lugeda.
03.04.2024 kohtumisel tegelesime sellega, et toal oleks uks, mille kaudu tegelane saab väljuda toast ning klassiga Ese, et tegelasel oleks võimalik ese mängumaailmast "üles" võtta ja selle eest punkte saada.
```

### Iga rühmaliikme panus (sh tehtud klassid/meetodid) ja ajakulu (orienteeruvalt);
Mõlema liikme panus tundides on umbes 8h

### Tegemise mured (nt millistest teadmistest/oskustest tundsite projekti tegemisel puudust);
Üks probleemidest, millega puutusime kokku oli non blocking input ehk me ei saanud teha nii, et mäng loeks mängija inputi kohe, ilma enter-klahvi vajutamata.


### Hinnang oma töö lõpptulemusele (millega saite hästi hakkama ja mis vajab arendamist);
Hästi: 
* Tegelane liigub seal, kus vaja ning ei liigu seal, kus ta ei pea(sein on takistuseks)
* Tegelane saab kätte eseme ...

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

