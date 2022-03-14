package by.kharchenko.xml.parser.dom;

import by.kharchenko.xml.entity.*;
import by.kharchenko.xml.parser.XmlPublicationTags;
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

    private Papers catalog;
    private DocumentBuilder docBuilder;

    public PublicationDOMParser() {
        this.catalog = new Papers();
        // создание DOM-анализатора
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Ошибка конфигурации парсера: " + e);
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
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
        }
    }

    private AbstractPublication buildMagazine(Element publication) {
        Magazine magazine =  new Magazine();
        magazine.setId(publication.getAttribute(XmlPublicationTags.ID.getName()));
        magazine.setGlossy(publication.getAttribute(XmlPublicationTags.GLOSSY.getName()));
        magazine.setPages(Integer.parseInt(getElementTextContent(publication, XmlPublicationTags.PAGES.getName())));
        magazine.setDate(LocalDateTime.parse(getElementTextContent(publication, XmlPublicationTags.DATE.getName())));
        magazine.setMonthly(Boolean.parseBoolean(getElementTextContent(publication, XmlPublicationTags.MONTHLY.getName())));
        magazine.setTitle(getElementTextContent(publication, XmlPublicationTags.TITLE.getName()));
        magazine.setSubscription_index(Integer.parseInt(getElementTextContent(publication, XmlPublicationTags.SUBSCRIPTION_INDEX.getName())));
        return magazine;
    }

    private AbstractPublication buildBooklet(Element publication) {
        Booklet booklet =  new Booklet();
        booklet.setId(publication.getAttribute(XmlPublicationTags.ID.getName()));
        booklet.setGlossy(publication.getAttribute(XmlPublicationTags.GLOSSY.getName()));
        booklet.setPages(Integer.parseInt(getElementTextContent(publication, XmlPublicationTags.PAGES.getName())));
        booklet.setDate(LocalDateTime.parse(getElementTextContent(publication, XmlPublicationTags.DATE.getName())));
        booklet.setMonthly(Boolean.parseBoolean(getElementTextContent(publication, XmlPublicationTags.MONTHLY.getName())));
        booklet.setTitle(getElementTextContent(publication, XmlPublicationTags.TITLE.getName()));
        return booklet;
    }

    private AbstractPublication buildNewspaper(Element publication) {
        Newspaper newspaper =  new Newspaper();
        newspaper.setId(publication.getAttribute(XmlPublicationTags.ID.getName()));
        newspaper.setPages(Integer.parseInt(getElementTextContent(publication, XmlPublicationTags.PAGES.getName())));
        newspaper.setDate(LocalDateTime.parse(getElementTextContent(publication, XmlPublicationTags.DATE.getName())));
        newspaper.setMonthly(Boolean.parseBoolean(getElementTextContent(publication, XmlPublicationTags.MONTHLY.getName())));
        newspaper.setTitle(getElementTextContent(publication, XmlPublicationTags.TITLE.getName()));
        newspaper.setSubscription_index(Integer.parseInt(getElementTextContent(publication, XmlPublicationTags.SUBSCRIPTION_INDEX.getName())));
        return newspaper;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

}
