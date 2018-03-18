package entities;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * Created by User on 20.02.2018.
 */
@XmlRootElement(name = "ReceiptService")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {"id", "name"})

public class ReceiptService implements Serializable {

    @XmlAttribute(required = true)
    private int id;

    @XmlElement(name = "servicename")
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
        return "MySQLService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
