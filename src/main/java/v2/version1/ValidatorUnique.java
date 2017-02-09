package v2.version1;


import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Igor Ryabtsev on 16.12.2016.
 * Класс переопределяет метод,
 * который предназначен для проверки слова на уникальность
 */
@Component
public class ValidatorUnique implements Validator{

    String[] words;

    private Set<String> set;

    public ValidatorUnique() {
        set = new HashSet<>();
    }


    /**
     * @param str Слово, которое будет проверено на уникальность
     * @param set Коллекция уникальных значений для проверки уникальности слова
     * @return <tt>true</tt> Если в коллекцию уникальных значений было добавлено слово
     */
    private boolean validateUnique(String str, Set set)  {
        return set.add(str) ? true : false;
    }


    @Override
    public boolean validate(String str) {
        words = str.split("[\\p{Punct}\\s]+");
        for (String s : words) {
            if (!validateUnique(s.toLowerCase(), set)){
                return false;
            }
        }
        return true;
    }
}
