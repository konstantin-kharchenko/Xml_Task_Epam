package by.kharchenko.xml.builder.director;

import by.kharchenko.xml.builder.parser_builder.AbstractBaseBuilder;
import by.kharchenko.xml.entity.*;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class PublicationDirector {
    public static Papers createPublication (AbstractBaseBuilder builder, String path) throws IOException, SAXException, XMLStreamException {
       builder.buildCatalog(path);
       return builder.getCatalog();
    }
}
