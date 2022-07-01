package parser.sax;


import by.kharchenko.xml.builder.director.PublicationDirector;
import by.kharchenko.xml.builder.parser_builder.extends_builders.SAXBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import parser.ReferencePublication;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;


public class SAXTest {

    @Test
    public void testBuilder() throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
        var a = PublicationDirector.createPublication(new SAXBuilder(), "src/test/resources/files/papers.xml");
        var b = ReferencePublication.getPublicationList();
        Assert.assertEquals(a.getPublications(), b);
        System.out.println(a);
    }
}
