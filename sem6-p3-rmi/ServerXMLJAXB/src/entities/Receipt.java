package entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author User
 */
@XmlRootElement
public class Receipt implements Serializable{
    private int id;
    private ReceiptService receiptService;
    private ReceiptCustomer receiptCustomer;
    private LocalDate date;

    public Receipt(){}
    
    public Receipt(ReceiptService receiptService, ReceiptCustomer receiptCustomer, LocalDate date){
        this.receiptService = receiptService;
        this.receiptCustomer=receiptCustomer;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    @XmlAnyAttribute
    public void setId(int id) {
        this.id = id;
    }

    public ReceiptService getReceiptService() {
        return receiptService;
    }

    @XmlElement
    public void setReceiptService(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    public ReceiptCustomer getReceiptCustomer() {
        return receiptCustomer;
    }

    @XmlElement
    public void setReceiptCustomer(ReceiptCustomer receiptCustomer) {
        this.receiptCustomer = receiptCustomer;
    }

    public LocalDate getDate() {
        return date;
    }

    @XmlElement
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void addReceiptCustomer(final ReceiptCustomer receiptCustomer){
        this.receiptCustomer = receiptCustomer;
    }

    public void addReceiptService(final ReceiptService receiptService){
        this.receiptService = receiptService;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", receiptService=" + receiptService +
                ", receiptCustomer=" + receiptCustomer +
                ", date=" + date +
                '}';
    }

}

