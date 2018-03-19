package dao;

import entities.Receipt;

import java.util.List;

/**
 * Интерфейс для работы с квитанциями.
 *
 * @author Polischuk Alexander
 * @version 1.0
 */
public interface ReceiptDAO {
    /**
     * Метод добавления квитанции.
     *
     * @param receipt добавляемая квитанция
     */
    void addReceipt(final Receipt receipt);

    /**
     * Метод получения списка квитанций.
     *
     * @param sqlex SQL-запрос
     * @return List список квитанций
     */
    List<Receipt> getReceipts(String sqlex);

    /**
     * Метод для удаления квитанции.
     *
     * @param receipt удаляемая квитанция
     */
    void deleteReceipt(Receipt receipt);
}
