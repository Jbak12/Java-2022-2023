public class Decoder {
    //liczba wprowadzana w sekcji danych
    private String outPut = "";
    //aktualnie konstruowana liczba
    private String currentNumber = "";
    //liczba powtorzen danej liczby
    private int repetitions = 0;
    //mnoznik cyfr wprowadzanych w sekcji powtorzen
    private int multiplier = 1000;
    /* zmienna opicująca obecny stan wczytywania (stan wczytywania liczby do powtarzania,
    bądź stan wpisywania ilosci powtorzen)*/
    private Mode inputMode = Mode.NUM;

    // enum opisujący to to czy wprowadzamy ilość powtorzen danej liczby (REPEAT) czy wprowadzamy daną liczbę (NUM)
    private enum Mode {
        NUM,
        REPEAT
    }
    //metoda pobierająca cyfrę od użytkownika
    public void input(byte value) {

        if (!(value < 10 && value >=0 )) {
            return;
        }

        if (inputMode == Mode.NUM) {
            if (value == 0) {
                inputMode = Mode.REPEAT;
                multiplier = 1000;
            } else {
                currentNumber += value;
            }
        } else if (inputMode == Mode.REPEAT) {
            repetitions += value * multiplier;
            if (multiplier == 1) {
                inputMode = Mode.NUM;
                outPut += currentNumber.repeat(repetitions);
                currentNumber = "";
                repetitions = 0;
            }
            multiplier = multiplier/10;
        }
    }
    //metoda zwracająca powstały ciąg znaków
    public String output() {
        return outPut;
    }
    // metoda przywracająca dane do stanu początkowego
    public void reset() {
        outPut = "";
        inputMode = Mode.NUM;
        multiplier = 1000;
        currentNumber = "";
    }
}
