package dao;

import entities.ReceiptCustomer;
import exceptions.MyDAOException;

import java.util.List;

/**
 * Интерфейс для работы с клиентами.
 *
 * @author Polischuk Alexander
 * @version 1.0
 */
public interface ReceiptCustomerDAO {
    /**
     * Метод для добавления клиента.
     *
     * @param receiptCustomer добавляемый клиент
     * @throws MyDAOException исключение {@link MyDAOException}
     */
    void addReceiptCustomer(final ReceiptCustomer receiptCustomer) throws MyDAOException;

    /**
     * Метод для полумения клиентов.
     *
     * @return List список клиентов
     * @throws MyDAOException
     */
    List<ReceiptCustomer> getReceiptCustomers() throws MyDAOException;
}
