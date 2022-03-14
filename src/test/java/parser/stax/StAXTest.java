package parser.stax;

import by.kharchenko.xml.builder.director.PublicationDirector;
import by.kharchenko.xml.builder.parser_builder.extends_builders.StAXBuilder;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class StAXTest {
    @Test
    public void testBuilder() throws SAXException, IOException, XMLStreamException {
        var a = PublicationDirector.createPublication(new StAXBuilder(), "src/main/resources/files/papers.xml") ;
        System.out.println(a);
    }
}
