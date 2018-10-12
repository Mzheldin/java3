package hw6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Methods {

    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,6,7,8,9};
        System.out.println(arrContains1or4(arr));
        System.out.println(Arrays.toString(arrAfterLast4(arr)));
    }

    public static int[] arrAfterLast4(int[] arr){
        List<Integer> a = new ArrayList<>();
        int j = 0;
        int i = arr.length - 1;
        while (arr[i] != 4){
            a.add(arr[i]);
            i--;
            if (i == 0 && arr[i] != 4) throw new RuntimeException("В массиве должно быть хотя бы одно число 4") ;
        }
        Collections.reverse(a);
        int result[] = new int[a.size()];
        for (int o: a) {
            result[j] = o;
            j++;
        }
        return result;
    }

    public static boolean arrContains1or4(int[] arr){
        for (int i: arr) {
            if (i == 1 || i == 4) return true;
        }
        return false;
    }
}
