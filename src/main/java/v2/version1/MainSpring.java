package v2.version1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by innopolis on 26.12.2016.
 */
public class MainSpring {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        System.out.println("Ресурсы для анализа");
        ParseFile file = (ParseFile) context.getBean("parserFile");
        new Thread(file).start();


    }
}
