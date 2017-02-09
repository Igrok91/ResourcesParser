package v2.version2;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Igor Ryabtsev on 16.12.2016.
 * Класс переопределяет метод,
 * который предназначен для синтаксического анализа
 * текста
 */

public class Parser implements Parse{

    private Matcher matcher;

    private Pattern pattern;

    public Parser(String regex) {
        pattern = Pattern.compile(regex);
    }



    @Override
    public boolean parseInt(String str) {

        matcher = pattern.matcher(str);

       return matcher.matches() ? true : false;
    }

}
