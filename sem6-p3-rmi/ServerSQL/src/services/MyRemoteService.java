package services;

import entities.Receipt;
import entities.ReceiptCustomer;
import exceptions.MyDAOException;
import entities.ReceiptService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * <p>Интерфейс <b>удаленного сервиса</b>, для реализации
 * вызова методов с помощью библиотеки <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>.
 *
 * @author Polischuk Alexander
 * @version 1.0
 * @see Remote
 */
public interface MyRemoteService extends Remote {

    /**
     * Метод для добавления квитанции.
     *
     * @param receipt добавляемая квитанция
     * @throws RemoteException исключение <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>
     * @throws MyDAOException  исключение {@link MyDAOException}
     */
    void addReceipt(Receipt receipt) throws RemoteException, MyDAOException;

    /**
     * Метод для получения всех квитанций за все время.
     *
     * @return список всех квитанций
     * @throws RemoteException исключение <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>
     * @throws MyDAOException  исключение {@link MyDAOException}
     */
    List<Receipt> getAllReceipts() throws RemoteException, MyDAOException;

    /**
     * Метод для получения квитанций за текущий день.
     *
     * @return список квитанций за текущий день
     * @throws RemoteException исключение <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>
     * @throws MyDAOException  исключение {@link MyDAOException}
     */
    List<Receipt> getReceiptsInCurrentDay() throws RemoteException, MyDAOException;

    /**
     * Метод для получения квитанций за текущий месяц.
     *
     * @return список квитанций за текущий месяц
     * @throws RemoteException исключение <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>
     * @throws MyDAOException  исключение {@link MyDAOException}
     */
    List<Receipt> getReceiptsInCurrentMonth() throws RemoteException, MyDAOException;

    /**
     * Метод для получения квитанций за текущий квартал.
     *
     * @return список квитанций за текущий квартал
     * @throws RemoteException исключение <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>
     * @throws MyDAOException  исключение {@link MyDAOException}
     */
    List<Receipt> getReceiptsInCurrentQuarter() throws RemoteException, MyDAOException;

    /**
     * Метод для удаления квитанции.
     *
     * @param receipt квитанция для удаления
     * @throws RemoteException исключение <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>
     * @throws MyDAOException  исключение {@link MyDAOException}
     */
    void deleteReceipt(Receipt receipt) throws RemoteException, MyDAOException;

    /**
     * Метод для добавления клиента.
     *
     * @param receiptCustomer добавляемый клиент
     * @throws RemoteException исключение <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>
     * @throws MyDAOException  исключение {@link MyDAOException}
     */
    void addReceiptCustomer(final ReceiptCustomer receiptCustomer) throws RemoteException, MyDAOException;

    /**
     * Метод для получения списка всех клиентов.
     *
     * @return список всех клиентов
     * @throws RemoteException исключение <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>
     * @throws MyDAOException  исключение {@link MyDAOException}
     */
    List<ReceiptCustomer> getReceiptCurstomers() throws RemoteException, MyDAOException;

    /**
     * Метод для получения списка всех услуг.
     *
     * @return список всех услуг
     * @throws RemoteException исключение <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>
     * @throws MyDAOException  исключение {@link MyDAOException}
     */
    List<ReceiptService> getReceiptServices() throws RemoteException, MyDAOException;
}
