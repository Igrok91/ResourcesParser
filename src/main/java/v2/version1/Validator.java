package v2.version1;

/**
 * Created by Igor Ryabtsev on 16.12.2016.
 * Интерфейс определяет единственный метод,
 * который предназначен для  анализа
 * текстовых данных
 */
public interface Validator {

    /**
     * @param str Слово, которое будет проверено
     * @return  <tt>true</tt> Если проверки прошла успешно
     */
     boolean validate(String str);
}
