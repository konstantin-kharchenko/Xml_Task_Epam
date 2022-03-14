package parser.dom;

import by.kharchenko.xml.builder.director.PublicationDirector;
import by.kharchenko.xml.builder.parser_builder.extends_builders.DOMBuilder;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class DOMTest {

    @Test
    public void testBuilder() throws SAXException, IOException, XMLStreamException {
        var a = PublicationDirector.createPublication(new DOMBuilder(), "src/main/resources/files/papers.xml") ;
        System.out.println(a);
    }
}
