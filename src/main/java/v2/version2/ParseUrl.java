package v2.version2;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;


/**
 * Created by Igor Ryabtsev on 16.12.2016.
 * Класс определяет метод,
 * который предназначен для синтаксического анализа
 * текста в отдельном потоке, полученного через URL
 */
@Component
public class ParseUrl implements Runnable{
    private static Logger log = LoggerFactory.getLogger(ParseUrl.class);

    private String resource;
    private Service service;

    private String regex;
    //Ссылка на интерфейс Parse для проверки текста на соответствие шаблону
    @Autowired
    private Parser parser;
    //Ссылка на интерфейс Validator для проверки слова на уникальность
    @Autowired
    private Validator validator;

    /**
     *
     * @param resource Принимает ресурс который будет подвергаться синтаксическому анализу
     * @param regex Принмает регулярное выражение
     * @param service Принимает экземпляр класса Service, который является общим для всех потоков
     */
    public ParseUrl(String resource, String regex, Service service) {
        this.resource = resource;
        this.service = service;
        this.regex = regex;

    }

    public ParseUrl() {
    }

    @Override
    public void run() {
         doParse();
    }


    /**
     *  Метод проводит синтаксический анализ текста
     */
    private void doParse()  {
        try {
            String text = Jsoup.connect(resource).get().text();
            if (service.isStopped()){
                return;
            }

            //parser = new Parser(regex);
            //validator = new ValidatorUnique();

            if (!(parser.parseWords(text) && validator.validate(text))) {
                service.setStopped(true);
                service.failMessage(resource);
            }
        } catch (MalformedURLException e) {
            log.warn(e.getMessage());
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
    }
}
