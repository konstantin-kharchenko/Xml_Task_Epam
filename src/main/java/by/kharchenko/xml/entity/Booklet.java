package by.kharchenko.xml.entity;


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

    public static class BookletBuilder extends AbstractPublicationBuilder<Booklet> {

        public BookletBuilder() {
            this.publication = new Booklet();
        }
    }
}
