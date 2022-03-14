package parser.sax;


import by.kharchenko.xml.builder.director.PublicationDirector;
import by.kharchenko.xml.builder.parser_builder.extends_builders.SAXBuilder;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;


public class SAXTest {

    @Test
    public void testBuilder() throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
        var a = PublicationDirector.createPublication(new SAXBuilder(), "src/main/resources/files/papers.xml");
        System.out.println(a);
    }
}
