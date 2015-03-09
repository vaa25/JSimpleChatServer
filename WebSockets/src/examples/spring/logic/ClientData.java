package examples.spring.logic;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by vaa25 on 22.02.2015.
 */
public class ClientData {
    private static Logger log = Logger.getLogger(ClientData.class.getName());
    private Person person;
    private WebSocketSession springSession;

    public ClientData(String id) {
        log.info("New ClientData(" + id + ") created");
        person = new Person();
        person.setId(id);
    }

    public ClientData addName(String value) {
        person.setName(value);
        return this;
    }

    public ClientData addSession(WebSocketSession value) {
        springSession = value;
        return this;
    }

    public String getId() {
        return person.getId();
    }

    public boolean sendJson(String json) {
        try {
            if (springSession != null) {
                springSession.sendMessage(new TextMessage(json));
            }
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
                '}';
    }
}
