package examples.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by vaa25 on 07.03.2015.
 */
public class Hello {
    private String s;
    private String who;

    public Hello(String str) {
        s = str;
    }

    public static void main(String[] args) {
        //показываем Spring где лежит файл конфигурации
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring-config.xml"});
        //указываем id нашего bean-а
        Hello h = (Hello) ac.getBean("hello");
        h.sayHi();
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public void sayHi() {
        System.out.println(s);
    }
}