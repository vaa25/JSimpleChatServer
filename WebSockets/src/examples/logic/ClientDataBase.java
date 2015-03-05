package examples.logic;

import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

/**
 * Created by vaa25 on 22.02.2015.
 */
public class ClientDataBase {
    public static final ClientDataBase INSTANCE = new ClientDataBase();
    private static Logger log = Logger.getLogger(ClientDataBase.class.getName());
    private ConcurrentHashMap<String, ClientData> map;
    private List<Person> personList;

    private ClientDataBase() {
        log.info("New ClientDataBase created");
        this.personList = new CopyOnWriteArrayList<>();
        map = new ConcurrentHashMap<>();
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public ClientDataBase add(ClientData clientData) {
        log.info("ClientDataBase adds ClientData: " + clientData.getId());
        personList.add(clientData.getPerson());
        map.put(clientData.getId(), clientData);
        return this;
    }

    public Person removeById(String id) {
        log.info("ClientDataBase removes ClientData: " + id);
        Person result = map.remove(id).getPerson();
        personList.remove(result);
        return result;
    }

    public ClientDataBase sendToAll(Message message) {
        String json = new Gson().toJson(message);
        log.info("ClientDataBase sends to all json: " + json);
        for (ClientData clientData : map.values()) {
            clientData.sendJson(json);
        }
        return this;
    }

    public ClientDataBase sendToAllExceptId(String id, Message message) {
        String json = new Gson().toJson(message);
        log.info("ClientDataBase sends to all except id=" + id + " json: " + json);
        for (ClientData clientData : map.values()) {
            if (!id.equals(clientData.getId())) {
                clientData.sendJson(json);
            }
        }
        return this;
    }

    public ClientDataBase sendToId(String id, Message message) {
        String json = new Gson().toJson(message);
        log.info("ClientDataBase sends to id=" + id + " json: " + json);
        map.get(id).sendJson(json);
        return this;
    }


    public int size() {
        return map.size();
    }
}
