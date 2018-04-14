package beans;

/**
 * Created by User on 08.04.2018.
 */
public class Receipt {
    private String service;
    private boolean quick;
    private String name;
    private String surname;
    private String street;

    public Receipt() {
    }


    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public boolean isQuick() {
        return quick;
    }

    public void setQuick(boolean quick) {
        this.quick = quick;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "service='" + service + '\'' +
                ", quick=" + quick +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
