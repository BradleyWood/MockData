# MockData

## What is this?

This is a project designed to generate realistic mock data for
testing purposes. Very few things are supported at the moment.
However, we can generate bounded numbers, names, arrays, row numbers,
etc.

## Goals

- Data dependencies between fields of the same row

- Data generation based on statistical distributions

- Invalid test data


## Examples

#### Junit Parameterized Test example

```java
@Parameterized.Parameters
public static Collection parameters() {
    final RecordEngine re = new RecordEngine(new RowNumberField(), new FirstNameField(), new LastNameField(),
            new EmailField(), new IntField(18, 65));

    return re.generate(10);
}
```

#### Generate streamable records

```java
@Parameterized.Parameters(name = "Selector Test")
public static Collection parameters() {
    final IntField intField = new IntField(1, 100);
    final ArrayField af = new ArrayField(new IntField(), intField.generate());
    final RecordEngine re = new RecordEngine(af);

    // generate 100 random sets of varying length containing random integers

    return re.stream().limit(100).peek(a -> af.setSize(intField.generate()))
            .map(Record::getValues).collect(Collectors.toList());
}
```
