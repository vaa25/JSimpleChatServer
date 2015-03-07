package examples.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by vaa25 on 07.03.2015.
 */

public class Country {
    @Autowired
    @Qualifier("moskow")
    private Capital capital;

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring-config.xml"});
//        Country russia = (Country) ac.getBean("country");
//        System.out.println(russia.getCapital().getName());
    }

    public Capital getCapital() {
        return capital;
    }

    public void setCapital(Capital capital) {
        this.capital = capital;
    }

    @Autowired
    public void print() {
        System.out.println(capital.getName());
    }
}
