package by.kharchenko.xml.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Newspaper extends AbstractPublication {
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
                ", PublicationId='" + publicationId + '\'' +
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

    public static class NewspaperBuilder{
        private Newspaper newspaper;

        public NewspaperBuilder() {
            newspaper = new Newspaper();
        }

        public NewspaperBuilder(Newspaper newspaper) {
            this.newspaper = newspaper;
        }

        public NewspaperBuilder withColor(boolean color){
            newspaper.color= color;
            return this;
        }

        public NewspaperBuilder withMonthly(boolean monthly){
            newspaper.monthly= monthly;
            return this;
        }

        public NewspaperBuilder withPages(int pages){
            newspaper.pages= pages;
            return this;
        }

        public NewspaperBuilder withID(String id){
            newspaper.publicationId= id;
            return this;
        }

        public NewspaperBuilder withDate(LocalDateTime date){
            newspaper.date= date;
            return this;
        }

        public NewspaperBuilder withGlossy(String glossy){
            newspaper.glossy= glossy;
            return this;
        }

        public NewspaperBuilder withTitle(String title){
            newspaper.title= title;
            return this;
        }

        public NewspaperBuilder withSubscriptionIndex(int index){
            newspaper.subscription_index= index;
            return this;
        }

        public Newspaper build(){
            return newspaper;
        }
    }
}
