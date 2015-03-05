package examples;

import com.google.gson.Gson;
import examples.logic.ClientData;
import examples.logic.ClientDataBase;
import examples.logic.Duel;
import examples.logic.Message;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by vaa25 on 25.02.2015.
 */
@WebSocket
public class MyWebSocket {
    private ClientDataBase dataBase;

    public MyWebSocket() {
        this.dataBase = ClientDataBase.INSTANCE;
        System.out.println("MyWebSocket created");
    }

    @OnWebSocketClose
    public void onWebSocketClose(Session session, int statusCode, String reason) throws UnsupportedEncodingException {
        final String id = session.getUpgradeRequest().getHeader("id");
        final String name = getNameFromSession(session);
        System.out.println(name + " closed because of " + reason);
        remove(id, new Message(name + " вышел из чата"));
    }

    @OnWebSocketConnect
    public void onWebSocketConnect(Session session) throws UnsupportedEncodingException {
        final String id = session.getUpgradeRequest().getHeader("id");
        final String name = getNameFromSession(session);
        System.out.println(name + " connected from " + session.getRemoteAddress());
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

    private String getNameFromSession(Session session) throws UnsupportedEncodingException {
        return URLDecoder.decode(session.getUpgradeRequest().getHeader("name"), "UTF-8");
    }

    @OnWebSocketError
    public void onWebSocketError(Throwable cause) {
        cause.printStackTrace(System.err);
    }

    @OnWebSocketMessage
    public void onWebSocketText(Session session, String data) {
        Message message = new Gson().fromJson(data, Message.class);

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
            dataBase.removeById(session.getUpgradeRequest().getHeader("id"));
        }
    }

    private void remove(String id, Message message) {
        dataBase.sendToAll(message.setPerson(dataBase.removeById(id).setOnline(false)));
    }

}
