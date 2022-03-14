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
        Magazine magazine =  new Magazine();
        publicationInit(magazine, publication);
        magazine.setGlossy(publication.getAttribute(XmlPublicationTags.GLOSSY.getName()));
        magazine.setSubscription_index(Integer.parseInt(getElementTextContent(publication, XmlPublicationTags.SUBSCRIPTION_INDEX.getName())));
        return magazine;
    }

    private AbstractPublication buildBooklet(Element publication) {
        Booklet booklet =  new Booklet();
        publicationInit(booklet, publication);
        booklet.setGlossy(publication.getAttribute(XmlPublicationTags.GLOSSY.getName()));

        return booklet;
    }

    private AbstractPublication buildNewspaper(Element publication) {
        Newspaper newspaper =  new Newspaper();
        publicationInit(newspaper, publication);
        newspaper.setSubscription_index(Integer.parseInt(getElementTextContent(publication, XmlPublicationTags.SUBSCRIPTION_INDEX.getName())));
        return newspaper;
    }

    private void publicationInit(AbstractPublication publication, Element element){
        publication.setId(element.getAttribute(XmlPublicationTags.ID.getName()));
        publication.setPages(Integer.parseInt(getElementTextContent(element, XmlPublicationTags.PAGES.getName())));
        publication.setDate(LocalDateTime.parse(getElementTextContent(element, XmlPublicationTags.DATE.getName())));
        publication.setMonthly(Boolean.parseBoolean(getElementTextContent(element, XmlPublicationTags.MONTHLY.getName())));
        publication.setTitle(getElementTextContent(element, XmlPublicationTags.TITLE.getName()));
    }
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }

}
