package entities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by User on 20.02.2018.
 */
@XmlRootElement
public class ReceiptService implements Serializable{

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

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
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
