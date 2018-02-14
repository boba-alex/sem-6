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
import main.dao.ReceiptDAO;
import main.dao.ReceiptDaoImpl;
import main.entities.Receipt;

import java.util.List;

public class ReceiptService {
    private ReceiptDAO receiptDAO;

    public ReceiptService() {
        receiptDAO = new ReceiptDaoImpl();
    }

    public void addReceipt(Receipt receipt) {
        receiptDAO.addReceipt(receipt);
    }

    public List<Receipt> getReceipts() {
        return receiptDAO.getReceipts();
    }

    public List<Receipt> getReceiptsInCurrentDay() {
        return receiptDAO.getReceiptsInTime(ConstantsSQL.SQL_QUERY_GET_RECEIPTS_IN_CURRENT_DAY);
    }

    public List<Receipt> getReceiptsInCurrentMonth() {
        return receiptDAO.getReceiptsInTime(ConstantsSQL.SQL_QUERY_GET_RECEIPTS_IN_CURRENT_MONTH);
    }

    public List<Receipt> getReceiptsInCurrentQuarter() {
        return receiptDAO.getReceiptsInTime(ConstantsSQL.SQL_QUERY_GET_RECEIPTS_IN_CURRENT_CUARTER);
    }
}
