package main.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import main.constants.ConstantsSQL;
import main.entities.Receipt;

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

public class ReceiptDaoImpl implements ReceiptDAO {


    @Override
    public void addReceipt(Receipt receipt) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(ConstantsSQL.DRIVER);
            connection = DriverManager.getConnection(ConstantsSQL.URL_DATABASE, ConstantsSQL.LOGIN, ConstantsSQL.PASSWORD);
            preparedStatement = connection.prepareStatement(ConstantsSQL.SQL_QUERY_ADD_RECEIPT);
            preparedStatement.setInt(1, receipt.getNumber());
            preparedStatement.setString(2, receipt.getName() + "");
            preparedStatement.setObject(3, receipt.getDate()); //for Date
            preparedStatement.setInt(4, 1);
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
    public List<Receipt> getReceipts() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Receipt> receipts = null;
        try {
            Class.forName(ConstantsSQL.DRIVER);
            connection = DriverManager.getConnection(
                    ConstantsSQL.URL_DATABASE, ConstantsSQL.LOGIN, ConstantsSQL.PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(ConstantsSQL.SQL_QUERY_GET_RECEIPTS);
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

    @Override
    public List<Receipt> getReceiptsInTime(String sqlex) {
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

    @Override
    public void deleteReceipt(Receipt receipt) {

    }

    private List<Receipt> initReceipts(ResultSet resultSet) throws SQLException {
        List<Receipt> receipts = new ArrayList<Receipt>();
        while (resultSet.next()) {
            Receipt receipt = new Receipt();
            receipt.setNumber(resultSet.getInt(2));
            receipt.setName(resultSet.getString(3));
            receipt.setDate(resultSet.getObject(4, LocalDate.class));
            receipt.setReceiptCustomerName(resultSet.getString(5));
            receipts.add(receipt);
        }
        return receipts;
    }

}




