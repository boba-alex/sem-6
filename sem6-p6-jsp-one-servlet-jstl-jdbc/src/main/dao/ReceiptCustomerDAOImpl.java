package main.dao;

import main.constants.ConstantsSQL;
import main.entities.ReceiptCustomer;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 20.02.2018.
 */
public class ReceiptCustomerDAOImpl implements ReceiptCustomerDAO {

    @Override
    public void addReceiptCustomer(ReceiptCustomer receiptCustomer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(ConstantsSQL.DRIVER);
            connection = DriverManager.getConnection(ConstantsSQL.URL_DATABASE, ConstantsSQL.LOGIN, ConstantsSQL.PASSWORD);
            preparedStatement = connection.prepareStatement(ConstantsSQL.SQL_QUERY_ADD_RECEIPT_CUSTOMER);
            preparedStatement.setString(1, receiptCustomer.getName());
            preparedStatement.setString(2, receiptCustomer.getSurname());
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

    @Override
    public List<ReceiptCustomer> getReceiptCurstomers() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<ReceiptCustomer> receiptCustomers = null;
        try {
            Class.forName(ConstantsSQL.DRIVER);
            connection = DriverManager.getConnection(
                    ConstantsSQL.URL_DATABASE, ConstantsSQL.LOGIN, ConstantsSQL.PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(ConstantsSQL.SQL_QUERY_GET_RECEIPT_CUSTOMERS);
            receiptCustomers = initReceiptCustomers(resultSet);

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
        return receiptCustomers;
    }

    @Override
    public ReceiptCustomer getReceiptCustomerById(int id) {
        return null;
    }

    private List<ReceiptCustomer> initReceiptCustomers(ResultSet resultSet) throws SQLException {
        List<ReceiptCustomer> receiptCustomers = new ArrayList<ReceiptCustomer>();
        while (resultSet.next()) {
            ReceiptCustomer receiptCustomer = new ReceiptCustomer();

            receiptCustomer.setId(resultSet.getInt(1));
            receiptCustomer.setName(resultSet.getString(2));
            receiptCustomer.setSurname(resultSet.getString(3));

            receiptCustomers.add(receiptCustomer);
        }
        return receiptCustomers;
    }
}
