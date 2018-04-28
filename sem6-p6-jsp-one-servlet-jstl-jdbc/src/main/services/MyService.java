package main.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * @author User
 */

import main.constants.ConstantsSQL;
import main.dao.*;
import main.entities.Receipt;
import main.entities.ReceiptCustomer;
import main.entities.ReceiptService;

import java.util.List;

public class MyService {
    private ReceiptDAO receiptDAO;
    private ReceiptCustomerDAO receiptCustomerDAO;
    private ReceiptServiceDAO receiptServiceDAO;

    public MyService() {
        receiptDAO = new ReceiptDAOImpl();
        receiptCustomerDAO = new ReceiptCustomerDAOImpl();
        receiptServiceDAO = new ReceiptServiceDAOImpl();
    }

    public void addReceipt(Receipt receipt) {
        receiptDAO.addReceipt(receipt);
    }

    public List<Receipt> getAllReceipts() {
        return receiptDAO.getReceipts(ConstantsSQL.SQL_QUERY_GET_RECEIPTS);
    }

    public List<Receipt> getReceiptsInCurrentDay() {
        return receiptDAO.getReceipts(ConstantsSQL.SQL_QUERY_GET_RECEIPTS_IN_CURRENT_DAY);
    }

    public List<Receipt> getReceiptsInCurrentMonth() {
        return receiptDAO.getReceipts(ConstantsSQL.SQL_QUERY_GET_RECEIPTS_IN_CURRENT_MONTH);
    }

    public List<Receipt> getReceiptsInCurrentQuarter() {
        return receiptDAO.getReceipts(ConstantsSQL.SQL_QUERY_GET_RECEIPTS_IN_CURRENT_CUARTER);
    }

    public void deleteReceipt(Receipt receipt) {
        receiptDAO.deleteReceipt(receipt);
    }

    public void addReceiptCustomer(final ReceiptCustomer receiptCustomer) {
        receiptCustomerDAO.addReceiptCustomer(receiptCustomer);
    }

    public List<ReceiptCustomer> getReceiptCustomers() {
        return receiptCustomerDAO.getReceiptCurstomers();
    }

    public List<ReceiptService> getReceiptServices() {
        return receiptServiceDAO.getReceiptServices();
    }

    public Receipt getReceiptById(int id) {
        return receiptDAO.getReceiptById(id);
    }

    public ReceiptCustomer getReceiptCustomerById(int id) {
        return receiptCustomerDAO.getReceiptCustomerById(id);
    }

    public ReceiptService getReceiptServiceById(int id) {
        return receiptServiceDAO.getReceiptServiceById(id);
    }

}
