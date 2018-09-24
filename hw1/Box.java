package hw1;

import java.util.ArrayList;

public class Box <T extends Fruit> {

    ArrayList<T> fruitsInBox = new ArrayList<>();

    public void putFruit(T obj){
        fruitsInBox.add(obj);
    }

    public float getWeight(){
        float boxWeight = 0;
        for (T o: fruitsInBox) {
            boxWeight += o.getWeight();
        }
        return boxWeight;
    }

    public boolean boxCompare(Box<? extends Fruit> box){
        return this.getWeight() == box.getWeight();
    }

    public void emptyToBox(Box<T> box){
        for (T o: fruitsInBox) {
            box.putFruit(o);
        }
        fruitsInBox.removeAll(fruitsInBox);
    }
}
