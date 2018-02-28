package services;

import entities.Receipt;
import entities.ReceiptCustomer;
import entities.ReceiptService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by User on 28.02.2018.
 */
public interface MyRemoteService extends Remote {
    void addReceipt(Receipt receipt) throws RemoteException;
    List<Receipt> getAllReceipts() throws RemoteException;
    List<Receipt> getReceiptsInCurrentDay() throws RemoteException;
    List<Receipt> getReceiptsInCurrentMonth() throws RemoteException;
    List<Receipt> getReceiptsInCurrentQuarter() throws RemoteException;
    void deleteReceipt(Receipt receipt) throws RemoteException;
    void addReceiptCustomer(final ReceiptCustomer receiptCustomer) throws RemoteException;
    List<ReceiptCustomer> getReceiptCurstomers() throws RemoteException;
    List<ReceiptService> getReceiptServices() throws RemoteException;
}
