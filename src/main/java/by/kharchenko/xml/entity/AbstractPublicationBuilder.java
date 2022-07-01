package by.kharchenko.xml.entity;

import java.time.LocalDateTime;

public abstract class AbstractPublicationBuilder<T extends AbstractPublication> {
    protected T publication;

    public AbstractPublicationBuilder(T publication) {
        this.publication = publication;
    }

    public AbstractPublicationBuilder() {
    }

    public AbstractPublicationBuilder<T> withColor(boolean color) {
        publication.color = color;
        return this;
    }

    public AbstractPublicationBuilder<T> withMonthly(boolean monthly) {
        publication.monthly = monthly;
        return this;
    }

    public AbstractPublicationBuilder<T> withPages(int pages) {
        publication.pages = pages;
        return this;
    }

    public AbstractPublicationBuilder<T> withID(String id) {
        publication.publicationId = id;
        return this;
    }

    public AbstractPublicationBuilder<T> withDate(LocalDateTime date) {
        publication.date = date;
        return this;
    }

    public AbstractPublicationBuilder<T> withGlossy(String glossy) {
        publication.glossy = glossy;
        return this;
    }

    public AbstractPublicationBuilder<T> withTitle(String title) {
        publication.title = title;
        return this;
    }

    public T build() {
        return publication;
    }
}
