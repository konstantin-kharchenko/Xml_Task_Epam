package by.kharchenko.xml.entity;


public class Booklet extends AbstractPublication{

    public Booklet() {
    }

    @Override
    public String toString() {
        return "Booklet{" +
                "title='" + title + '\'' +
                ", monthly=" + monthly +
                ", color=" + color +
                ", pages=" + pages +
                ", PublicationId='" + PublicationId + '\'' +
                ", date=" + date +
                ", glossy='" + glossy + '\'' +
                '}';
    }
}
