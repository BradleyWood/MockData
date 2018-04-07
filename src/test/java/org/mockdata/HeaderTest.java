package org.mockdata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeaderTest {

    private Header header;

    @Before
    public void setup() {
        header = new Header();
    }

    @Test
    public void testIndex() {
        header.addColumn("x");
        header.addColumn("y");
        header.addColumn("z");

        Assert.assertEquals(3, header.size());
        Assert.assertEquals(0, header.getIndex("x"));
        Assert.assertEquals(1, header.getIndex("y"));
        Assert.assertEquals(2, header.getIndex("z"));

        header.removeColumn("y");
        Assert.assertEquals(0, header.getIndex("x"));
        Assert.assertEquals(1, header.getIndex("z"));
    }

    @Test
    public void testAddColumn() {
        header.addColumn("x");
        header.addColumn("y");
        header.addColumn(0,"z");

        Assert.assertEquals(1, header.getIndex("x"));
        Assert.assertEquals(2, header.getIndex("y"));
        Assert.assertEquals(0, header.getIndex("z"));

        header.removeColumn("y");
        Assert.assertEquals(1, header.getIndex("x"));
        Assert.assertEquals(0, header.getIndex("z"));
    }

    @Test
    public void testInit() {
        header = new Header("x", "y", "z");
        Assert.assertFalse(header.isEmpty());
        Assert.assertEquals(3, header.size());
        header.addColumn("a");
        Assert.assertEquals(4, header.size());
    }

    @Test
    public void testSize() {
        Assert.assertTrue(header.isEmpty());

        Assert.assertEquals(0, header.size());
        header.addColumn("x");
        Assert.assertEquals(1, header.size());
        header.addColumn("y");
        Assert.assertEquals(2, header.size());
        header.addColumn("z");
        Assert.assertEquals(3, header.size());
        header.removeColumn("y");
        Assert.assertEquals(2, header.size());
        header.removeColumn("x");
        header.removeColumn("z");
        Assert.assertEquals(0, header.size());

        Assert.assertTrue(header.isEmpty());
    }

    @Test
    public void testToString() {
        header.addColumn("x");
        header.addColumn("y");
        header.addColumn(0,"z");
        Assert.assertEquals("z,x,y", header.toString());
    }
}
