package examples.logic;

import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by vaa25 on 22.02.2015.
 */
public class ClientData {
    private static Logger log = Logger.getLogger(ClientData.class.getName());
    private Person person;
    private Session session;

    public ClientData(String id) {
        log.info("New ClientData(" + id + ") created");
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

    public boolean sendJson(String json) {
        try {
            session.getRemote().sendString(json);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "ClientData{" +
                "person=" + person +
//                ", session=" + session +
                '}';
    }
}
