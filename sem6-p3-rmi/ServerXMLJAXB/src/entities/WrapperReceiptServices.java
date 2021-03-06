package entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * <p>Класс-обертка, содержащий список услуг.</p>
 *
 * @author Polischuk Alexander
 * @version 1.0
 */
@XmlRootElement(name = "ReceiptServices")
@XmlAccessorType(XmlAccessType.NONE)
public class WrapperReceiptServices implements Serializable{

    @XmlElement(name = "ReceiptService")
    private List<ReceiptService> receiptServices;

    public List<ReceiptService> getReceiptServices() {
        return receiptServices;
    }

    public void setReceiptServices(List<ReceiptService> receiptServices) {
        this.receiptServices = receiptServices;
    }
}