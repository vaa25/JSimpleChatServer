package examples.spring.websocket;

import com.google.gson.Gson;
import examples.spring.logic.ClientData;
import examples.spring.logic.ClientDataBase;
import examples.spring.logic.Duel;
import examples.spring.logic.Message;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Logger;

/**
 * Created by vaa25 on 07.03.2015.
 */
public class MyHandler extends AbstractWebSocketHandler {
    private static Logger log = Logger.getLogger(MyHandler.class.getName());
    private ClientDataBase dataBase;

    public MyHandler() {
        super();
        this.dataBase = ClientDataBase.INSTANCE;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        final String id = getIdFromSession(session);
        final String name = getNameFromSession(session);
        log.info("WebSocket " + name + " connected from " + session.getRemoteAddress());

        ClientData clientData = new ClientData(id)
                .addName(name)
                .addSession(session);
        dataBase.add(clientData)
                .sendToAllExceptId(id, new Message(name + " вошел в чат")
                        .setPerson(clientData.getPerson()))
                .sendToId(id, new Message("Вы вошли в чат")
                        .setPerson(clientData.getPerson())
                        .setPersonList(dataBase.getPersonList()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        final String id = getIdFromSession(session);
        final String name = getNameFromSession(session);
        log.info("WebSocket " + name + " closed because of " + status.getReason());
        remove(id, new Message(name + " вышел из чата"));
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) {
        Message message = new Gson().fromJson(textMessage.getPayload(), Message.class);
        if (message.getPerson().isOnline()) {
            if (message.getDuel() != null) {
                Duel duel = message.getDuel();
                if ("request".equals(duel.getStatus())) {
                    dataBase.sendToId(duel.getDestinationId(), message);
                }
            } else {
                dataBase.sendToAll(message.setText(message.getPerson().getName() + ": " + message.getText()));
            }
        } else {
            dataBase.removeById((String) session.getAttributes().get("id"));
        }
    }

    private String getNameFromSession(WebSocketSession session) throws UnsupportedEncodingException {
        return URLDecoder.decode(session.getHandshakeHeaders().getFirst("name"), "UTF-8");
    }

    private String getIdFromSession(WebSocketSession session) {
        return session.getHandshakeHeaders().getFirst("id");
    }


    private void remove(String id, Message message) {
        dataBase.sendToAll(message.setPerson(dataBase.removeById(id).setOnline(false)));
    }
}