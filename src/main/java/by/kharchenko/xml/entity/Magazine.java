package by.kharchenko.xml.entity;

import java.util.Objects;


public class Magazine extends AbstractPublication {

    private int subscription_index;

    public Magazine() {
    }

    public int getSubscription_index() {
        return subscription_index;
    }

    public void setSubscription_index(int subscription_index) {
        this.subscription_index = subscription_index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Magazine magazine = (Magazine) o;
        return subscription_index == magazine.subscription_index && Objects.equals(glossy, magazine.glossy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscription_index, glossy);
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "title='" + title + '\'' +
                ", monthly=" + monthly +
                ", color=" + color +
                ", pages=" + pages +
                ", PublicationId='" + publicationId + '\'' +
                ", date=" + date +
                ", subscription_index=" + subscription_index +
                ", glossy='" + glossy + '\'' +
                '}';
    }

    public static class MagazineBuilder extends AbstractPublicationBuilder<Magazine> {
        public MagazineBuilder() {
            this.publication = new Magazine();
        }

        public MagazineBuilder(Magazine publication) {
            super(publication);
        }

        public MagazineBuilder withSubscriptionIndex(int index) {
            this.publication.subscription_index = index;
            return this;
        }


    }

}
