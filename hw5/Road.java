package hw5;

public class Road extends Stage {
    public Road ( int length, boolean finish) {
        this .length = length;
        this .description = "Дорога " + length + " метров" ;
        this.finish = finish;
    }
    @Override
    public void go (Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000 );
            System.out.println(c.getName() + " закончил этап: " + description);
            finished(c.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}