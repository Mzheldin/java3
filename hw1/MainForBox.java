package hw1;

public class MainForBox {

    public static void main(String[] args) {
        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Orange> orangeBox1 = new Box<>();

        for (int i = 0; i < 5; i++) appleBox1.putFruit(new Apple());
        System.out.println("1st apple box's weight " + appleBox1.getWeight());

        appleBox1.emptyToBox(appleBox2);
        System.out.println("1st apple box's weight " + appleBox1.getWeight() + "\n" + "2nd apple box's weight "
                + appleBox2.getWeight());

        for (int i = 0; i < 4; i++) orangeBox1.putFruit(new Orange());
        System.out.println("Orange box's weight " + orangeBox1.getWeight());
        System.out.println("2nd apple box weight = orange box weight? " + appleBox2.boxCompare(orangeBox1));

    }
}
