package by.kharchenko.xml.builder.parser_builder.extends_builders;

import by.kharchenko.xml.builder.parser_builder.AbstractBaseBuilder;
import by.kharchenko.xml.parser.sax.PublicationErrorHandler;
import by.kharchenko.xml.parser.sax.PublicationSAXParser;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAXBuilder extends AbstractBaseBuilder {

    private final SAXParserFactory factory = SAXParserFactory.newInstance();
    private final SAXParser parser = factory.newSAXParser();
    private final XMLReader reader = parser.getXMLReader();
    private final PublicationSAXParser publicationSAXParser = new PublicationSAXParser();

    public SAXBuilder() throws ParserConfigurationException, SAXException {
    }

    @Override
    public void buildCatalog(String path) throws IOException, SAXException {
        reader.setContentHandler(publicationSAXParser);
        reader.setErrorHandler(new PublicationErrorHandler());
        reader.parse(path);
        catalog = publicationSAXParser.getCatalog();
    }
}
