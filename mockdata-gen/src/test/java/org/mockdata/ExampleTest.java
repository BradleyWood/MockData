package org.mockdata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockdata.fields.*;

import java.util.Collection;

@RunWith(Parameterized.class)
public class ExampleTest {

    private final int Id;
    private final String fName;
    private final String lName;
    private final String email;
    private final int age;

    public ExampleTest(final int Id, final String fName, final String lName, final String email, final int age) {
        this.Id = Id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.age = age;
    }

    @Test
    public void exampleTest() {
        System.err.println(Id + " " + fName + " " + lName + " " + email + " " + age);
    }

    @Parameterized.Parameters
    public static Collection parameters() {
        final RecordEngine re = new RecordEngine(new RowNumberField(), new FirstNameField(), new LastNameField(),
                new EmailField(), new IntField(18, 65));

        return re.generate(10);
    }
}
