package main.dao;

import main.entities.Receipt;

import java.util.List;

/**
 *
 * @author User
 */
public interface ReceiptDAO {
    void addReceipt(final Receipt receipt);
    List<Receipt> getReceipts();
    List<Receipt> getReceiptsInTime(String sqlex);
    void deleteReceipt(Receipt receipt);
}
