package apaintus.models.xml;

import apaintus.models.Point;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestXmlContractTranslator {

    @Test
    public void testTranslateTo(){

        DrawableShape s1 = mock(DrawableShape.class);
        when(s1.getCoordinates()).thenReturn(new Point(1,1));
        when(s1.getHeight()).thenReturn(20.0);
        when(s1.getWidth()).thenReturn(20.0);
        when(s1.getOrientation()).thenReturn(0.0);
        when(s1.getStrokeColor()).thenReturn("Black");
        when(s1.getStrokeSize()).thenReturn(5.0);
        when(s1.getShapeType()).thenReturn(ShapeType.RECTANGLE);

        List<DrawableShape> testList = Arrays.asList(s1,s1,s1,s1,s1,s1);

        assertNotNull(XmlContractTranslator.translateTo(testList));
    }

    @Test
    public void testTranslateFrom(){
        XmlContract testContract = mock(XmlContract.class);

        DrawableShape s1 = mock(DrawableShape.class);
        when(s1.getCoordinates()).thenReturn(new Point(1,1));
        when(s1.getHeight()).thenReturn(20.0);
        when(s1.getWidth()).thenReturn(20.0);
        when(s1.getOrientation()).thenReturn(0.0);
        when(s1.getStrokeColor()).thenReturn("Black");
        when(s1.getStrokeSize()).thenReturn(5.0);
        when(s1.getShapeType()).thenReturn(ShapeType.RECTANGLE);

        List<DrawableShape> testList = Arrays.asList(s1,s1,s1,s1,s1,s1);

        when(testContract.getShapeList()).thenReturn(testList);

        assertArrayEquals(testList.toArray(),XmlContractTranslator.translateFrom(testContract).toArray());
    }

}