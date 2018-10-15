package hw7;

public class Main {

    public static void main(String[] args) {
        Calc calc = new Calc();
        Tests tests = new Tests(calc);

        Tests.start(calc.getClass());
        tests.testEquals(7, "sum",4);
        tests.testBool(true, "biggerThanZero", 1);
    }

}
