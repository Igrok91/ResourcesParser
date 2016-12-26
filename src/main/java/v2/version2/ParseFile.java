package v2.version2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by Igor Ryabtsev on 16.12.2016
 * Класс определяет метод,
 * который предназначен для синтаксического анализа
 * текста в отдельном потоке, считанного из Файла
 */
@Component
public class ParseFile implements Runnable {

    private static Logger log = LoggerFactory.getLogger(ParseFile.class);

    private String resource;

    private String regex;
    private Service service;
//Ссылка на интерфейс Parse для проверки текста на соответствие шаблону

    private Parse parser;
//Ссылка на интерфейс Validator для проверки слова на уникальность

    private Validator validator;

    /**
     * @param resource Принимает файл который будет подвергаться синтаксическому анализу
     * @param regex Принмает регулярное выражение
     * @param service Принимает экземпляр класса Service, который является общим для всех потоков
     */
    public ParseFile(String resource, String regex, Service service) {
        this.resource = resource;
        this.regex = regex;
        this.service = service;
    }

    public ParseFile() {
    }

    @Override
    public void run() {
        doParse();
    }


    /**
     *  Метод проводит синтаксический анализ текста
     */
    private void doParse() {
        File file = new File(resource);
        if (file.isFile() && file.canRead()) {
            try (BufferedReader reader = new BufferedReader
                    (new InputStreamReader(new FileInputStream(file), "windows-1251"))) {
                String s;
                //parser = new Parser(regex);
               // validator = new ValidatorUnique();
                while ((s = reader.readLine()) != null) {
                    if (service.isStopped()){
                        return;
                    }
                    if (!(parser.parseWords(s) && validator.validate(s))) {
                        service.setStopped(true);
                        service.failMessage(resource);
                    }
                }
            } catch (FileNotFoundException e) {
                log.warn(e.getMessage());
            } catch (IOException e) {
                log.warn(e.getMessage());
            }
        } else {
            System.out.println("Ошибка чтения файла");
        }
    }

    public void setParser(Parse parser) {
        this.parser = parser;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}
