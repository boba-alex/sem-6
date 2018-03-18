package dao;

import entities.ReceiptCustomer;
import exceptions.MyDAOException;

import java.util.List;

/**
 * Created by User on 20.02.2018.
 */
public interface ReceiptCustomerDAO {
    void addReceiptCustomer(final ReceiptCustomer receiptCustomer) throws MyDAOException;
    List<ReceiptCustomer> getReceiptCustomers() throws MyDAOException;
}
