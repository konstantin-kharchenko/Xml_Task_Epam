package by.kharchenko.xml.parser;

import java.util.Objects;

public enum XmlPublicationTags {

    PAPERS("papers"),
    NEWSPAPER("newspaper"),
    BOOKLET("booklet"),
    MAGAZINE("magazine"),
    TITLE("title"),
    MONTHLY("monthly"),
    COLOR("color"),
    PAGES("pages"),
    DATE("date"),
    ID("id"),
    GLOSSY("glossy"),
    SUBSCRIPTION_INDEX("subscription-index");

    private String name;

    public String getName() {
        return name;
    }

    XmlPublicationTags(String name) {
        this.name = name;
    }

    public static XmlPublicationTags getXmlPublicationWord(String name) {
        for (XmlPublicationTags word : XmlPublicationTags.values()) {
            if (Objects.equals(word.name, name)) return word;
        }
        return null;
    }


}
