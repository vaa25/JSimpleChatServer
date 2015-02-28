package examples.logic;

/**
 * @author Alexander Vlasov
 */
public class Person {
    private String name;
    private boolean online;
    private String id;

    public Person() {
        this("Alex");
    }

    public Person(String name) {
        this.name = name;
        online = true;
        id = "defaultId";
    }

    public String getId() {
        return id;
    }

    public Person setId(String id) {
        this.id = id;
        return this;
    }

    public boolean isOnline() {
        return online;
    }

    public Person setOnline(boolean online) {
        this.online = online;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass()) && id.equals(((Person) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", online=" + online +
                '}';
    }
}
