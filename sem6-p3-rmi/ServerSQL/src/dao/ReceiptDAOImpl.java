package dao;

import constants.ConstantsSQL;
import entities.Receipt;
import entities.ReceiptCustomer;
import entities.ReceiptService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * <p>Класс, реализующий интерфейс, {@link ReceiptDAO},
 * служит для <b>хранения процедур</b> для работы с квитанциями.
 * Осуществляет работу с базой данных <tt>MySQL.</tt></p>
 *
 * @author Polischuk Alexander
 * @version 1.0
 * @see ReceiptDAO
 */
public class ReceiptDAOImpl implements ReceiptDAO {

    /**
     * Метод добавления квитанции.
     *
     * @param receipt добавляемая квитанция
     */
    @Override
    public void addReceipt(Receipt receipt) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(ConstantsSQL.DRIVER);
            connection = DriverManager.getConnection(ConstantsSQL.URL_DATABASE, ConstantsSQL.LOGIN, ConstantsSQL.PASSWORD);
            preparedStatement = connection.prepareStatement(ConstantsSQL.SQL_QUERY_ADD_RECEIPT);
            preparedStatement.setInt(1, receipt.getReceiptService().getId());
            preparedStatement.setObject(2, receipt.getDate()); //for Date
            preparedStatement.setInt(3, receipt.getReceiptCustomer().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL exception occurred during add client");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Class driver not found");
            ex.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("SQL exception occurred during add client");
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод для получения списка квитанций в зависимости от переданного SQL-запроса.
     *
     * @param sqlex SQL-запрос
     * @return List список квитанций, полученный согласно SQL-запросу.
     */
    @Override
    public List<Receipt> getReceipts(String sqlex) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Receipt> receipts = null;
        try {
            Class.forName(ConstantsSQL.DRIVER);
            connection = DriverManager.getConnection(
                    ConstantsSQL.URL_DATABASE, ConstantsSQL.LOGIN, ConstantsSQL.PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlex);
            receipts = initReceipts(resultSet);

        } catch (ClassNotFoundException e) {
            System.out.println("Class driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL exception occurred during add client");
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("SQL exception occurred during add client");
                e.printStackTrace();
            }
        }
        return receipts;
    }

    /**
     * Метод для инициализации списка квитанций из {@link ResultSet}.
     *
     * @param resultSet передаваемый параметр
     * @return List список проинициализированных квитанций
     * @throws SQLException исключение {@link SQLException}
     */
    private List<Receipt> initReceipts(ResultSet resultSet) throws SQLException {
        List<Receipt> receipts = new ArrayList<Receipt>();
        while (resultSet.next()) {
            Receipt receipt = new Receipt();
            ReceiptService receiptService = new ReceiptService();
            ReceiptCustomer receiptCustomer = new ReceiptCustomer();

            receipt.setId(resultSet.getInt(1));
            receipt.setDate(resultSet.getObject(3, LocalDate.class));

            receiptService.setId(resultSet.getInt(2));
            receiptService.setName(resultSet.getString(7));

            receiptCustomer.setId(resultSet.getInt(4));
            receiptCustomer.setName(resultSet.getString(5));
            receiptCustomer.setSurname(resultSet.getString(6));

            receipt.addReceiptCustomer(receiptCustomer);
            receipt.addReceiptService(receiptService);

            receipts.add(receipt);
        }
        return receipts;
    }

    /**
     * Метод для удаления квитанции.
     *
     * @param receipt удаляемая квитанция
     */
    @Override
    public void deleteReceipt(Receipt receipt) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(ConstantsSQL.DRIVER);
            connection = DriverManager.getConnection(ConstantsSQL.URL_DATABASE, ConstantsSQL.LOGIN, ConstantsSQL.PASSWORD);
            preparedStatement = connection.prepareStatement(ConstantsSQL.SQL_QUERY_DELETE_RECEIPT_BY_ID);
            preparedStatement.setString(1, Integer.valueOf(receipt.getId()).toString());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL exception occurred during add client");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Class driver not found");
            ex.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("SQL exception occurred during add client");
                e.printStackTrace();
            }
        }
    }
}




