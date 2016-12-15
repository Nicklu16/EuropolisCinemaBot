import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CinemaParser {
    public static void main(String[] args) {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.mirage.ru/schedule/raspisanie.htm").get();
            Elements newsHeadlines = doc.select("td.col2 a.red");
            for (Element e : newsHeadlines) {
                System.out.println(e.html());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
