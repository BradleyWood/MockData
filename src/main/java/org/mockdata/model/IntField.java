package org.mockdata.model;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class IntField extends DiscreteNumericField<Integer> {

    private Random random = new Random();

    public IntField() {
        this(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public IntField(Integer min, Integer max) {
        super(min, max);
    }

    @NotNull
    @Override
    public Integer generate() {
        if (getMin() == null || getMax() == null)
            throw new NullPointerException("Invalid min or max value!");

        int max = getMax().intValue();
        int min = getMin().intValue();

        long diff = (long) max - min;
        int bound = (int) diff;

        if (diff > Integer.MAX_VALUE) { // handle bounds greater than 31 bits
            min += random.nextInt(Integer.MAX_VALUE);
            bound = (int) (diff - Integer.MAX_VALUE - 1);
            bound--;
        }

        int value = min + random.nextInt(bound + 1);

        if (!isValid(value))
            throw new RuntimeException("Invalid integer generated: " + value + " acceptable range [" + min + "," + max + "]");

        return value;
    }
}
