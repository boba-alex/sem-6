package services;

import constants.ConstantsSQL;
import dao.*;
import entities.Receipt;
import entities.ReceiptCustomer;
import entities.ReceiptService;
import exceptions.MyDAOException;

import java.rmi.RemoteException;
import java.util.List;

/**
 * <p>Класс служит для <b>хранения процедур</b>,
 * к которым можно обращаться средствами <tt>RMI</tt>,
 * для работы с базой данных <tt><a href="https://en.wikipedia.org/wiki/MySQL">MySQL</a></tt></p>
 *
 * @author Polischuk Alexander
 * @version 1.0
 * @see MyRemoteService исключение <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>
 */
public class MySQLService implements MyRemoteService {
    /**
     * Необходимые DAO.
     */
    private ReceiptDAO receiptDAO;
    private ReceiptCustomerDAO receiptCustomerDAO;
    private ReceiptServiceDAO receiptServiceDAO;

    /**
     * Конструктор, в котором инициализируются все DAO.
     */
    public MySQLService() {
        receiptDAO = new ReceiptDAOImpl();
        receiptCustomerDAO = new ReceiptCustomerDAOImpl();
        receiptServiceDAO = new ReceiptServiceDAOImpl();
    }

    /**
     * Метод для добавления квитанции.
     *
     * @param receipt добавляемая квитанция
     * @throws RemoteException исключение <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>
     * @throws MyDAOException  исключение {@link MyDAOException}
     */
    @Override
    public void addReceipt(Receipt receipt) throws RemoteException, MyDAOException {
        receiptDAO.addReceipt(receipt);
    }

    /**
     * Метод для получения всех квитанций за все время.
     *
     * @return список всех квитанций
     * @throws RemoteException исключение <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>
     * @throws MyDAOException  исключение {@link MyDAOException}
     */
    @Override
    public List<Receipt> getAllReceipts() throws RemoteException, MyDAOException {
        return receiptDAO.getReceipts(ConstantsSQL.SQL_QUERY_GET_RECEIPTS);
    }

    /**
     * Метод для получения квитанций за текущий день.
     *
     * @return список квитанций за текущий день
     * @throws RemoteException исключение <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>
     * @throws MyDAOException  исключение {@link MyDAOException}
     */
    @Override
    public List<Receipt> getReceiptsInCurrentDay() throws RemoteException, MyDAOException {
        return receiptDAO.getReceipts(ConstantsSQL.SQL_QUERY_GET_RECEIPTS_IN_CURRENT_DAY);
    }

    /**
     * Метод для получения квитанций за текущий месяц.
     *
     * @return список квитанций за текущий месяц
     * @throws RemoteException исключение <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>
     * @throws MyDAOException  исключение {@link MyDAOException}
     */
    @Override
    public List<Receipt> getReceiptsInCurrentMonth() throws RemoteException, MyDAOException {
        return receiptDAO.getReceipts(ConstantsSQL.SQL_QUERY_GET_RECEIPTS_IN_CURRENT_MONTH);
    }

    /**
     * Метод для получения квитанций за текущий квартал.
     *
     * @return список квитанций за текущий квартал
     * @throws RemoteException исключение <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>
     * @throws MyDAOException  исключение {@link MyDAOException}
     */
    @Override
    public List<Receipt> getReceiptsInCurrentQuarter() throws RemoteException, MyDAOException {
        return receiptDAO.getReceipts(ConstantsSQL.SQL_QUERY_GET_RECEIPTS_IN_CURRENT_CUARTER);
    }

    /**
     * Метод для удаления квитанции.
     *
     * @param receipt квитанция для удаления
     * @throws RemoteException исключение <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>
     * @throws MyDAOException  исключение {@link MyDAOException}
     */
    @Override
    public void deleteReceipt(Receipt receipt) throws RemoteException, MyDAOException {
        receiptDAO.deleteReceipt(receipt);
    }

    /**
     * Метод для добавления клиента.
     *
     * @param receiptCustomer добавляемый клиент
     * @throws RemoteException исключение <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>
     * @throws MyDAOException  исключение {@link MyDAOException}
     */
    @Override
    public void addReceiptCustomer(final ReceiptCustomer receiptCustomer) throws RemoteException, MyDAOException {
        receiptCustomerDAO.addReceiptCustomer(receiptCustomer);
    }

    /**
     * Метод для получения списка всех клиентов.
     *
     * @return список всех клиентов
     * @throws RemoteException исключение <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>
     * @throws MyDAOException  исключение {@link MyDAOException}
     */
    @Override
    public List<ReceiptCustomer> getReceiptCurstomers() throws RemoteException, MyDAOException {
        return receiptCustomerDAO.getReceiptCurstomers();
    }

    /**
     * Метод для получения списка всех услуг.
     *
     * @return список всех услуг
     * @throws RemoteException исключение <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>
     * @throws MyDAOException  исключение {@link MyDAOException}
     */
    @Override
    public List<ReceiptService> getReceiptServices() throws RemoteException, MyDAOException {
        return receiptServiceDAO.getReceiptServices();
    }

}
