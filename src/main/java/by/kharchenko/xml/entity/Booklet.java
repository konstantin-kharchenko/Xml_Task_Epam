package by.kharchenko.xml.entity;


import java.time.LocalDateTime;

public class Booklet extends AbstractPublication {

    public Booklet() {
    }

    @Override
    public String toString() {
        return "Booklet{" +
                "title='" + title + '\'' +
                ", monthly=" + monthly +
                ", color=" + color +
                ", pages=" + pages +
                ", PublicationId='" + publicationId + '\'' +
                ", date=" + date +
                ", glossy='" + glossy + '\'' +
                '}';
    }

    public static class BookletBuilder{
        private Booklet booklet;

        public BookletBuilder() {
            booklet = new Booklet();
        }

        public BookletBuilder(Booklet booklet) {
            this.booklet = booklet;
        }

        public BookletBuilder withColor(boolean color){
            booklet.color= color;
            return this;
        }

        public BookletBuilder withMonthly(boolean monthly){
            booklet.monthly= monthly;
            return this;
        }

        public BookletBuilder withPages(int pages){
            booklet.pages= pages;
            return this;
        }

        public BookletBuilder withID(String id){
            booklet.publicationId= id;
            return this;
        }

        public BookletBuilder withDate(LocalDateTime date){
            booklet.date= date;
            return this;
        }

        public BookletBuilder withGlossy(String glossy){
            booklet.glossy= glossy;
            return this;
        }

        public BookletBuilder withTitle(String title){
            booklet.title= title;
            return this;
        }

        public Booklet build(){
            return booklet;
        }
    }
}
