package entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * <p>Класс-обертка, содержащий список клиентов.</p>
 *
 * @author Polischuk Alexander
 * @version 1.0
 */
@XmlRootElement(name = "ReceiptCustomers")
@XmlAccessorType(XmlAccessType.NONE)
public class WrapperReceiptCustomers implements Serializable{

    @XmlElement(name = "ReceiptCustomer")
    private List<ReceiptCustomer> receiptCustomers;

    public List<ReceiptCustomer> getReceiptCustomers() {
        return receiptCustomers;
    }

    public void setReceiptCustomers(List<ReceiptCustomer> receiptCustomers) {
        this.receiptCustomers = receiptCustomers;
    }
}