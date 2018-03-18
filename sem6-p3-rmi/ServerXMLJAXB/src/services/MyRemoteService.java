package services;

import entities.Receipt;
import entities.ReceiptCustomer;
import entities.ReceiptService;
import exceptions.MyDAOException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by User on 28.02.2018.
 */
public interface MyRemoteService extends Remote {
    void addReceipt(Receipt receipt) throws RemoteException, MyDAOException;
    List<Receipt> getAllReceipts() throws RemoteException, MyDAOException;
    List<Receipt> getReceiptsInCurrentDay() throws RemoteException, MyDAOException;
    List<Receipt> getReceiptsInCurrentMonth() throws RemoteException, MyDAOException;
    List<Receipt> getReceiptsInCurrentQuarter() throws RemoteException, MyDAOException;
    void deleteReceipt(Receipt receipt) throws RemoteException, MyDAOException;
    void addReceiptCustomer(final ReceiptCustomer receiptCustomer) throws RemoteException, MyDAOException;
    List<ReceiptCustomer> getReceiptCurstomers() throws RemoteException, MyDAOException;
    List<ReceiptService> getReceiptServices() throws RemoteException, MyDAOException;
}
