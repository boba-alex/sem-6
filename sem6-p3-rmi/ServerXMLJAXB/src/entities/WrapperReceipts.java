package entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * <p>Класс-обертка, содержащий список квитанций.</p>
 *
 * @author Polischuk Alexander
 * @version 1.0
 */
@XmlRootElement(name = "Receipts")
@XmlAccessorType(XmlAccessType.NONE)
public class WrapperReceipts implements Serializable{

    @XmlElement(name = "Receipt")
    private List<Receipt> receipts;

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }
}