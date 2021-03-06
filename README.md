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


## Data Dependencies

Sometimes it is useful for data fields to depend on other fields. For example,
if we are generating a number of user accounts to populate a database, you might
want correlation between a person's name and gender or other such fields.
This can be accomplished by using the <font color="red">@DependentField</font> annotation.
The annotation has the class parameter which tells the record engine the type of data
field that this field is dependent on. The value of the field is set prior to execution
of the data field's generate() method.

```java
public class FirstNameField extends DataField<String> {
    
    @DependentField(dependentOn = GenderField.class)
    private Gender gender;
    
    ...
    ...
}
```

In this example if the record has a GenderField we can generate first names based
on the gender that was generated by the GenderField. If not, the field is set to null
and we could generate any first name.

## Cyclic Data Dependencies

A cyclic data dependency may occur when two data fields both depend on each other.
This type of problem does not cause a deadlock, instead the first field in the record
is generated independently of its dependent field.


## Project Layout

- [mockdata-gen](mockdata-gen/src/main/java/org/mockdata) - Api to generate mock data

- [mockdata-api](mockdata-api/src/main/java/org/mockdata/api) - Restful api to request mock data


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

## Testing

```
curl --header "Content-Type: application/json" \
--request POST \
--data "{
  "num_records": 5,
  "format": "json", // json or csv
  "field_config": [ // array of objects defining each field
    {
      "type": "row_number"
    },
    {
      "type": "first_name"
    },
    {
      "type": "integer",
      "parameters": { // specify parameters for the integer field
      "min": 18,
      "max": 65
      }
    }
  ]
}" \
http://localhost:8000/datarequest
```

Response:

```json
[
  [0, "Britt", 18],
  [1, "Joan", 48],
  [2, "Lee", 62],
  [3, "Morton", 26],
  [4, "Jacqueline", 43]
]
```
