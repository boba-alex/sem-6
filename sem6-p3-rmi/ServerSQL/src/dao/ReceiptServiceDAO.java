package dao;

import entities.ReceiptService;

import java.util.List;

/**
 * Интерфейс для работы с услугами.
 *
 * @author Polischuk Alexander
 * @version 1.0
 */
public interface ReceiptServiceDAO {
    /**
     * Метод получения всех услуг.
     *
     * @return List список услуг
     */
    List<ReceiptService> getReceiptServices();
}
