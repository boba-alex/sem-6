package dao;

import entities.ReceiptCustomer;

import java.util.List;

/**
 * Интерфейс для работы с клиентами.
 *
 * @author Polischuk Alexander
 * @version 1.0
 */
public interface ReceiptCustomerDAO {
    /**
     * Метод добавления клиента.
     *
     * @param receiptCustomer добавляемый клиент
     */
    void addReceiptCustomer(final ReceiptCustomer receiptCustomer);

    /**
     * Метод получения клиентов из базы данных.
     *
     * @return List список клиентов
     */
    List<ReceiptCustomer> getReceiptCurstomers();
}
