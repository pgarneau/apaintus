package apaintus.models.xml;

import apaintus.models.Point;
import apaintus.models.nodes.Node;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class TestXmlContract {
    @Test
    public void testXmlContract(){
        Node s1 = mock(Node.class);
        when(s1.getCoordinates()).thenReturn(new Point(1,1));
        when(s1.getHeight()).thenReturn(20.0);
        when(s1.getWidth()).thenReturn(20.0);
        when(s1.getOrientation()).thenReturn(0.0);

        List<Node> testInitList = Arrays.asList(s1);
        XmlContract test = new XmlContract(testInitList);
        assertNotNull(test);
    }
}