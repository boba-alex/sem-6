package services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * @author User
 */

import constants.ConstantsSQL;
import dao.*;
import entities.Receipt;
import entities.ReceiptCustomer;
import entities.ReceiptService;

import java.rmi.RemoteException;
import java.util.List;

public class MySQLService implements MyRemoteService{
    private ReceiptDAO receiptDAO;
    private ReceiptCustomerDAO receiptCustomerDAO;
    private ReceiptServiceDAO receiptServiceDAO;

    public MySQLService() {
        receiptDAO = new ReceiptDAOImpl();
        receiptCustomerDAO = new ReceiptCustomerDAOImpl();
        receiptServiceDAO = new ReceiptServiceDAOImpl();
    }

    @Override
    public void addReceipt(Receipt receipt) throws RemoteException {
        receiptDAO.addReceipt(receipt);
    }

    @Override
    public List<Receipt> getAllReceipts() throws RemoteException{
        return receiptDAO.getReceipts(ConstantsSQL.SQL_QUERY_GET_RECEIPTS);
    }

    @Override
    public List<Receipt> getReceiptsInCurrentDay() throws RemoteException{
        return receiptDAO.getReceipts(ConstantsSQL.SQL_QUERY_GET_RECEIPTS_IN_CURRENT_DAY);
    }

    @Override
    public List<Receipt> getReceiptsInCurrentMonth() throws RemoteException{
        return receiptDAO.getReceipts(ConstantsSQL.SQL_QUERY_GET_RECEIPTS_IN_CURRENT_MONTH);
    }

    @Override
    public List<Receipt> getReceiptsInCurrentQuarter() throws RemoteException{
        return receiptDAO.getReceipts(ConstantsSQL.SQL_QUERY_GET_RECEIPTS_IN_CURRENT_CUARTER);
    }

    @Override
    public void deleteReceipt(Receipt receipt) throws RemoteException{
        receiptDAO.deleteReceipt(receipt);
    }

    @Override
    public void addReceiptCustomer(final ReceiptCustomer receiptCustomer) throws RemoteException{
        receiptCustomerDAO.addReceiptCustomer(receiptCustomer);
    }

    @Override
    public List<ReceiptCustomer> getReceiptCurstomers() throws RemoteException{
        return receiptCustomerDAO.getReceiptCurstomers();
    }

    @Override
    public List<ReceiptService> getReceiptServices() throws RemoteException{
        return receiptServiceDAO.getReceiptServices();
    }

}
