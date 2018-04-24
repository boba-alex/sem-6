package main.entities;

/**
 * Created by User on 20.02.2018.
 */
public class ReceiptService {

    private int id;
    private String name;

    public ReceiptService() {
    }

    public ReceiptService(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
