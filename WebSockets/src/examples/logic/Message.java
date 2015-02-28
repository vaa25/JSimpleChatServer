package examples.logic;

import java.util.Enumeration;
import java.util.List;

/**
 * @author Alexander Vlasov
 */
public class Message {
    private Person person;
    private String text;
    private Enumeration special;
    private List<Person> personList;

    public Message(String text) {
        this.text = text;
        person = new Person("default");
        special = null;
        personList = null;
    }

    public Person getPerson() {
        return person;
    }

    public Message setPerson(Person value) {
        person = value;
        return this;
    }

    public String getText() {
        return text;
    }

    public Message setText(String text) {
        this.text = text;
        return this;
    }

    public Enumeration getSpecial() {
        return special;
    }

    public Message setSpecial(Enumeration special) {
        this.special = special;
        return this;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public Message setPersonList(List<Person> personList) {
        this.personList = personList;
        return this;
    }
}
