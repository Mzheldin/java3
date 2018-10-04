package hw4;

public class Main {
    public static void main(String[] args) {
        Pch pch = new Pch();
        Thrd thrdA = new Thrd(pch,'A');
        Thrd thrdB = new Thrd(pch,'B');
        Thrd thrdC = new Thrd(pch,'C');

    }
}


