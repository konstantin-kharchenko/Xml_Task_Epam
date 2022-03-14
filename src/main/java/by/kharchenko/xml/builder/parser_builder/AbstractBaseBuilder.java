package by.kharchenko.xml.builder.parser_builder;

import by.kharchenko.xml.entity.*;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public abstract class AbstractBaseBuilder {

    protected Papers catalog;

    public AbstractBaseBuilder() {
    }

    public Papers getCatalog() {
        return catalog;
    }

    public abstract void buildCatalog(String path) throws IOException, SAXException, XMLStreamException;

}
