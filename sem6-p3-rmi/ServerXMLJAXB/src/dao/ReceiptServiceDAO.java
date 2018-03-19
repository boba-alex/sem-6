package dao;

import exceptions.MyDAOException;
import entities.ReceiptService;

import java.util.List;

/**
 * Created by User on 20.02.2018.
 */
public interface ReceiptServiceDAO {
    List<ReceiptService> getReceiptServices() throws MyDAOException;
}
