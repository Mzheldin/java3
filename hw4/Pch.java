package hw4;

public class Pch {
    volatile char vch;
    char[] chrs = {'A','B','C'};
    int j = 0;
    int i = 0;
    public  synchronized void printChar(char ch){
        while (i < 5){
            vch = chrs[j];
            if (ch == vch) {
                System.out.print(ch);
                j++;
                if (j == 3){
                    j = 0;
                    i++;
                }
                notifyAll();
                wt();
            } else wt();
        }
    }

    private void wt(){
        try {
            wait();
        } catch (Exception e) { }
    }
}
