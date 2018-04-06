package org.mockdata.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class InclusiveTest {

    private IntField field;

    public InclusiveTest(int min, int max) {
        field = new IntField(min, max);
    }

    @Test
    public void testGenerate() {
        boolean min = false;
        boolean max = false;
        for (int i = 0; i < 1000; i++) {
            int val = field.generate();

            if (val == field.getMin().intValue())
                min = true;
            if (val == field.getMax().intValue())
                max = true;
            if (min && max)
                break;
        }

        Assert.assertTrue(min);
        Assert.assertTrue(max);
    }

    @Parameterized.Parameters()
    public static List<Object> values() {
        return Arrays.asList(new Object[][]{
                {80, 100},
                {0, 40},
                {-10, 10},
                {-100, -80},
                {-20, 0}
        });
    }
}