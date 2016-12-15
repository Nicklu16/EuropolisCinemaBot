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
            Elements newsHeadlines = doc.select("table#innerTable tr");
            // td.col2 a.red
            for (Element e : newsHeadlines) {
                String time = e.select("td.col1").first().text();
                if (time.startsWith("24"))
                    time = time.substring(2);
                String name = e.select("td.col2 a.red").first().html();
                System.out.println(time + " " + name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
