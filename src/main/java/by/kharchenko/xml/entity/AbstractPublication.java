package by.kharchenko.xml.entity;


import java.time.LocalDateTime;
import java.util.Objects;

public abstract class AbstractPublication {

    protected String publicationId;
    protected String title;
    protected boolean monthly;
    protected boolean color;
    protected int pages;
    protected String glossy;

    public String getGlossy() {
        return glossy;
    }

    public void setGlossy(String glossy) {
        this.glossy = glossy;
    }

    protected LocalDateTime date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isMonthly() {
        return monthly;
    }

    public void setMonthly(boolean monthly) {
        this.monthly = monthly;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getId() {
        return publicationId;
    }

    public void setId(String id) {
        publicationId = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public AbstractPublication() {
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id='" + publicationId + '\'' +
                "title='" + title + '\'' +
                ", monthly=" + monthly +
                ", color=" + color +
                ", pages=" + pages +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPublication that = (AbstractPublication) o;
        return monthly == that.monthly && color == that.color && pages == that.pages && publicationId.equals(that.publicationId) && title.equals(that.title) && glossy.equals(that.glossy) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicationId, title, monthly, color, pages, glossy, date);
    }
}
