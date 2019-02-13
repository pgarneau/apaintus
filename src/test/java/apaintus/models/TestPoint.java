package apaintus.models;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TestPoint {
    @Test
    public void testCreateNewPointWithoutValues(){
        Point test = new Point();
        assertEquals(test.getX(), 0, 0.1);
        assertEquals(test.getY(), 0, 0.1);
    }

    @Test
    public void testCreateNewPointWithValues() {
        Point test = new Point(1, 2);
        assertEquals(test.getX(), 1, 0.1);
        assertEquals(test.getY(), 2, 0.1);
    }

    @Test
    public void testSettingNewPointValue(){
        Point test = new Point();
        test.setX(1);
        test.setY(2);
        assertEquals(test.getX(),1,0.1);
        assertEquals(test.getY(),2,0.1);
    }
}
