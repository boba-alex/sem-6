package main.entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import main.exceptions.MyException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;

/**
 *
 * @author User
 */
public class Receipt implements Serializable, Comparable<Receipt>{
    private int id;
    private int number;
    private String name;
    private String receiptCustomerName;
    private LocalDate date;

    public Receipt(){}
    
    public Receipt(int number, String name, String receiptCustomerName, LocalDate date) throws MyException {
        if(name.equals("")) throw new MyException("Name is empty!");
        if(receiptCustomerName.equals("")) throw new MyException("receiptCustomerName is empty!");
        this.number = number;
        this.name = name;
        this.receiptCustomerName = receiptCustomerName;
        this.date = date;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReceiptCustomerName() {
        return receiptCustomerName;
    }

    public void setReceiptCustomerName(String receiptCustomerName) {
        this.receiptCustomerName = receiptCustomerName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", receiptCustomerName='" + receiptCustomerName + '\'' +
                ", date=" + date +
                '}';
    }


    @Override
    public int compareTo(Receipt o) {
        if(this.getDate().getYear() != o.getDate().getYear())
            return Integer.compare(this.getDate().getYear(), o.getDate().getYear());
        if(this.getDate().getMonth() != o.getDate().getMonth())
            return Integer.compare(this.getDate().getMonthValue(), o.getDate().getMonthValue());
        if(this.getDate().getDayOfMonth() != o.getDate().getDayOfMonth())
            return Integer.compare(this.getDate().getDayOfMonth(), o.getDate().getDayOfMonth());
        if(this.getNumber() != o.getNumber())
            return Integer.compare(this.getNumber(), o.getNumber());
        if(this.getReceiptCustomerName().compareTo(o.getReceiptCustomerName()) != 0)
            return this.getReceiptCustomerName().compareTo(o.getReceiptCustomerName());
        return this.getName().compareTo(o.getName());
    }
}

