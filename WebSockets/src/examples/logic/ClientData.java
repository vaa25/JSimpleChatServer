package examples.logic;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;

/**
 * Created by vaa25 on 22.02.2015.
 */
public class ClientData {
    private Person person;
    private Session session;

    public ClientData(String id) {
        person = new Person();
        person.setId(id);
    }

    public ClientData addName(String value) {
        person.setName(value);
        return this;
    }

    public ClientData addSession(Session value) {
        session = value;
        return this;
    }

    public String getId() {
        return person.getId();
    }

    public String getName() {
        return person.getName();
    }

    public boolean sendMessage(Message message) {
        try {
            System.out.println(message.getPerson().getName() + " to " + getName() + " message ->" +
                    new Gson().toJson(message));
            session.getRemote().sendString(new Gson().toJson(message));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Person getPerson() {
        return person;
    }
}
