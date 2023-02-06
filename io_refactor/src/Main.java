class NormalPayment extends Payment {
    double getBillableAmount() {
        double baseAmt = units * rate;
        double tax = baseAmt * TAX_RATE;
        return baseAmt + tax;
    }
    NormalPayment(int units, double rate) {
        super(units,rate);
    }
}

class PaymentForSeniorCitizen extends Payment {
    final double seniorRatio = 0.8;
    double getBillableAmount() {
        double baseAmt = units * rate * seniorRatio;
        double tax = baseAmt * (TAX_RATE - 0.5) ;
        return baseAmt + tax;
    }

    PaymentForSeniorCitizen(int units, double rate) {
        super(units,rate);
    }
}

class Payment {
    int units;
    double rate;
    final double TAX_RATE = 0.1;

    Payment(int units, double rate) {
        this.units = units;
        this.rate = rate;
    }
}

public class Main {

    public static void main(String[] args) {
        PaymentForSeniorCitizen zaplata = new PaymentForSeniorCitizen();
        System.out.println("Hello world!");
    }
}