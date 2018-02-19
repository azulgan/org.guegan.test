package org.guegan.test.strings;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * (c) aguegan, 2018
 * User: aguegan
 * Date: 2/16/18
 * Time: 6:27 PM
 */
@RunWith(Parameterized.class)
public class StringTest {

    @Parameterized.Parameters
    public static int[][] returnParams() {
        return new int[][] { new int[] { 100} , new int[] { 1000} , new int[] {10000}};
    }

    private int nb;

    public StringTest(int [] nb) {
        this.nb = nb[0];
    }

    @Test
    public void stringTest() {
        String ret = "";
        for (int i = 0; i < nb; i++) {
            ret += "toto: " + nb + ", ";
        }
    }

    @Test
    public void stringBufferTest() {
        StringBuffer ret = new StringBuffer(nb * 10);
        for (int i = 0; i < nb; i++) {
            ret.append("toto: ").append(i).append(", ");
        }
    }

    @Test
    public void stringBuilderTest() {
        StringBuilder ret = new StringBuilder(nb * 10);
        for (int i = 0; i < nb; i++) {
            ret.append("toto: ").append(i).append(", ");
        }
    }

//    @After
//    public void after() {
//        Runtime.getRuntime().gc();
//    }
}
