package parser;

import by.kharchenko.xml.entity.AbstractPublication;
import by.kharchenko.xml.entity.Booklet;
import by.kharchenko.xml.entity.Magazine;
import by.kharchenko.xml.entity.Newspaper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReferencePublication {

    private static List<AbstractPublication> publicationList = new ArrayList<>();

    public static List<AbstractPublication> getPublicationList() {
        AbstractPublication publication = new Newspaper.NewspaperBuilder().withSubscriptionIndex(5).withColor(false).withMonthly(false).withTitle("Гродненская правда")
                .withDate(LocalDateTime.parse("2002-05-30T09:00:00")).withID("P1").withGlossy("нет глянца(это газета)").withPages(21).build();
        ;
        publicationList.add(publication);
        publication = new Booklet.BookletBuilder().withColor(false).withMonthly(false).withTitle("остров чистоты")
                .withDate(LocalDateTime.parse("2002-05-30T09:00:00")).withID("P2").withGlossy("глянец на буклете").withPages(34).build();

        publicationList.add(publication);
        publication = new Magazine.MagazineBuilder().withSubscriptionIndex(56).withColor(false).withMonthly(false).withTitle("остров чистоты")
                .withDate(LocalDateTime.parse("2002-05-30T09:00:00")).withID("P3").withGlossy("глянец на журнале")
                .withPages(34).build();
        publicationList.add(publication);

        return publicationList;
    }
}
