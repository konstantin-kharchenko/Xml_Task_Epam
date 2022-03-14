package by.kharchenko.xml.parser.sax;


import by.kharchenko.xml.entity.*;
import by.kharchenko.xml.parser.XmlPublicationTags;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.time.LocalDateTime;
import java.util.Objects;

public class PublicationSAXParser extends DefaultHandler {

    public Papers getCatalog() {
        return catalog;
    }

    private Papers catalog;
    private Booklet booklet;
    private Magazine magazine;
    private Newspaper newspaper;
    private String tag = null;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String id = attributes.getValue(0);
        String glossy = attributes.getValue(1);
        if (Objects.equals(XmlPublicationTags.PAPERS.getName(), qName)) {
            catalog = new Papers();
            tag = null;
        } else if (Objects.equals(XmlPublicationTags.MAGAZINE.getName(), qName)) {
            magazine = new Magazine();
            magazine.setId(id);
            magazine.setGlossy(glossy);
            tag = null;
            booklet = null;
            newspaper = null;
        } else if (Objects.equals(XmlPublicationTags.BOOKLET.getName(), qName)) {
            booklet = new Booklet();
            booklet.setId(id);
            booklet.setGlossy(glossy);
            tag = null;
            newspaper = null;
            magazine = null;
        } else if (Objects.equals(XmlPublicationTags.NEWSPAPER.getName(), qName)) {
            newspaper = new Newspaper();
            newspaper.setId(id);
            tag = null;
            booklet = null;
            magazine = null;
        } else {
            tag = qName;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        String s = new String(ch, start, length);

        if (tag != null && !s.contains("\n")) {
            if (Objects.equals(XmlPublicationTags.COLOR.getName(), tag)) {
                if (magazine != null) {
                    magazine.setColor(Boolean.parseBoolean(s));
                } else if (booklet != null) {
                    booklet.setColor(Boolean.parseBoolean(s));
                } else if (newspaper != null) {
                    newspaper.setColor(Boolean.parseBoolean(s));
                }
            }
            else if (Objects.equals(XmlPublicationTags.TITLE.getName(), tag)) {
                if (magazine != null) {
                    magazine.setTitle(s);
                } else if (booklet != null) {
                    booklet.setTitle(s);
                } else if (newspaper != null) {
                    newspaper.setTitle(s);
                }
            }
            else if (Objects.equals(XmlPublicationTags.MONTHLY.getName(), tag)) {
                if (magazine != null) {
                    magazine.setMonthly(Boolean.parseBoolean(s));
                } else if (booklet != null) {
                    booklet.setMonthly(Boolean.parseBoolean(s));
                } else if (newspaper != null) {
                    newspaper.setMonthly(Boolean.parseBoolean(s));
                }
            }
            else if (Objects.equals(XmlPublicationTags.PAGES.getName(), tag)) {
                if (magazine != null) {
                    magazine.setPages(Integer.parseInt(s));
                } else if (booklet != null) {
                    booklet.setPages(Integer.parseInt(s));
                } else if (newspaper != null) {
                    newspaper.setPages(Integer.parseInt(s));
                }
            }
            else if (Objects.equals(XmlPublicationTags.DATE.getName(), tag)) {
                if (magazine != null) {
                    LocalDateTime dateTime = LocalDateTime.parse(s);
                    magazine.setDate(dateTime);
                } else if (booklet != null) {
                    LocalDateTime dateTime = LocalDateTime.parse(s);
                    booklet.setDate(dateTime);
                } else if (newspaper != null) {
                    LocalDateTime dateTime = LocalDateTime.parse(s);
                    newspaper.setDate(dateTime);
                }
            }
            else if (Objects.equals(XmlPublicationTags.SUBSCRIPTION_INDEX.getName(), tag)) {
                if (magazine != null) {
                    magazine.setSubscription_index(Integer.parseInt(s));
                } else if (newspaper != null) {
                    newspaper.setSubscription_index(Integer.parseInt(s));
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (Objects.equals(XmlPublicationTags.MAGAZINE.getName(), qName)) {
            catalog.add(magazine);
        } else if (Objects.equals(XmlPublicationTags.BOOKLET.getName(), qName)) {
            catalog.add(booklet);
        } else if (Objects.equals(XmlPublicationTags.NEWSPAPER.getName(), qName)) {
            catalog.add(newspaper);
        }

    }

    @Override
    public void endDocument() throws SAXException {

    }
}
