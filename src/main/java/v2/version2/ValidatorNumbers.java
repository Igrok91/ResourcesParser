package v2.version2;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor Ryabtsev on 16.12.2016.
 * Класс переопределяет метод,
 * который предназначен для проверки слова на уникальность
 */
@Component
public class ValidatorNumbers implements Validator{

    String[] number;

    private List<Integer> list;

    public ValidatorNumbers() {
        list = new ArrayList<>();
    }


    /**
     * @param str Слово, которое будет проверено на уникальность
     * @param list Коллекция уникальных значений для проверки уникальности слова
     * @return <tt>true</tt> Если в коллекцию уникальных значений было добавлено слово
     */
    private   boolean printResult(int str, List<Integer> list)  {
        if(str > 0 && str%2 == 0){
            list.add(str);
            System.out.println(" Общая сумма чисел:  " + list.stream().reduce((x,y)->x+y).orElse(0));
        }
        return  true;
    }



    @Override
    public  boolean  validate(String str) {
        number = str.split(" ");
        for (String s : number) {
            if (!printResult(Integer.parseInt(s), list)){
                return false;
            }
        }
        return true;
    }
}
