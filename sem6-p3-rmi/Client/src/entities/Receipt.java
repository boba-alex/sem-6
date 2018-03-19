package entities;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *<p>Класс-сущность, предоставляющая информацию о квитанции об оказанной услуге.</p>
 *
 * @author Polischuk Alexander
 * @version 1.0
 */
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

