package by.kharchenko.xml.entity;

import java.util.ArrayList;
import java.util.List;

public class Papers {

    private List<AbstractPublication> publications;

    public List<AbstractPublication> getPublications() {
        return new ArrayList<>(publications);
    }

    public void setPublications(List<AbstractPublication> publications) {
        this.publications = publications;
    }

    public Papers() {
        publications = new ArrayList<>();
    }

    public void add(AbstractPublication publication) {
        publications.add(publication);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "publications=" + publications +
                '}';
    }
}
