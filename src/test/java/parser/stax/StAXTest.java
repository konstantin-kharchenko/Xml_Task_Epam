package parser.stax;

import by.kharchenko.xml.builder.director.PublicationDirector;
import by.kharchenko.xml.builder.parser_builder.extends_builders.StAXBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import parser.ReferencePublication;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class StAXTest {
    @Test
    public void testBuilder() throws SAXException, IOException, XMLStreamException {
        var a = PublicationDirector.createPublication(new StAXBuilder(), "src/test/resources/files/papers.xml");
        var b = ReferencePublication.getPublicationList();
        Assert.assertEquals(a.getPublications(), b);
        System.out.println(a);
    }
}
