Tere :)
Oleme Aksel Martin ja Liina.
Kavas teha mäng, mille eeskujuks 1980. aastate konsoolimäng Rogue, et mängu kuvatakse tähemärkide abil.

## Mängu praegune seis

Mängija saab sisendina anda tähti w a s d, et mänguväljal liikuda. Seintest läbi minna ei saa.
Eksisteerivad teised tegelased, kes suvaliselt mööda maailma liiguvad. Neid on võimalik
rünnata ja nende käest rünnata saada. Eksisteerivad esemed, mida saab võtta üles ja selle eest punkte saada.

## Põhiline plaan

Luua mäng, milles on võimalik saada punkte, kaotada elusid ning läbida tube.
Salvestada vahepealne tulemus faili nii, et mängu uuesti alustades näev eelmist suurimat tulemust.

## Kasutusjuhend

Jooksutada ./gradlew run selles kaustas

Selleks, et liikuda...

...paremale tuleb vajutada d;

...vasakule tuleb vajutada a;

...üles tuleb vajutada w;

...alla tuleb vajutada s;

Kui elud on otsa saanud saab mängu uuesti alustada vajutades f.
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
* Loks - klass, mis kontrollib lõkse, mis üle kõndinud tegelaste elusid võtavad
* Syda - esemeklass, mis annab juurde elusid

- PACKAGE visuaal, kus sees on

* Kuvaja - klass, mis genereerib maailma pildi ning kuvab seda, uuendab mängu seisu andes teada vahepealset statistikat
  ning võimaldab taasluua kuvari pärast iga liigutuse tegemist.

- PACKAGE tegelased, kus sees on klassid:

* Mangija - klass, mis kontrollib peategelast
* Tegelane - klass, mis kontrollib mängu teisi tegelasi
* Limus - klass, mis loob peategelast ründavaid tegelasi
* Luukere - klass, mis loob peategelast ründavaid tegelasi, kes närviliselt ringi jooksevad
* Kummitus - klass, mis loob peategelast ründavaid tegelasi, kes seintest läbi liiguvad

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
17.04.2024 Tegime mängule erinevad toad, mille vahel saab liikuda. Lahendasime oma "uks nurgas" probleemi. Lisaks sellele lisasime mängu kõrvale sõnumid ülesvõetud eseme ja mängu skoori kohta. 
24.04.2024 Tegelesime sellega, et toad oleksid ilusamad, sõnumid skoori ja elude kohta ei kaoks ära ning et rünnaku alla sattudes väheneksid tegelaste elud.
2.05.2024 Panime nulleludega tegelased päriselt surema. Lisasime kõrgskoori lugemise, mis salvestatakse faili ning kuvatakse mängu alguses. Lisasime nupu üles korjatud esemete kuvamiseks.
09.05.2024 Tegime lõksud, millesse sattudes kaotavad tegelased elusid, südamed, mis annavad elusid juurde. Uued vaenlased luukered ja kummitused, veel tegelased enam ei liigu kui nad mängija vaateväljast väljas on.
15.05.2024 Lõplik viimistlus
```

### Iga rühmaliikme panus (sh tehtud klassid/meetodid) ja ajakulu (orienteeruvalt);

1. etapil mõlema liikme panus umbes 8h.
2. etapil mõlema liikme panus umbes 12h.

### Tegemise mured (nt millistest teadmistest/oskustest tundsite projekti tegemisel puudust);

1. etapp:
   Üks probleemidest, millega puutusime kokku oli non blocking input ehk me ei saanud teha nii, et mäng loeks mängija
   inputi kohe, ilma enter-klahvi vajutamata.
   Teine mure oli sellega, kuidas pärast sammu tegemist/tegelase liigutamist kuvada uuendatud mänguväli

2. etapp:
   Esemete, peategelase ja kõrvaltegelaste suhtlus, pidime natuke pead murdma selle üle, kuidas teha nii,
   et kui elud saavad otsa, siis mäng lõpeb mitte ei hakka miinuselusid sisse tooma.
   Samuti oli natukene peamurdmist sellega, kuidas luua uued toad ja teha seda nii, et need oleksid läbitavad.

### Hinnang oma töö lõpptulemusele (millega saite hästi hakkama ja mis vajab arendamist);

Hästi:

* Tegelane liigub seal, kus vaja ning ei liigu seal, kus ta ei pea(sein on takistuseks)
* Tegelane saab kätte eseme, saab selle eest punkti ja esemed lisatakse ülesvõetud esemete nimekirja
* Kõrvaltegelased on olemas ning liiguvad sõltumata peategelasest
* Mäng läheb tööle ning on võimalik saada punkte
* Kõrvaltegelased ründavad peategelast.
* Elusid on võimalik nii kaotada kui ka juurde saada.

Vajab arendamist:

* Graafiline pool, hetkel on kõik väga primitiivne

### Selgitus ja/või näited, kuidas programmi osi eraldi ja programmi tervikuna testisite ehk kuidas veendusite, et programm töötab korrektselt.

Proovisime läbi, kas peategelane liigub vastavalt sellele, mis tähte on vajutatud;
Vaatasime, et peategelane ei pääseks toast välja läbi seina, vaid ainult läbi ukse;
Kontrollisime, et kõrvaltegelased liiguksid sõltumata peategelasest;
Kontrollisime, et peategelane saaks võtta üles eseme, see lisataks nimekirja ning selle eest saaks punkti;
Veendusime, et tegelane liigub vaid siis, kui vajutada õiged nupukombinatsioonid;
Veendusime, et graafiline liides töötab;
Veendusime, et mängu tulemus salvestatakse faili;
Veendusime, et kui tegelasel on 0 elu, tema jaoks mäng lõpeb;
Veendusime, et kui peategelasel on 0 või vähem elu, mäng ei jätku

## Mängust:

(kaldkirjas kirjed on implementeerimata.)
Tegelased:

* on olemas peategelane, kes liigub ringi mängumaailmas, peab läbima erineva raskusastmega tube. Peategelase tähis
  on "@".
* tegelased 'limused', tähisega "o", kes liiguvad ringi mängumaailmas ning takistavad peategelasel tubade läbimist.
* tegelased 'luukered', tähisega "☠", kes liiguvad teatud intervallide tagant
* tegelased 'kummitused', tähisega "☃", kes liiguvad teatud intervallide tagant ja seintest läbi
* peategelasel on võimalik koguda punkte. Kõrgskoorid salvestatakse faili mängija surres (siis ja ainult siis)
* peategelasel on võimalik kaotada elusid aga on võimalik neid ka juurde saada, näiteks saades kätte "❤"

Maailm:

* Mänguväli, kus tegelane liigub ringi. Koosneb erinevatest tubadest.
* Erinevatel tubadel võib olla erinev läbimise raskusaste (tehniliselt).
* Maailmal on kindel suurus, millest välja tegelane ei pääse.
* _Tegelane ei pääse välja ka toast kus ta parasjagu asub, kui ta ei ole teinud ära pääsemiseks vajalikku ülesannet_

Lõksud:

* Lõksud on mõeldud selleks, et teha mängu mängimise põnevamaks ja tubade läbimise keerulisemaks.
* Lõksudega kokkupuutel on võimalik kaotada elusid.
* Lõksu ei ole algselt näha, see tekib siis, kui tegelane või peategelane jookseb sellele otsa. 

