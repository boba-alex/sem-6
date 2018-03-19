package dao;

import entities.Receipt;

import java.util.List;

/**
 * @author User
 */
public interface ReceiptDAO {
    void addReceipt(final Receipt receipt);

    List<Receipt> getAllReceipts();

    List<Receipt> getReceiptsInCurrentDay();

    List<Receipt> getReceiptsInCurrentMonth();

    List<Receipt> getReceiptsInCurrentQuarter();

    void deleteReceipt(Receipt receipt);
}
