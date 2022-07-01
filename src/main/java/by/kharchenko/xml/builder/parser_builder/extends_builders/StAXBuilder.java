package by.kharchenko.xml.builder.parser_builder.extends_builders;

import by.kharchenko.xml.builder.parser_builder.AbstractBaseBuilder;
import by.kharchenko.xml.parser.stax.PublicationStAXParser;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

public class StAXBuilder extends AbstractBaseBuilder {

    @Override
    public void buildCatalog(String path) throws XMLStreamException, FileNotFoundException {
        PublicationStAXParser stAXBuilder = new PublicationStAXParser();
        stAXBuilder.buildCatalog(path);
        catalog = stAXBuilder.getCatalog();

    }
}
