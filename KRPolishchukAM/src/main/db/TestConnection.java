package main.db;

import main.constants.ConstantsSQL;
import main.dao.DealDAOImpl;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Class.forName(ConstantsSQL.DRIVER);
            Connection conn = DriverManager.getConnection(ConstantsSQL.URL_DATABASE, ConstantsSQL.LOGIN, ConstantsSQL.PASSWORD);
            if (conn == null) {
                System.out.println("Нет соединения с БД!");
                System.exit(0);
            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(ConstantsSQL.SQL_QUERY_GET_DEALS);
            while (rs.next()) {
                System.out.println(rs.getString(1) + rs.getString(2) +
                        rs.getString(3) + rs.getString(4) + rs.getString(5));
            }
            System.out.println(new DealDAOImpl().countAllDeals());
            System.out.println(new DealDAOImpl().sumAllDeals());
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
