package hw6;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class MethodsTest {
    @Parameterized.Parameters
            public static Collection<Object[]> data(){
        int[] a1 = {1,2,3,4,5,6,7,8,9};
        int[] q1 = {5,6,7,8,9};
        int[] a2 = {0,2,3,5,6,7,4,8,9};
        int[] q2 = {8,9};
        int[] a3 = {4,1,2,3,5,6};
        int[] q3 = {1,2,3,5,6};
        int[] a4 = {1,2,3,4};
        int[] q4 = {};
        return Arrays.asList(new Object[][]{
                {true, a1, q1 },
                {true, a2, q2},
                {true, a3, q3},
                {true, a4, q4}
        });
    }
    private boolean a;
    private int[] b;
    private int[] c;
    public MethodsTest(boolean a, int[] b, int[] c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @org.junit.Test
    public void arrAfterLast4() {
        Assert.assertArrayEquals(c, Methods.arrAfterLast4(b));
    }

    @org.junit.Test
    public void arrContains1or4() {
        Assert.assertEquals(a, Methods.arrContains1or4(b));
    }

    private int[] arr = {0,2,3,5,6,7,8,9};
    @org.junit.Test (expected = RuntimeException.class)
    public void arrContains1or4err() {
        Assert.assertEquals(1, Methods.arrAfterLast4(arr));
    }

    @org.junit.Test
    public void arrContains1or4false() {
        Assert.assertEquals(false, Methods.arrContains1or4(arr));
    }
}