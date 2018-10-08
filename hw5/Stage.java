package hw5;

public abstract class Stage {
    protected static boolean winner = true;
    protected boolean finish;
    protected int length;
    protected String description;
    public String getDescription () {
        return description;
    }
    public abstract void go (Car c);

    protected void finished(String carname){
        if (finish) {
            Main.cdlPECountDown();
            if (winner) {
                winner = false;
                System.out.println(carname + " WIN");
            }
        }
    }
}