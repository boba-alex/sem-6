package dao;

import entities.ReceiptCustomer;

import java.util.List;

/**
 * Created by User on 20.02.2018.
 */
public interface ReceiptCustomerDAO {
    void addReceiptCustomer(final ReceiptCustomer receiptCustomer);
    List<ReceiptCustomer> getReceiptCustomers();
}
