package entities;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * Created by User on 20.02.2018.
 */
@XmlRootElement(name = "ReceiptCustomer")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {"id", "name", "surname"})

public class ReceiptCustomer implements Serializable{

    @XmlAttribute(required = true)
    private int id;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "surname")
    private String surname;

    public ReceiptCustomer() {
    }

    public ReceiptCustomer(String name, String surname) {
        this.name = name;
        this.surname = surname;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "ReceiptCustomer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
