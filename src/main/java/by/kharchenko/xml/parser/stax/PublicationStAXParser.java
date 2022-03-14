package by.kharchenko.xml.parser.stax;

import by.kharchenko.xml.entity.*;
import by.kharchenko.xml.parser.XmlPublicationTags;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

public class PublicationStAXParser {

    private Papers catalog;
    private XMLInputFactory inputFactory;

    public PublicationStAXParser() {
        inputFactory = XMLInputFactory.newInstance();
        catalog = new Papers();
    }

    public Papers getCatalog() {
        return catalog;
    }

    public void buildCatalog(String fileName) throws XMLStreamException, FileNotFoundException {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (Objects.equals(name, XmlPublicationTags.NEWSPAPER.getName())) {
                        var a = buildNewspaper(reader);
                        catalog.add(a);
                    }
                    if (Objects.equals(name, XmlPublicationTags.MAGAZINE.getName())) {
                        var a = buildMagazine(reader);
                        catalog.add(a);
                    }
                    if (Objects.equals(name, XmlPublicationTags.BOOKLET.getName())) {
                        var a = buildBooklet(reader);
                        catalog.add(a);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            System.err.println("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println("File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Impossible close file " + fileName + " : " + e);
            }
        }
    }

    private AbstractPublication buildMagazine(XMLStreamReader reader) throws XMLStreamException {
        Magazine magazine = new Magazine();
        magazine.setId(reader.getAttributeValue(null, XmlPublicationTags.ID.getName()));
        magazine.setGlossy(reader.getAttributeValue(null, XmlPublicationTags.GLOSSY.getName()));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (Objects.requireNonNull(XmlPublicationTags.getXmlPublicationWord(name))) {
                        case TITLE:
                            magazine.setTitle(getXMLText(reader));
                            break;
                        case PAGES:
                            magazine.setPages(Integer.parseInt(getXMLText(reader)));
                            break;
                        case SUBSCRIPTION_INDEX:
                            magazine.setSubscription_index(Integer.parseInt(getXMLText(reader)));
                            break;
                        case DATE:
                            magazine.setDate(LocalDateTime.parse(getXMLText(reader)));
                            break;
                        case COLOR:
                            magazine.setColor(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case MONTHLY:
                            magazine.setMonthly(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (Objects.equals(name, XmlPublicationTags.MAGAZINE.getName())) {
                        return magazine;
                    }
                    break;
            }
        }
        return null;
    }

    private AbstractPublication buildBooklet(XMLStreamReader reader) throws XMLStreamException {
        Booklet booklet = new Booklet();
        booklet.setId(reader.getAttributeValue(null, XmlPublicationTags.ID.getName()));
        booklet.setGlossy(reader.getAttributeValue(null, XmlPublicationTags.GLOSSY.getName()));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (Objects.requireNonNull(XmlPublicationTags.getXmlPublicationWord(name))) {
                        case TITLE:
                            booklet.setTitle(getXMLText(reader));
                            break;
                        case PAGES:
                            booklet.setPages(Integer.parseInt(getXMLText(reader)));
                            break;
                        case DATE:
                            booklet.setDate(LocalDateTime.parse(getXMLText(reader)));
                            break;
                        case COLOR:
                            booklet.setColor(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case MONTHLY:
                            booklet.setMonthly(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (Objects.equals(name, XmlPublicationTags.BOOKLET.getName())) {
                        return booklet;
                    }
                    break;
            }
        }
        return null;
    }

    private AbstractPublication buildNewspaper(XMLStreamReader reader) throws XMLStreamException {
        Newspaper newspaper = new Newspaper();
        newspaper.setId(reader.getAttributeValue(null, XmlPublicationTags.ID.getName()));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (Objects.requireNonNull(XmlPublicationTags.getXmlPublicationWord(name))) {
                        case TITLE:
                            newspaper.setTitle(getXMLText(reader));
                            break;
                        case PAGES:
                            newspaper.setPages(Integer.parseInt(getXMLText(reader)));
                            break;
                        case SUBSCRIPTION_INDEX:
                            newspaper.setSubscription_index(Integer.parseInt(getXMLText(reader)));
                            break;
                        case DATE:
                            newspaper.setDate(LocalDateTime.parse(getXMLText(reader)));
                            break;
                        case COLOR:
                            newspaper.setColor(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case MONTHLY:
                            newspaper.setMonthly(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (Objects.equals(name, XmlPublicationTags.NEWSPAPER.getName())) {
                        return newspaper;
                    }
                    break;
            }
        }
        return null;
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
