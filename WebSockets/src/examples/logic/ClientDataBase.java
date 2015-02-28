package examples.logic;

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

    public void sendToAll(Message message) {
        for (ClientData clientData : map.values()) {
            clientData.sendMessage(message);
        }
    }

    public ClientDataBase sendToAllExceptId(String id, Message message) {
        for (ClientData clientData : map.values()) {
            if (!id.equals(clientData.getId())) {
                clientData.sendMessage(message);
            }
        }
        return this;
    }

    public ClientDataBase sendToId(String id, Message message) {
        map.get(id).sendMessage(message);
        return this;
    }


}
