package by.kharchenko.xml.parser.dom;

import by.kharchenko.xml.entity.*;
import by.kharchenko.xml.parser.XmlPublicationTags;
import by.kharchenko.xml.parser.sax.PublicationSAXParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.print.Book;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

public class PublicationDOMParser {

    private static final Logger LOGGER = LogManager.getLogger(PublicationSAXParser.class);
    private final Papers catalog;
    private DocumentBuilder docBuilder;

    public PublicationDOMParser() {
        this.catalog = new Papers();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.ERROR, "Ошибка конфигурации парсера: " + e);
        }
    }

    public Papers getCatalog() {
        return catalog;
    }

    public void buildCatalog(String fileName) {
        Document doc = null;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList publicationList = root.getElementsByTagName("newspaper");
            for (int i = 0; i < publicationList.getLength(); i++) {
                Element publication = (Element) publicationList.item(i);
                AbstractPublication abstractPublication = buildNewspaper(publication);
                catalog.add(abstractPublication);
            }
            publicationList = root.getElementsByTagName("booklet");
            for (int i = 0; i < publicationList.getLength(); i++) {
                Element publication = (Element) publicationList.item(i);
                AbstractPublication abstractPublication = buildBooklet(publication);
                catalog.add(abstractPublication);
            }
            publicationList = root.getElementsByTagName("magazine");
            for (int i = 0; i < publicationList.getLength(); i++) {
                Element publication = (Element) publicationList.item(i);
                AbstractPublication abstractPublication = buildMagazine(publication);
                catalog.add(abstractPublication);
            }
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "File error or I/O error: " + e);
        } catch (SAXException e) {
            LOGGER.log(Level.ERROR, "Parsing failure: " + e);
        }
    }

    private AbstractPublication buildMagazine(Element publication) {
        Magazine magazine = new Magazine();
        addPublicationElements(magazine, publication);
        return new Magazine.MagazineBuilder(magazine)
                .withSubscriptionIndex(Integer.parseInt(getElementTextContent(publication, XmlPublicationTags.SUBSCRIPTION_INDEX.getName())))
                .build();
    }

    private AbstractPublication buildBooklet(Element publication) {
        Booklet booklet = new Booklet();
        addPublicationElements(booklet, publication);
        return booklet;
    }

    private AbstractPublication buildNewspaper(Element publication) {
        Newspaper newspaper = new Newspaper();
        addPublicationElements(newspaper, publication);
        return new Newspaper.NewspaperBuilder(newspaper)
                .withSubscriptionIndex(Integer.parseInt(getElementTextContent(publication, XmlPublicationTags.SUBSCRIPTION_INDEX.getName())))
                .build();
    }

    private void addPublicationElements(AbstractPublication publication, Element element) {
        publication.setId(element.getAttribute(XmlPublicationTags.ID.getName()));
        publication.setPages(Integer.parseInt(getElementTextContent(element, XmlPublicationTags.PAGES.getName())));
        publication.setDate(LocalDateTime.parse(getElementTextContent(element, XmlPublicationTags.DATE.getName())));
        publication.setMonthly(Boolean.parseBoolean(getElementTextContent(element, XmlPublicationTags.MONTHLY.getName())));
        publication.setTitle(getElementTextContent(element, XmlPublicationTags.TITLE.getName()));
        publication.setGlossy(element.getAttribute(XmlPublicationTags.GLOSSY.getName()));
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }

}
