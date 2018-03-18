package dao;

import entities.ReceiptService;
import exceptions.MyDAOException;

import java.util.List;

/**
 * Created by User on 20.02.2018.
 */
public interface ReceiptServiceDAO {
    List<ReceiptService> getReceiptServices() throws MyDAOException;
}
