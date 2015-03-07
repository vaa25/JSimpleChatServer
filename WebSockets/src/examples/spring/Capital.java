package examples.spring;

/**
 * Created by vaa25 on 07.03.2015.
 */
public class Capital {
    private static Capital instance = null;
    private String name;

    public Capital() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Capital{" +
                "name='" + name + '\'' +
                '}';
    }
}
