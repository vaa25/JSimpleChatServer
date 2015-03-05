package examples.logic;

import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by vaa25 on 22.02.2015.
 */
public class ClientDataBase {
    public static final ClientDataBase INSTANCE = new ClientDataBase();
    private ConcurrentHashMap<String, ClientData> map;
    private List<Person> personList;

    private ClientDataBase() {
        this.personList = new CopyOnWriteArrayList<>();
        map = new ConcurrentHashMap<>();
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public ClientDataBase add(ClientData clientData) {
        personList.add(clientData.getPerson());
        map.put(clientData.getId(), clientData);
        return this;
    }

    public Person removeById(String id) {
        Person result = map.remove(id).getPerson();
        personList.remove(result);
        return result;
    }

    public ClientDataBase sendToAll(Message message) {
        String json = new Gson().toJson(message);
        for (ClientData clientData : map.values()) {
            clientData.sendJson(json);
        }
        return this;
    }

    public ClientDataBase sendToAllExceptId(String id, Message message) {
        String json = new Gson().toJson(message);
        for (ClientData clientData : map.values()) {
            if (!id.equals(clientData.getId())) {
                clientData.sendJson(json);
            }
        }
        return this;
    }

    public ClientDataBase sendToId(String id, Message message) {
        map.get(id).sendJson(new Gson().toJson(message));
        return this;
    }


    public int size() {
        return map.size();
    }
}
