import java.util.*;

class Auctioner {
    String username = "";
    Aukcja.Powiadomienie powiadomienie;

    Integer oferta  = -2137;

    Auctioner(String username, Aukcja.Powiadomienie powiadomienie, Boolean isSubscriber  ) {
        this.username = username;
        this.powiadomienie = powiadomienie;
        this.isSubscriber = isSubscriber;
    }

    Integer getOferta() {
        return  oferta;
    }

    Boolean isSubscriber = false;
}


class  Przedmiot implements Aukcja.PrzedmiotAukcji {

    int id = 0;

    String nazwaPrzedmiotu = "";

    int aktualnaCena   = 0;

    int aktualnaOferta = 0;

    String najlepszyKlient = "";

    Set<Auctioner> auctioners = new HashSet<>();

    Boolean jestAktywna = true;

    Przedmiot(Aukcja.PrzedmiotAukcji przedmiot) {
        this.id = przedmiot.identyfikator();
        this.aktualnaOferta = przedmiot.aktualnaOferta();
        this.aktualnaCena = przedmiot.aktualnaCena();
        this.nazwaPrzedmiotu = przedmiot.nazwaPrzedmiotu();
    }

    public void dezaktywuj() {
        this.jestAktywna = false;
    }
    @Override
    public int identyfikator() {
        return  id;
    }

    @Override
    public String nazwaPrzedmiotu() {
        return nazwaPrzedmiotu;
    }

    @Override
    public int aktualnaOferta() {
        return aktualnaOferta;
    }

    @Override
    public int aktualnaCena() {
        return aktualnaCena;
    }

}

public class SerwisAukcyjny implements Aukcja{


    Set<Przedmiot> przedmioty = new HashSet<>();
    Map<String,Powiadomienie> usersToNotifications = new HashMap<>();

    @Override
    public void dodajUżytkownika(String username, Powiadomienie kontakt) {
        usersToNotifications.put(username,kontakt);
    }

    @Override
    public void dodajPrzedmiotAukcji(PrzedmiotAukcji przedmiot) {
        Przedmiot przedmiotDoDodania = new Przedmiot(przedmiot);
        przedmioty.add(przedmiotDoDodania);
    }

    @Override
    public void subskrypcjaPowiadomień(String username, int identyfikatorPrzedmiotuAukcji) {
        Przedmiot danyPrzedmiot = znajdzPrzedmiot(identyfikatorPrzedmiotuAukcji);
        if (danyPrzedmiot.auctioners.stream()
                .anyMatch(auctioner -> auctioner.username == username)) {
                    danyPrzedmiot.auctioners.stream()
                    .filter(auctioner -> auctioner.username == username).findFirst()
                                                                        .get().isSubscriber = true;

        } else {
            Auctioner sub = new Auctioner(username, usersToNotifications.get(username),true);
            danyPrzedmiot.auctioners.add(sub);
        }



    }

    @Override
    public void rezygnacjaZPowiadomień(String username, int identyfikatorPrzedmiotuAukcji) {

        znajdzPrzedmiot(identyfikatorPrzedmiotuAukcji)  .auctioners
                                                        .stream().filter(a -> a.username == username)
                                                        .findFirst()
                                                        .get().isSubscriber = false;

    }

    @Override
    public void oferta(String username, int identyfikatorPrzedmiotuAukcji, int oferowanaKwota) {

        Przedmiot przedmiot = znajdzPrzedmiot(identyfikatorPrzedmiotuAukcji);

        if  (przedmiot.jestAktywna == false){
            return;
        }

        if (oferowanaKwota > przedmiot.aktualnaCena) {
            przedmiot.aktualnaCena = oferowanaKwota;
            przedmiot.najlepszyKlient = username;
        }
        przedmiot.aktualnaOferta = oferowanaKwota;




        if (!(przedmiot.auctioners.stream().anyMatch(a -> a.username == username))) {
            Auctioner ziomal = new Auctioner(username,usersToNotifications.get(username),false);
            ziomal.oferta = oferowanaKwota;
            przedmiot.auctioners.add(ziomal);
        }


        for (Auctioner s: przedmiot.auctioners) {
            if (s.username == username) {
                s.oferta = s.oferta > oferowanaKwota ? s.oferta: oferowanaKwota;
            }
            if (Integer.valueOf(oferowanaKwota) > s.getOferta() && s.isSubscriber) {
                    s.powiadomienie.przebitoTwojąOfertę(przedmiot);
            }
        }


    }

    @Override
    public void koniecAukcji(int identyfikatorPrzedmiotuAukcji) {
        znajdzPrzedmiot(identyfikatorPrzedmiotuAukcji).dezaktywuj();
    }

    @Override
    public String ktoWygrywa(int identyfikatorPrzedmiotuAukcji) {
        return znajdzPrzedmiot(identyfikatorPrzedmiotuAukcji).najlepszyKlient;
    }

    @Override
    public int najwyższaOferta(int identyfikatorPrzedmiotuAukcji) {
        return znajdzPrzedmiot(identyfikatorPrzedmiotuAukcji).aktualnaCena;
    }

    Przedmiot znajdzPrzedmiot(int identyfikatorPrzedmiotuAukcji) {
        return przedmioty.stream()
                .filter(przedmiot -> przedmiot.id == identyfikatorPrzedmiotuAukcji)
                .findFirst()
                .get();
    }
}
