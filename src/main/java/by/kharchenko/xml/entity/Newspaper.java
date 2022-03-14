package by.kharchenko.xml.entity;

import java.util.Objects;
public class Newspaper extends AbstractPublication{
    private int subscription_index;

    public int getSubscription_index() {
        return subscription_index;
    }

    public void setSubscription_index(int subscription_index) {
        this.subscription_index = subscription_index;
    }

    public Newspaper() {
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                "title='" + title + '\'' +
                ", monthly=" + monthly +
                ", color=" + color +
                ", pages=" + pages +
                ", PublicationId='" + PublicationId + '\'' +
                ", date=" + date +
                ", subscription_index=" + subscription_index +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Newspaper newspaper = (Newspaper) o;
        return subscription_index == newspaper.subscription_index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscription_index);
    }
}
