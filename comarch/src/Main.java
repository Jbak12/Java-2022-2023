public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

public class Test {
    private static String foo(int a) { return "A"; }
    private static String foo(int... tab) { return "B";}
    private static String foo(int a, int b) { return "C";}
    public static void main(String[] args) {
        System.out.print(foo(1));
        System.out.print(foo(1, 1));
        System.out.print(foo(1, 1, 1));
    }