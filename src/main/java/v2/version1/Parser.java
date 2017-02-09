package v2.version1;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Igor Ryabtsev on 16.12.2016.
 * Класс переопределяет метод,
 * который предназначен для синтаксического анализа
 * текста
 */
@Component
public class Parser implements Parse{

    private Matcher matcher;

    private Pattern pattern;

    public Parser(String regex) {
        pattern = Pattern.compile(regex);
    }

    public Parser() {
    }

    @Override
    public boolean parseWords(String str) {

        matcher = pattern.matcher(str);

       return matcher.matches() ? true : false;
    }

}
