Tere :)
Oleme Aksel Martin ja Liina.
Praegu on meil kavas teha konsoolimängu, eeskujuks 1980. aasta mäng Rogue.

## Mängu praegune seis
Mängija saab sisendina anda tähti w a s d, et mänguväljal liikuda. Seintest läbi minna ei saa. 
Eksisteerivad teised tegelased, kes hetkel suvaliselt mööda maailma liiguvad.
Samuti eksisteerivad esemed, mida saab võtta üles ja selle eest punkte saada.

## Põhiline plaan 
Hetkel on kavas, et Aksel Martinil kirjutada andmete interpretaator
(so. asjad nagu nt relvad, tegelased, muud on tekstifailidena mingis formaadis kirjas
ning mäng alustades loeb need andmed sisse ning valmistab vastavad klasside isendid)
ning Liinal kirjutada hulk interpreteeritavaid andmeid (ehk kunstniku rollis justkui).

## Kasutusjuhend
Käivitada klass main.Main, soovitatavalt arvuti terminalis (out/production/main-kaustas jooksutada `java main/Main`).

Selleks, et liikuda...

...paremale tuleb vajutada d

...vasakule tuleb vajutada a

...üles tuleb vajutada w

...alla tuleb vajutada s
Kui elud on otsa saanud saab mängust väljuda vajutades ENTER-it.
Selleks, et näha nimekirja kogutud esemetest saab avada uut akent vajutades nuppu "Esemed". 

## Klassid
* Main - peaklass, kust saab mängu käivitada.

- PACKAGE main, kus sees on
* Mang - põhiline klass, kus määratakse peategelane, tegelased, algkoordinaadid ning hallatakse sisendit.

- PACKAGE maailm, kus sees on klassid:
* Maailm - klass, mis sisaldab mänguala andmeid, tegelaste positsioonid, tubade positsioonid ja suurused.
* Tuba - klass, mis genereerib toa mängumaastikule
* Punkt - klass, milles on positsoiinikoordinaadid maailma ruudustikus ning kuvatavat sümbolit.
* Ese - klass, mis kirjeldab ülesvõetavat eset ning selle omadusi.

- PACKAGE visuaal, kus sees on
* Kuvaja - klass, mis genereerib maailma pildi ning kuvab seda, uuendab mängu seisu andes teada vahepealset statistikat ning võimaldab taasluua kuvari pärast iga liigutuse tegemist.

- PACKAGE tegelased, kus sees on klassid:
* Mangija - klass, mis kontrollib peategelast
* Tegelane - klass, mis kontrollib mängu teisi tegelasi
* Limus - klass, mis loob peategelast ründavaid tegelasi

- PACKAGE abi, kus on
- Abi - abimeetodid
- Koordinaat - kujutab kahemõõtmelist täisarvulist koordinaati
- Sonum - hoolitseb selle eest, et sõnumid saaksid kuvatud ning kustuksid teatud aja pärast
- Sonumid - lisab uued sõnumid ekraanile

## Projekti tegemise protsessi kirjeldus
```
Projektiga alustasime 13.03.2024, saime kokku ja asusime tööle. Tegime valmis põhiklassid, mõned meetodid ja lahendasime sisendi probleemi- programm ei tahtnud meie sisendist aru saada ja tegelast liigutada ilma, et ta uuesti terve maailma väljastaks.
Klasside ja meetodite tegemine oli üsna lihtne, kuid sisendi probleemi lahendamine võttis aega.
20.03.2024 Aksel Martin arendas edasi maastikku ja maailma ning Liina tegeles mängu reeglite ja omaduste paikapanemisega.
27.03.2024 Tegelesime ruumidega, tegelaste positsioonidega ja punktisüsteemi genereerimisega. Leppisime reaalsusega, et hetkel me mängu tegevust blokeerimata ei saa sisendit lugeda.
03.04.2024 Tegelesime sellega, et toal oleks uks, mille kaudu tegelane saab väljuda toast ning klassiga Ese, et tegelasel oleks võimalik ese mängumaailmast "üles" võtta ja selle eest punkte saada.

Projekti 2. etapp
10.04.2024 Paigutasime seni terminalis toimunud tegevuse üle graafilisse keskkonda. Lisaks muutsime sisendit, et see kontrolliks klahvivajutusi. Graafilisse keskkonda üleminekuga lahenes ka meie non-blocking input'i probleem:D
17.04.2024 Tegime mängule erinevad toad, mille vahel saab liikuda. Lahendasime oma "uks nurgas" probleemi. Lisaks selleke lisasime mängu kõrvale sõnumid ülesvõetud eseme ja mängu skoori kohta. 
24.04.2024 Tegelesime sellega, et toad oleksid ilusamad, sõnumid skoori ja elude kohta ei kaoks ära ning et rünnaku alla sattudes väheneksid tegelaste elud.
```
### Iga rühmaliikme panus (sh tehtud klassid/meetodid) ja ajakulu (orienteeruvalt);
Mõlema liikme panus tundides on umbes 8h (1. etapp)
... (2. etapp)

