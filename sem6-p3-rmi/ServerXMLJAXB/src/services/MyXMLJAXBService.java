package services;

import dao.*;
import entities.Receipt;
import entities.ReceiptCustomer;
import entities.ReceiptService;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by User on 13.03.2018.
 */
public class MyXMLJAXBService implements MyRemoteService {
    private ReceiptDAO receiptDAO;
    private ReceiptCustomerDAO receiptCustomerDAO;
    private ReceiptServiceDAO receiptServiceDAO;

    public MyXMLJAXBService() {
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
        return receiptDAO.getAllReceipts();
    }

    @Override
    public List<Receipt> getReceiptsInCurrentDay() throws RemoteException{
        return receiptDAO.getReceiptsInCurrentDay();
    }

    @Override
    public List<Receipt> getReceiptsInCurrentMonth() throws RemoteException{
        return receiptDAO.getReceiptsInCurrentMonth();
    }

    @Override
    public List<Receipt> getReceiptsInCurrentQuarter() throws RemoteException{
        return receiptDAO.getReceiptsInCurrentQuarter();
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
        return receiptCustomerDAO.getReceiptCustomers();
    }

    @Override
    public List<ReceiptService> getReceiptServices() throws RemoteException{
        return receiptServiceDAO.getReceiptServices();
    }
}
