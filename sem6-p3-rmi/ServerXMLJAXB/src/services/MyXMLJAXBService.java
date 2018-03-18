package services;

import dao.*;
import entities.Receipt;
import entities.ReceiptCustomer;
import entities.ReceiptService;
import exceptions.MyDAOException;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by User on 13.03.2018.
 */
public class MyXMLJAXBService implements MyRemoteService {
    private ReceiptDAO receiptDAO;
    private ReceiptCustomerDAO receiptCustomerDAO;
    private ReceiptServiceDAO receiptServiceDAO;

    public MyXMLJAXBService() throws MyDAOException {
        receiptDAO = new ReceiptDAOImpl();
        receiptCustomerDAO = new ReceiptCustomerDAOImpl();
        receiptServiceDAO = new ReceiptServiceDAOImpl();
    }

    @Override
    public void addReceipt(Receipt receipt) throws RemoteException, MyDAOException {
        receiptDAO.addReceipt(receipt);
    }

    @Override
    public List<Receipt> getAllReceipts() throws RemoteException, MyDAOException {
        return receiptDAO.getAllReceipts();
    }

    @Override
    public List<Receipt> getReceiptsInCurrentDay() throws RemoteException, MyDAOException {
        return receiptDAO.getReceiptsInCurrentDay();
    }

    @Override
    public List<Receipt> getReceiptsInCurrentMonth() throws RemoteException, MyDAOException {
        return receiptDAO.getReceiptsInCurrentMonth();
    }

    @Override
    public List<Receipt> getReceiptsInCurrentQuarter() throws RemoteException, MyDAOException {
        return receiptDAO.getReceiptsInCurrentQuarter();
    }

    @Override
    public void deleteReceipt(Receipt receipt) throws RemoteException, MyDAOException {
        receiptDAO.deleteReceipt(receipt);
    }

    @Override
    public void addReceiptCustomer(final ReceiptCustomer receiptCustomer) throws RemoteException, MyDAOException {
        receiptCustomerDAO.addReceiptCustomer(receiptCustomer);
    }

    @Override
    public List<ReceiptCustomer> getReceiptCurstomers() throws RemoteException, MyDAOException {
        return receiptCustomerDAO.getReceiptCustomers();
    }

    @Override
    public List<ReceiptService> getReceiptServices() throws RemoteException, MyDAOException {
        return receiptServiceDAO.getReceiptServices();
    }
}
