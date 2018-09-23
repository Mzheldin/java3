package hw1;

import java.util.Random;

public class Fruit {
    float weight = 0;
    Random random = new Random();

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = random.nextFloat() + weight;
    }
}