### Tegemise mured (nt millistest teadmistest/oskustest tundsite projekti tegemisel puudust);
Üks probleemidest, millega puutusime kokku oli non blocking input ehk me ei saanud teha nii, et mäng loeks mängija inputi kohe, ilma enter-klahvi vajutamata.
Teine mure oli sellega, kuidas pärast sammu tegemist/tegelase liigutamist kuvada uuendatud mänguväli


### Hinnang oma töö lõpptulemusele (millega saite hästi hakkama ja mis vajab arendamist);
Hästi: 
* Tegelane liigub seal, kus vaja ning ei liigu seal, kus ta ei pea(sein on takistuseks)
* Tegelane saab kätte eseme, saab selle eest punkti ja esemed lisatakse ülesvõetud esemete nimekirja
* Kõrvaltegelased on olemas ning liiguvad sõltumata peategelasest
* Mäng läheb tööle ning on võimalik saada punkte

Vajab arendamist:
* Lisada juurde esemeid, mille eest tegelane saab punkte saada
* Teha teised toad suurema raskusastmega
* Lisada takistused ning elude arvestus
* Kui peategelase elud saavad otsa, mäng lõpeb
* Esemete list ja esemete kasutamine peategelase kaitsmiseks/ teise tegelase ründamiseks

### Selgitus ja/või näited, kuidas programmi osi eraldi ja programmi tervikuna testisite ehk kuidas veendusite, et programm töötab korrektselt.

Proovisime läbi, kas peategelane liigub vastavalt sellele, mis tähte on vajutatud;
Vaatasime, et peategelane ei pääseks toast välja läbi seina, vaid ainult läbi ukse;
Kontrollisime, et kõrvaltegelased liiguksid sõltumata peategelasest;
Kontrollisime, et peategelane saaks võtta üles eseme, see lisataks nimekirja ning selle eest saaks punkti;
Veendusime, et tegelane liigub vaid siis, kui vajutada õiged nupukombinatsioonid;


## Mängust:
(kaldkirjas kirjed on implementeerimata.)
Tegelased:
* on olemas peategelane, kes liigub ringi mängumaailmas, peab läbima _erineva raskusastmega_ tube. Peategelase tähis on "@". 
* tegelased, kes liiguvad ringi mängumaailmas ning takistavad peategelasel tubade läbimist. Tegelaste tähis on (hetkel) "Ö".
* peategelasel on võimalik koguda punkte


Maailm: 
* Mänguväli, kus tegelane liigub ringi. Koosneb erinevatest tubadest.
* _Erinevatel tubadel võib olla erinev läbimise raskusaste_. 
* Maailmal on kindel suurus, millest välja tegelane ei pääse.
* Tegelane ei pääse välja ka toast kus ta parasjagu asub, kui ta ei ole teinud ära pääsemiseks vajalikku ülesannet


_Takistused_:
* _Takistused on mõeldud selleks, et teha mängu mängimise põnevamaks ja tubade läbimise keerulisemaks._
* _Takistustega kokkupuutel on võimalik kaotada elusid._

