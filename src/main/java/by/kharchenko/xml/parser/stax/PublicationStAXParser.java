package by.kharchenko.xml.parser.stax;

import by.kharchenko.xml.entity.*;
import by.kharchenko.xml.parser.XmlPublicationTags;
import by.kharchenko.xml.parser.sax.PublicationSAXParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final Logger LOGGER = LogManager.getLogger(PublicationSAXParser.class);
    private final Papers catalog;
    private final XMLInputFactory inputFactory;

    public PublicationStAXParser() {
        inputFactory = XMLInputFactory.newInstance();
        catalog = new Papers();
    }

    public Papers getCatalog() {
        return catalog;
    }

    public void buildCatalog(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
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
            LOGGER.log(Level.ERROR, "StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.ERROR, "File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, "Impossible close file " + fileName + " : " + e);
            }
        }
    }

    private AbstractPublication buildMagazine(XMLStreamReader reader) throws XMLStreamException {
        Magazine magazine = new Magazine();
        publicationInitialization(magazine, reader);
        return magazine;
    }

    private AbstractPublication buildBooklet(XMLStreamReader reader) throws XMLStreamException {
        Booklet booklet = new Booklet();
        publicationInitialization(booklet, reader);
        return booklet;
    }

    private AbstractPublication buildNewspaper(XMLStreamReader reader) throws XMLStreamException {
        Newspaper newspaper = new Newspaper();
        publicationInitialization(newspaper, reader);
        return newspaper;
    }


    private void publicationInitialization(AbstractPublication publication, XMLStreamReader reader) throws XMLStreamException {

        publication.setId(reader.getAttributeValue(null, XmlPublicationTags.ID.getName()));
        publication.setGlossy(reader.getAttributeValue(null, XmlPublicationTags.GLOSSY.getName()));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (Objects.requireNonNull(XmlPublicationTags.getXmlPublicationWord(name))) {
                        case TITLE -> publication.setTitle(getXMLText(reader));
                        case PAGES -> publication.setPages(Integer.parseInt(getXMLText(reader)));
                        case DATE -> publication.setDate(LocalDateTime.parse(getXMLText(reader)));
                        case COLOR -> publication.setColor(Boolean.parseBoolean(getXMLText(reader)));
                        case MONTHLY -> publication.setMonthly(Boolean.parseBoolean(getXMLText(reader)));
                    }
                    if (Objects.equals(name, XmlPublicationTags.SUBSCRIPTION_INDEX.getName())) {
                        if (publication instanceof Newspaper) {
                            ((Newspaper) publication).setSubscription_index(Integer.parseInt(getXMLText(reader)));
                        }
                        if (publication instanceof Magazine) {
                            ((Magazine) publication).setSubscription_index(Integer.parseInt(getXMLText(reader)));
                        }
                    }
                    break;
            }
            if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName();
                if (Objects.equals(name, XmlPublicationTags.NEWSPAPER.getName())
                        || Objects.equals(name, XmlPublicationTags.MAGAZINE.getName())
                        || Objects.equals(name, XmlPublicationTags.BOOKLET.getName())) {
                    break;
                }
            }
        }
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
