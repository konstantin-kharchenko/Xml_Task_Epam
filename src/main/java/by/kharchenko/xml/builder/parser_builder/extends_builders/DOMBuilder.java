package by.kharchenko.xml.builder.parser_builder.extends_builders;

import by.kharchenko.xml.builder.parser_builder.AbstractBaseBuilder;
import by.kharchenko.xml.parser.dom.PublicationDOMParser;

public class DOMBuilder extends AbstractBaseBuilder {

    @Override
    public void buildCatalog(String path) {
        PublicationDOMParser domBuilder = new PublicationDOMParser();
        domBuilder.buildCatalog(path);
        catalog = domBuilder.getCatalog();
    }
}
