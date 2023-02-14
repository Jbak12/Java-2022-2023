import java.util.*;
class Ziomal {
    String name;
    int age;
    Ziomal(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
public class Main {

    Ziomal zioma1 = new Ziomal("Jacek", 15);
    Ziomal zioma2 = new Ziomal("Darek", 18);

    Set<Ziomal> ziomale = new HashSet<>();

    void addziomali() {
        ziomale.add(zioma1);
        ziomale.add(zioma2);
    }

    void edytuj() {
        ziomale.stream().filter(z->z.age == 15).findFirst().get().name = "jacuwa";
    }
    void wypisz() {
        for(Ziomal z: ziomale) {
            System.out.println(z.name);
        }
    }



    public static void main(String[] args) {
        System.out.println("Hello world!");
        Main main = new Main();
        main.addziomali();
        main.wypisz();
        main.edytuj();
        main.wypisz();

    }
}