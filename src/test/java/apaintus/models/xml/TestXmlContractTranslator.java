package apaintus.models.xml;

import apaintus.models.Point;
import apaintus.models.nodes.Node;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestXmlContractTranslator {
    @Test
    public void testTranslateTo(){
        Node s1 = mock(Node.class);
        when(s1.getCoordinates()).thenReturn(new Point(1,1));
        when(s1.getHeight()).thenReturn(20.0);
        when(s1.getWidth()).thenReturn(20.0);
        when(s1.getOrientation()).thenReturn(0.0);

        List<Node> testList = Arrays.asList(s1,s1,s1,s1,s1,s1);

        assertNotNull(XmlContractTranslator.translateTo(testList));
    }

    @Test
    public void testTranslateFrom(){
        XmlContract testContract = mock(XmlContract.class);

        Node s1 = mock(Node.class);
        when(s1.getCoordinates()).thenReturn(new Point(1,1));
        when(s1.getHeight()).thenReturn(20.0);
        when(s1.getWidth()).thenReturn(20.0);
        when(s1.getOrientation()).thenReturn(0.0);

        List<Node> testList = Arrays.asList(s1,s1,s1,s1,s1,s1);

        when(testContract.getNodeList()).thenReturn(testList);

        assertArrayEquals(testList.toArray(),XmlContractTranslator.translateFrom(testContract).toArray());
    }
}