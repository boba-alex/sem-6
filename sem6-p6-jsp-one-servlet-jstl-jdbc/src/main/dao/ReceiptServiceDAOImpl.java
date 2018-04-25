package main.dao;

import main.constants.ConstantsSQL;
import main.entities.ReceiptService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 20.02.2018.
 */
public class ReceiptServiceDAOImpl implements ReceiptServiceDAO{

    @Override
    public List<ReceiptService> getReceiptServices() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<ReceiptService> receiptServices = null;
        try {
            Class.forName(ConstantsSQL.DRIVER);
            connection = DriverManager.getConnection(
                    ConstantsSQL.URL_DATABASE, ConstantsSQL.LOGIN, ConstantsSQL.PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(ConstantsSQL.SQL_QUERY_GET_RECEIPT_SERVICES);
            receiptServices = initReceiptServices(resultSet);

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
        return receiptServices;
    }

    @Override
    public ReceiptService getReceiptServiceById(int id) {
        return null;
    }

    private List<ReceiptService> initReceiptServices(ResultSet resultSet) throws SQLException {
        List<ReceiptService> receiptServices = new ArrayList<ReceiptService>();
        while (resultSet.next()) {
            ReceiptService receiptService = new ReceiptService();

            receiptService.setId(resultSet.getInt(1));
            receiptService.setName(resultSet.getString(2));

            receiptServices.add(receiptService);
        }
        return receiptServices;
    }

}
