package v2.version2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Igor Ryabtsev on 26.12.2016.
 * Необходимо разработать программу, которая получает на вход список ресурсов,
 * содержащих набор чисел и считает сумму всех положительных четных.
 * Каждый ресурс должен быть обработан в отдельном потоке, набор должен содержать лишь числа,
 * унарный оператор "-" и пробелы. Общая сумма должна отображаться на экране и изменяться в режиме реального времени.
 Запуск потоков реализовать через ссылки на методы, итоговый подсчет суммы через stream API
 */
public class MainSpring {


    private static List<Thread> threads = new ArrayList<>();

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        Service service = (Service) context.getBean("service");
        System.out.println("Ресурсы для анализа");

        for (String arg : args) {
            System.out.println(arg);
            ParseFile file = (ParseFile) context.getBean("parserFile");
            if (!service.isStopped()) {
                file.setResource(arg);
                threads.add(new Thread(file));
            }
        }
        startParser();
        if (!service.isStopped()) {
            service.successMessage("Программа заверишла проверку\n");
        }
    }



    private static  void startParser() {
        threads.stream().forEach(Thread::start);
    }
}
