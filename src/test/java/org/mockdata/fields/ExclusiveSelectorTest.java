package org.mockdata.fields;

import org.junit.Assert;
import org.junit.Test;
import org.mockdata.Record;
import org.mockdata.RecordEngine;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ExclusiveSelectorTest {

    private List<String> data = Arrays.asList("a", "b", "c", "d");

    @Test
    public void testExclusiveSelector() {
        final ExclusiveSelectorField<String> a = new ExclusiveSelectorField<>(data);
        final ExclusiveSelectorField<String> b = new ExclusiveSelectorField<>(data);
        final ExclusiveSelectorField<String> c = new ExclusiveSelectorField<>(data);
        final ExclusiveSelectorField<String> d = new ExclusiveSelectorField<>(data);

        final RecordEngine re = new RecordEngine(a, b, c, d);

        re.stream().limit(10).map(Record::getValues).forEach(v -> Assert.assertTrue(verify(v)));
    }

    private boolean verify(final Object[] output) {
        if (output.length != data.size())
            return false;

        for (final Object o : output) {
            if (!(o instanceof String))
                return false;

            final String str = (String) o;

            if (!data.contains(str))
                return false;

            int count = 0;

            for (Object other : output) {
                if (Objects.equals(o, other))
                    count++;
            }

            if (count != 1)
                return false;
        }

        return true;
    }
}
