package parser;

import by.kharchenko.xml.entity.AbstractPublication;
import by.kharchenko.xml.entity.Booklet;
import by.kharchenko.xml.entity.Magazine;
import by.kharchenko.xml.entity.Newspaper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*<newspaper id="P1" glossy="нет глянца(это газета)">
<title>Гродненская правда</title>
<monthly>false</monthly>
<color>false</color>
<pages>21</pages>
<date>2002-05-30T09:00:00</date>
<subscription-index>5</subscription-index>
</newspaper>
<booklet id="P2" glossy="глянец на буклете">
        <title>остров чистоты</title>
        <monthly>false</monthly>
        <color>false</color>
        <pages>34</pages>
        <date>2002-05-30T09:00:00</date>
    </booklet>
<magazine id="P3" glossy="глянец на журнале">
        <title>остров чистоты</title>
        <monthly>false</monthly>
        <color>false</color>
        <pages>34</pages>
        <date>2002-05-30T09:00:00</date>
        <subscription-index>56</subscription-index>
    </magazine>
*/

public class ReferencePublication {

    private static List<AbstractPublication> publicationList = new ArrayList<>();

    public static List<AbstractPublication> getPublicationList(){
        AbstractPublication publication = new Newspaper.NewspaperBuilder().withColor(false).withMonthly(false).withTitle("Гродненская правда")
                .withDate(LocalDateTime.parse("2002-05-30T09:00:00")).withID("P1").withGlossy("нет глянца(это газета)").withPages(21)
                .withSubscriptionIndex(5).build();
        publicationList.add(publication);
        publication = new Booklet.BookletBuilder().withColor(false).withMonthly(false).withTitle("остров чистоты")
                .withDate(LocalDateTime.parse("2002-05-30T09:00:00")).withID("P2").withGlossy("глянец на буклете").withPages(34).build();
        publicationList.add(publication);
        publication = new Magazine.MagazineBuilder().withColor(false).withMonthly(false).withTitle("остров чистоты")
                .withDate(LocalDateTime.parse("2002-05-30T09:00:00")).withID("P3").withGlossy("глянец на журнале").withPages(34)
                .withSubscriptionIndex(56).build();
        publicationList.add(publication);

        return publicationList;
    }
}
