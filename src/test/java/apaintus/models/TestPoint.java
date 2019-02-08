package apaintus.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPoint {
    @Test
    public void penis() {
        assertEquals(true, true);
    }

    @Test
    public void bonjour() {
        Point test = new Point(1, 2);
        assertEquals(test.getX(), 1, 0.1);
        assertEquals(test.getY(), 2, 0.1);
    }
}
