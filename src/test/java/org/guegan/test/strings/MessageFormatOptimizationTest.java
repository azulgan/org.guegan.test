package org.guegan.test.strings;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * (c) aguegan, 2018
 * User: aguegan
 * Date: 2/16/18
 * Time: 8:50 PM
 */
@RunWith(Parameterized.class)
public class MessageFormatOptimizationTest {

    @Parameterized.Parameters
    public static int[][] returnParams() {
        return new int[][] { new int[] { 100} , new int[] { 1000} , new int[] {10000}};
    }

    private int nb;
    private static final Object[] args = {new Date(), 17, "Rennes", 56.18, 'B', null};
    private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public MessageFormatOptimizationTest(int [] nb) {
        this.nb = nb[0];
    }

    @Test
    public void stringTest() {
        StringBuilder accu = new StringBuilder(10000);
        for (int i = 0; i < nb; i++) {
            args[5] = i;
            String res = "Generated: MyObject " + df.format(args[0]) + ", " + args[1] + ", " + args[2] + ", " + args[3] + ", " + args[4];
            accu.append(res);
        }
    }

    @Test
    public void messageFormatTest() {                //:date:dd/MM/yyyy
        final String format = "Generated: MyObject {0,date,dd/MM/yyyy}, {1}, {2}, {3}, {4}";
        StringBuilder accu = new StringBuilder(10000);
        for (int i = 0; i < nb; i++) {
            args[5] = i;
            String res = MessageFormat.format(format, args);
            accu.append(res);
        }
    }

    @AfterClass
    public static void restultAnalysis() {
        NumberFormat nf = NumberFormat.getInstance();
        System.out.println(nf.format(33.0/9.0));
        System.out.println(nf.format(157.0/27.0));
        System.out.println(nf.format(543.0/83.0));
    }

}
