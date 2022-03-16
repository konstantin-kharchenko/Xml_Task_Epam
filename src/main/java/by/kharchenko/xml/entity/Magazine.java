package by.kharchenko.xml.entity;

import java.time.LocalDateTime;
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

    public static class MagazineBuilder{
        private Magazine magazine;

        public MagazineBuilder() {
            magazine = new Magazine();
        }

        public MagazineBuilder(Magazine magazine) {
            this.magazine = magazine;
        }

        public MagazineBuilder withSubscriptionIndex(int index){
            magazine.subscription_index= index;
            return this;
        }

        public MagazineBuilder withColor(boolean color){
            magazine.color= color;
            return this;
        }

        public MagazineBuilder withMonthly(boolean monthly){
            magazine.monthly= monthly;
            return this;
        }

        public MagazineBuilder withPages(int pages){
            magazine.pages= pages;
            return this;
        }

        public MagazineBuilder withID(String id){
            magazine.publicationId= id;
            return this;
        }

        public MagazineBuilder withDate(LocalDateTime date){
            magazine.date= date;
            return this;
        }

        public MagazineBuilder withGlossy(String glossy){
            magazine.glossy= glossy;
            return this;
        }

        public MagazineBuilder withTitle(String title){
            magazine.title= title;
            return this;
        }

        public Magazine build(){
            return magazine;
        }
    }

}
