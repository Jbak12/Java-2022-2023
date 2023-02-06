//UWAGA: Proszę nie korzystać z domyślnych klas Javy związanych z listami :) 

public class Main {

    public static void main(String[] args) {
        //Tworzymy listę kandydatów do tronu UK
        Osoba PK=new Osoba("ksiaze Walii", "Karol");
        Osoba PW=new Osoba("ksiaze Cambridge", "Wilhelm");
        Osoba PG=new Osoba("ksiaze", "Gorge");
        Osoba PK1=new Osoba("ksiezniczka", "Karolina");

        ListaOsob KandydaciDoTronu= new ListaOsob(PK);
        KandydaciDoTronu.dodajKolejnaOsobe(PW);
        KandydaciDoTronu.dodajKolejnaOsobe(PG);
        KandydaciDoTronu.dodajKolejnaOsobe(PK1);
        //W czym jest problem: otóż teraz można wykonać coś takiego jak w dwóch poniższych liniach (tzn. dodać do listy osób obiekt klasy element)
        //element X=new element();
        //KandydaciDoTronu.addElement(X);
        //Oczywiście zakomentowany kod powyżej spowoduje później błąd rzutowania (choć sam kod się wykona), ale nie jest dobrze,
        //by w ogóle klasa listaOsob miała możliwość dodania do spisu obiektu nie będącego osobą (a teraz się da). Proszę pomyśleć
        //jak to poprawić


        Osoba temp=KandydaciDoTronu.getPierwszaOsoba();
        while(temp!=null)
        {
            System.out.println(temp.toString());
            temp=(Osoba)temp.getNastepny();
        }

    }
}

class element
{
    private element nastepny;
    public element getNastepny()
    {
        return nastepny;
    }
    public void setNastepny(element iNastepny)
    {
        nastepny=iNastepny;
    }
}

class LinkedList
{
    private element first;
    private element last;

    LinkedList(element iFirst)
    {
        first=iFirst;
        last=iFirst;
    }

    public element getFirst()
    {
        return  first;
    }

    public void addElement(element iNowy)
    {
        last.setNastepny(iNowy);
        last=iNowy;
    }
}

class Osoba extends element
{
    private String imie;
    //private String nazwisko;     //to w sumie bez sensu :)
    private String tytul;

    public Osoba(String iTytul, String iImie)
    {
        tytul=iTytul;
        imie=iImie;
    }

    @Override
    public String toString() {
        return tytul+"  "+imie;
    }
}

class ListaOsob extends  LinkedList
{
    public ListaOsob(Osoba pierwszaOsoba)
    {
            super(pierwszaOsoba);
    }

    public void dodajKolejnaOsobe(Osoba nowaOsoba)
    {
        addElement(nowaOsoba);
    }

    public Osoba getPierwszaOsoba()
    {
        return (Osoba)getFirst();
    }
}

