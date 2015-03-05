package examples.logic;

import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Alexander Vlasov
 */
public class Message {
    private static Logger log = Logger.getLogger(Message.class.getName());
    private Person person;
    private String text;
    private Enumeration special;
    private List<Person> personList;
    private Duel duel;

    public Message() {
    }

    public Message(String text) {
        log.info("New Message created: " + text);
        this.text = text;
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

    public Duel getDuel() {
        return duel;
    }

    public Message setDuel(Duel duel) {
        this.duel = duel;
        return this;
    }
}
