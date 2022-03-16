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
import java.io.IOException;
import java.time.LocalDateTime;

public class PublicationDOMParser {

    private static final Logger LOGGER = LogManager.getLogger(PublicationSAXParser.class);
    private Papers papers;
    private DocumentBuilder docBuilder;

    public PublicationDOMParser() {
        this.papers = new Papers();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.ERROR, "Ошибка конфигурации парсера: " + e);
        }
    }

    public Papers getPapers() {
        return papers;
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
                papers.add(abstractPublication);
            }
            publicationList = root.getElementsByTagName("booklet");
            for (int i = 0; i < publicationList.getLength(); i++) {
                Element publication = (Element) publicationList.item(i);
                AbstractPublication abstractPublication = buildBooklet(publication);
                papers.add(abstractPublication);
            }
            publicationList = root.getElementsByTagName("magazine");
            for (int i = 0; i < publicationList.getLength(); i++) {
                Element publication = (Element) publicationList.item(i);
                AbstractPublication abstractPublication = buildMagazine(publication);
                papers.add(abstractPublication);
            }
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "File error or I/O error: " + e);
        } catch (SAXException e) {
            LOGGER.log(Level.ERROR, "Parsing failure: " + e);
        }
    }

    private AbstractPublication buildMagazine(Element publication) {
        return new Magazine.MagazineBuilder((Magazine)addPublicationElements(new Magazine(), publication))
                .withSubscriptionIndex(Integer.parseInt(getElementTextContent(publication, XmlPublicationTags.SUBSCRIPTION_INDEX.getName())))
                .build();
    }

    private AbstractPublication buildBooklet(Element publication) {
        return addPublicationElements(new Booklet(), publication);
    }

    private AbstractPublication buildNewspaper(Element publication) {
        return new Newspaper.NewspaperBuilder((Newspaper)addPublicationElements(new Newspaper(), publication))
                .withSubscriptionIndex(Integer.parseInt(getElementTextContent(publication, XmlPublicationTags.SUBSCRIPTION_INDEX.getName())))
                .build();
    }

    private AbstractPublication addPublicationElements(AbstractPublication publication, Element element) {
        publication.setId(element.getAttribute(XmlPublicationTags.ID.getName()));
        publication.setPages(Integer.parseInt(getElementTextContent(element, XmlPublicationTags.PAGES.getName())));
        publication.setDate(LocalDateTime.parse(getElementTextContent(element, XmlPublicationTags.DATE.getName())));
        publication.setMonthly(Boolean.parseBoolean(getElementTextContent(element, XmlPublicationTags.MONTHLY.getName())));
        publication.setTitle(getElementTextContent(element, XmlPublicationTags.TITLE.getName()));
        publication.setGlossy(element.getAttribute(XmlPublicationTags.GLOSSY.getName()));
        return publication;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }

}
