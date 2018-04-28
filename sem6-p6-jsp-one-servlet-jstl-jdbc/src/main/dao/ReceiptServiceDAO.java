package main.dao;

import main.entities.ReceiptService;

import java.util.List;

/**
 * Created by User on 20.02.2018.
 */
public interface ReceiptServiceDAO {
    List<ReceiptService> getReceiptServices();

    ReceiptService getReceiptServiceById(int id);
}
