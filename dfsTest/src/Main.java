import java.util.*;

public class Main {

    public static void main(String[] args) {
        Map<String,Set<Integer>> mapka = new HashMap<>();
        Set<Integer> secik = new HashSet<>();
        secik.add(3);
        secik.add(4);
        mapka.put("a",secik);
        mapka.computeIfPresent("a",(key, val) -> val.add(6));
        System.out.println("Hello world!");
    }
}