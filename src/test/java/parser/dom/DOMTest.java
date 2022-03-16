package parser.dom;

import by.kharchenko.xml.builder.director.PublicationDirector;
import by.kharchenko.xml.builder.parser_builder.extends_builders.DOMBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import parser.ReferencePublication;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class DOMTest {

    @Test
    public void testBuilder() throws SAXException, IOException, XMLStreamException {
        var a = PublicationDirector.createPublication(new DOMBuilder(), "src/test/resources/files/papers.xml");
        var b = ReferencePublication.getPublicationList();
        Assert.assertEquals(a.getPublications(), b);
        System.out.println(a);
    }
}
