package main.dao;

import main.constants.ConstantsSQL;
import main.entity.Deal;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DealDAOImpl implements DealDAO {
    @Override
    public List<Deal> getDeals() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Deal> deals = null;
        try {
            Class.forName(ConstantsSQL.DRIVER);
            connection = DriverManager.getConnection(
                    ConstantsSQL.URL_DATABASE, ConstantsSQL.LOGIN, ConstantsSQL.PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(ConstantsSQL.SQL_QUERY_GET_DEALS);
            deals = initDeals(resultSet);

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
                System.out.println("SQL exception occurred during add student");
                e.printStackTrace();
            }
        }
        return deals;
    }

    @Override
    public int countAllDeals() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            Class.forName(ConstantsSQL.DRIVER);
            connection = DriverManager.getConnection(
                    ConstantsSQL.URL_DATABASE, ConstantsSQL.LOGIN, ConstantsSQL.PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(ConstantsSQL.SQL_QUERY_COUNT_ALL_DEALS);
            while(resultSet.next()){
                count = resultSet.getInt(1);
            }
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
                System.out.println("SQL exception occurred during add student");
                e.printStackTrace();
            }
        }
        return count;
    }

    @Override
    public int sumAllDeals() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int sum = 0;
        try {
            Class.forName(ConstantsSQL.DRIVER);
            connection = DriverManager.getConnection(
                    ConstantsSQL.URL_DATABASE, ConstantsSQL.LOGIN, ConstantsSQL.PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(ConstantsSQL.SQL_QUERY_SUM_ALL_DEALS);
            while(resultSet.next()){
                sum = resultSet.getInt(1);
            }
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
                System.out.println("SQL exception occurred during add student");
                e.printStackTrace();
            }
        }
        return sum;
    }

    private List<Deal> initDeals(ResultSet resultSet) throws SQLException {
        List<Deal> deals = new ArrayList<Deal>();
        while (resultSet.next()) {
            Deal deal = new Deal();

            deal.setId(resultSet.getInt(1));
            deal.setType(resultSet.getString(2));
            deal.setName(resultSet.getString(3));
            deal.setDateOfDeal(resultSet.getObject(4, LocalDate.class));
            deal.setSum(resultSet.getInt(5));

            deals.add(deal);
        }
        return deals;
    }

    public static void main(String[] args) {
        for(Deal s : new DealDAOImpl().getDeals()){
            System.out.println(s);
        }
    }
}
