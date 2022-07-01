package by.kharchenko.xml.parser.sax;


import by.kharchenko.xml.entity.*;
import by.kharchenko.xml.parser.XmlPublicationTags;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.util.Objects;

public class PublicationSAXParser extends DefaultHandler {

    private static final Logger LOGGER = LogManager.getLogger(PublicationSAXParser.class);

    private Papers catalog;
    private String tag = null;
    private AbstractPublication abstractPublication;

    public Papers getCatalog() {
        return catalog;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (Objects.equals(XmlPublicationTags.PAPERS.getName(), qName)) {
            catalog = new Papers();
            tag = null;
        } else if (Objects.equals(XmlPublicationTags.MAGAZINE.getName(), qName)) {
            abstractPublication = new Magazine();
            setAttribute(attributes);
        } else if (Objects.equals(XmlPublicationTags.BOOKLET.getName(), qName)) {
            abstractPublication = new Booklet();
            setAttribute(attributes);

        } else if (Objects.equals(XmlPublicationTags.NEWSPAPER.getName(), qName)) {
            abstractPublication = new Newspaper();
            setAttribute(attributes);
        } else {
            tag = qName;
        }

    }

    private void setAttribute(Attributes attributes) {
        String id = attributes.getValue(0);
        String glossy = attributes.getValue(1);
        abstractPublication.setId(id);
        abstractPublication.setGlossy(glossy);
        tag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        String s = new String(ch, start, length);

        if (tag != null && !s.contains("\n")) {
            if (Objects.equals(XmlPublicationTags.COLOR.getName(), tag)) {
                abstractPublication.setColor(Boolean.parseBoolean(s));
            } else if (Objects.equals(XmlPublicationTags.TITLE.getName(), tag)) {
                abstractPublication.setTitle(s);
            } else if (Objects.equals(XmlPublicationTags.MONTHLY.getName(), tag)) {
                abstractPublication.setMonthly(Boolean.parseBoolean(s));
            } else if (Objects.equals(XmlPublicationTags.PAGES.getName(), tag)) {
                abstractPublication.setPages(Integer.parseInt(s));
            } else if (Objects.equals(XmlPublicationTags.DATE.getName(), tag)) {
                LocalDateTime dateTime = LocalDateTime.parse(s);
                abstractPublication.setDate(dateTime);
            } else if (Objects.equals(XmlPublicationTags.SUBSCRIPTION_INDEX.getName(), tag)) {
                if (abstractPublication instanceof Magazine) {
                    ((Magazine) abstractPublication).setSubscription_index(Integer.parseInt(s));
                } else if (abstractPublication instanceof Newspaper) {
                    ((Newspaper) abstractPublication).setSubscription_index(Integer.parseInt(s));
                }
            } else {
                String clazz = abstractPublication.getClass().getName();
                LOGGER.info("this field is missing in the class" + clazz);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (Objects.equals(XmlPublicationTags.MAGAZINE.getName(), qName)
                || Objects.equals(XmlPublicationTags.BOOKLET.getName(), qName)
                || Objects.equals(XmlPublicationTags.NEWSPAPER.getName(), qName)) {
            catalog.add(abstractPublication);
        }

    }
}
