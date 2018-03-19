package dao;

import entities.Receipt;
import exceptions.MyDAOException;

import java.util.List;

/**
 * @author User
 */
public interface ReceiptDAO {
    void addReceipt(final Receipt receipt) throws MyDAOException;

    List<Receipt> getAllReceipts() throws MyDAOException;

    List<Receipt> getReceiptsInCurrentDay() throws MyDAOException;

    List<Receipt> getReceiptsInCurrentMonth() throws MyDAOException;

    List<Receipt> getReceiptsInCurrentQuarter() throws MyDAOException;

    void deleteReceipt(Receipt receipt) throws MyDAOException;
}
