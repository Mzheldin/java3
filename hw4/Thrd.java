package hw4;

public class Thrd {
    public Thrd(Pch pch, char ch){
        new Thread(() ->{
            pch.printChar(ch);
        }).start();
    }
}
