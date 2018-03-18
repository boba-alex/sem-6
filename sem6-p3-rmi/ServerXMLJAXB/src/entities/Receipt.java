package entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author User
 */

@XmlRootElement(name = "Receipt")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {"id", "receiptService", "receiptCustomer", "date"})
public class Receipt implements Serializable {

    @XmlAttribute(required = true)
    private int id;

    @XmlElement(name = "ReceiptService")
    private ReceiptService receiptService;

    @XmlElement(name = "ReceiptCustomer")
    private ReceiptCustomer receiptCustomer;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlElement(name = "DateOfReceiptService")
    private LocalDate date;

    public Receipt() {
    }

    public Receipt(ReceiptService receiptService, ReceiptCustomer receiptCustomer, LocalDate date) {
        this.receiptService = receiptService;
        this.receiptCustomer = receiptCustomer;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReceiptService getReceiptService() {
        return receiptService;
    }

    public void setReceiptService(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    public ReceiptCustomer getReceiptCustomer() {
        return receiptCustomer;
    }

    public void setReceiptCustomer(ReceiptCustomer receiptCustomer) {
        this.receiptCustomer = receiptCustomer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void addReceiptCustomer(final ReceiptCustomer receiptCustomer) {
        this.receiptCustomer = receiptCustomer;
    }

    public void addReceiptService(final ReceiptService receiptService) {
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

