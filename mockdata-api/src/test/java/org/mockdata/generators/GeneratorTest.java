package org.mockdata.generators;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockdata.api.model.DataRequest;
import org.mockdata.api.model.FieldConfig;
import org.mockdata.fields.DataField;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;

@RunWith(Parameterized.class)
@AllArgsConstructor
public class GeneratorTest {

    private final Gson gson = new Gson();

    private final String name;
    private final String jsonObject;

    @Test
    public void testGenerator() {
        final DataRequest request = gson.fromJson(jsonObject, DataRequest.class);

        Assert.assertEquals(1, request.getFieldConfig().size());

        final FieldConfig config = request.getFieldConfig().get(0);
        final DataField instance = config.instantiate();

        Assert.assertNotNull("failed to instantiate DataField", instance);
        Assert.assertEquals("created the wrong type of DataField", name, instance.getClass().getSimpleName());
    }

    @Parameterized.Parameters(name = "{0}Test")
    public static Collection<Object[]> parameters() throws IOException {
        final Path p = Paths.get("testdata/json/fields/");
        final LinkedList<Object[]> collection = new LinkedList<>();

        Files.walk(p).filter(path -> path.toString().endsWith("json")).forEach(path -> {
            try {
                collection.add(new Object[]{path.getFileName().toString().replace(".json", ""),
                        new String(Files.readAllBytes(path))});
            } catch (IOException e) {
                collection.add(new Object[]{path.getFileName().toString().replace(".json", ""), ""});
            }
        });

        return collection;
    }
}
