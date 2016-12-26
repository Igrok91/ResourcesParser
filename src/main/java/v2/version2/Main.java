package v2.version2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


/**
 * Created by Igor Ryabtsev on 16.12.2016.
 * Вариант № 2
 * Задача:
 * Необходимо разработать программу, которая получает на вход список ресурсов,
 * содержащих текст, и проверяет уникальность каждого слова.
 * Каждый ресурс должен быть обработан в отдельном потоке,
 * текст не должен содержать инностранных символов, только кириллица,
 * знаки препинания и цифры. В случае нахождения дубликата,
 * программа должна прекращать выполнение с соответсвующим сообщением.
 * Все ошибки должны быть корректно обработаны, все API покрыто модульными тестами
 */

public class Main {

    public static final String FILE_REGEX = "[а-яА-Я0-9—\\p{Punct}\\s]+";

    public static final String URL_REGEX = "^(https?:\\/\\/)([\\a-z\\.-]+)";

    public static void main(String[] args) {
        Service service = new Service();
        System.out.println("Ресурсы для анализа");

        for (String arg : args) {
            System.out.println(arg);
            parseResource(arg, service);
        }
      if (!service.isStopped()) {
          service.successMessage("Программа заверишла проверку");
        }
    }

    /**
     *
     * @param resource Получает ресурс для синтаксического анализа
     * @param service Принимает экземпляр класса Service, который является общим для всех потоков
     */
    private static void parseResource(String resource, Service service) {
        if (!service.isStopped()) {
            if (resource.matches(URL_REGEX)) {
                new Thread(new ParseUrl(resource, FILE_REGEX, service)).start();
            } else {
                new Thread(new ParseFile(resource, FILE_REGEX, service)).start();
            }
        }
    }
}
