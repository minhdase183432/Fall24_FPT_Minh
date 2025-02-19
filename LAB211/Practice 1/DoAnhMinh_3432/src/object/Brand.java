package object;

public class Brand {

    private String id;
    private String name;
    private String origin;

    public Brand(String id, String name, String origin) {
        this.id = id;
        this.name = name;
        this.origin = origin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

}
