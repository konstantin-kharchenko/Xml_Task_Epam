package by.kharchenko.xml.entity;


import java.time.LocalDateTime;

public abstract class AbstractPublication {

    protected String PublicationId;
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
        return PublicationId;
    }

    public void setId(String id) {
        PublicationId = id;
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
                "id='" + PublicationId + '\'' +
                "title='" + title + '\'' +
                ", monthly=" + monthly +
                ", color=" + color +
                ", pages=" + pages +
                ", date=" + date +
                '}';
    }

}
