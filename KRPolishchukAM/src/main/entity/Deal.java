package main.entity;

import java.time.LocalDate;

public class Deal {
    private int id;
    private String type;
    private String name;
    private LocalDate dateOfDeal;
    private int sum;

    public Deal() {
    }

    public Deal(String type, String name, LocalDate dateOfDeal, int sum) {
        this.type = type;
        this.name = name;
        this.dateOfDeal = dateOfDeal;
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfDeal() {
        return dateOfDeal;
    }

    public void setDateOfDeal(LocalDate dateOfDeal) {
        this.dateOfDeal = dateOfDeal;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", dateOfDeal=" + dateOfDeal +
                ", sum=" + sum +
                '}';
    }
}
