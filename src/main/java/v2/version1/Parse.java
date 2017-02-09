package v2.version1;

/**
 * Created by Igor Ryabtsev on 16.12.2016.
 * Интерфейс определяет единственный метод,
 * который предназначен для синтаксического анализа
 * данных
 */

public interface Parse {
    /**
     * @param str Принимает строку текста для проверки на соответствие шаблону
     * @return <tt>true</tt> если синтаксический анализ прошел успешно
     */
    boolean parseWords(String str) ;
}
