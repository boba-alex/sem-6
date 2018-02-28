package dao;

import entities.Receipt;

import java.util.List;

/**
 *
 * @author User
 */
public interface ReceiptDAO {
    void addReceipt(final Receipt receipt);
    List<Receipt> getReceipts(String sqlex);
    void deleteReceipt(Receipt receipt);
}
