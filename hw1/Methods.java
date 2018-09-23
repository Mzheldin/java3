package hw1;

import java.util.ArrayList;
import java.util.Arrays;

public class Methods {
    public static void main(String[] args) {
        String[] strings = {"1","2","3","4","5"};
        arrChangePos(strings, 2, 4);
        System.out.println(Arrays.toString(strings));
        System.out.println(toArrayList(strings));

    }

    public static void arrChangePos(Object[] arr, int a, int b){
        if ((a < 0) || (b < 0) || (a > arr.length) || (b > arr.length)){
            System.out.println("Incorrect input");
            return;
        }
        Object c = arr[a];
        arr[a] = arr[b];
        arr[b] = c;
    }

    public static ArrayList toArrayList(Object[] arr){
        ArrayList<Object> arrayList = new ArrayList<>(arr.length);
        for (Object o:arr) {
            arrayList.add(o);
        }
        //System.out.println(arrayList.get(0).getClass());
        return arrayList;
    }
}
