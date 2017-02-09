package v2.version1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Igor Ryabtsev on 16.12.2016.
 * Класс определяет методы для остановки программы
 */
public class Service {
    private static Logger log = LoggerFactory.getLogger(Service.class);

    private volatile boolean stopped = false;

    /**
     * @return Возвращает значение типа boolean
     * для проверки и (или)изменения состояния программы
     */
    public synchronized boolean isStopped() {
        return stopped;
    }

    /**
     * @param stopped Принимает значение типа boolean
     *  для установки состояния программе
     */
    public synchronized void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    /**
     * @param s Принимает имя ресурса, которое содержит не уникальные слова или не соответсвует принятому шаблону
     */
    public  void failMessage(String s ) {
        System.out.printf(" -> текст в %s содержит не уникальные слова или не соответсвует принятому шаблону \n" ,s);
        log.warn("Текст в {} содержит не уникальные слова или не соответсвует принятому шаблону", s);

    }

    /**
     * @param s  Выводит сообщение о завершении программы
     */
    public  void successMessage(String s ) {
        System.out.print(s);
        log.info("Программа заверишла проверку");
    }

}
