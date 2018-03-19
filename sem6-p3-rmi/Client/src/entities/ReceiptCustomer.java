package entities;

import java.io.Serializable;

/**
 *<p>Класс-сущность, предоставляющая информацию о клиенте, который заказал услугу.</p>
 *
 * @author Polischuk Alexander
 * @version 1.0
 */
public class ReceiptCustomer implements Serializable{
    private int id;
    private String name;
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
