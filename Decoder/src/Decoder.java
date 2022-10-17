public class Decoder {
    private String outPut = "";
    private String currentNumber = "";
    private int repetitions = 0;
    private int multiplier = 1000;

    // enum opisujący to to czy wprowadzamy ilość powtorzen danej liczby czy wprowadzamy daną liczbę
    private enum Mode {
        NUM,
        REPEAT

    }
    private Mode inputMode = Mode.NUM;

    private Boolean validateNum(byte value) {
        return (value < 10 && value >=0 );
    }

    public void input(byte value) {
        if (!validateNum(value)) {
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
    
    public String output() {
        return outPut;
    }

    public void reset() {
        outPut = "";
        inputMode = Mode.NUM;
        multiplier = 1000;
        currentNumber = "";
    }
}
