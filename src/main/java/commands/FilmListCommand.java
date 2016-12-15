package commands;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

import java.io.IOException;

/**
 * Помощь (справка) по всем командам
 */
public class FilmListCommand extends BotCommand {

    private static final String LOGTAG = FilmListCommand.class.getSimpleName();


    public FilmListCommand() {
        super("filmlist", "Список фильмов");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage msg = new SendMessage();
        msg.setChatId(chat.getId().toString());
        msg.enableHtml(true);

        StringBuilder result = new StringBuilder("<b>Список фильмов:</b>\n");

        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.mirage.ru/schedule/raspisanie.htm").get();
            Elements newsHeadlines = doc.select("td.col2 a.red");
            for (Element e : newsHeadlines) {
                result.append(e.html()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        msg.setText(result.toString());


        System.out.println(result);

        try {
            absSender.sendMessage(msg);
        } catch (TelegramApiException e) {
            BotLogger.error(LOGTAG, e);
        }
    }
}