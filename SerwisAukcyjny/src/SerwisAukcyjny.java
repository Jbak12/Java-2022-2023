public class SerwisAukcyjny implements Aukcja{
    @Override
    public void dodajUżytkownika(String username, Powiadomienie kontakt) {

    }

    @Override
    public void dodajPrzedmiotAukcji(PrzedmiotAukcji przedmiot) {

    }

    @Override
    public void subskrypcjaPowiadomień(String username, int identyfikatorPrzedmiotuAukcji) {

    }

    @Override
    public void rezygnacjaZPowiadomień(String username, int identyfikatorPrzedmiotuAukcji) {

    }

    @Override
    public void oferta(String username, int identyfikatorPrzedmiotuAukcji, int oferowanaKwota) {

    }

    @Override
    public void koniecAukcji(int identyfikatorPrzedmiotuAukcji) {

    }

    @Override
    public String ktoWygrywa(int identyfikatorPrzedmiotuAukcji) {
        return null;
    }

    @Override
    public int najwyższaOferta(int identyfikatorPrzedmiotuAukcji) {
        return 0;
    }
}
