package apaintus.models.xml;

import apaintus.models.Point;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class TestXmlContract {

    @Test
    public void testXmlContract(){
        DrawableShape s1 = mock(DrawableShape.class);
        when(s1.getCoordinates()).thenReturn(new Point(1,1));
        when(s1.getHeight()).thenReturn(20.0);
        when(s1.getWidth()).thenReturn(20.0);
        when(s1.getOrientation()).thenReturn(0.0);
        when(s1.getStrokeColor()).thenReturn("Black");
        when(s1.getStrokeSize()).thenReturn(5.0);
        when(s1.getShapeType()).thenReturn(ShapeType.RECTANGLE);

        List<DrawableShape> testInitList = Arrays.asList(s1);
        List<DrawableShape> testList = Arrays.asList(s1,s1,s1,s1,s1,s1);
        XmlContract test = new XmlContract(testInitList);
        assertNotNull(test);

        test.setShapeList(testList);
        assertArrayEquals(testList.toArray(),test.getShapeList().toArray());
    }
}