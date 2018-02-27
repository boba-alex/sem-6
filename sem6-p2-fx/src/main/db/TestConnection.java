package main.db;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import main.constants.ConstantsSQL;
import main.entities.Receipt;
import main.entities.ReceiptCustomer;
import main.entities.ReceiptService;
import main.services.MyService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author User
 */
public class TestConnection {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(ConstantsSQL.URL_DATABASE, ConstantsSQL.LOGIN, ConstantsSQL.PASSWORD);
            if (conn == null) {
                System.out.println("Нет соединения с БД!");
                System.exit(0);
            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(ConstantsSQL.SQL_QUERY_GET_RECEIPTS);
            while (rs.next()) {
                System.out.println(rs.getString(1) + rs.getString(2) +
                        rs.getString(3) + rs.getString(4) + rs.getString(5));
            }
            Receipt receipt = new Receipt(new ReceiptService("eee"), new ReceiptCustomer("a", "b"), LocalDate.now());

            receipt.setId(4);
            System.out.println(receipt);
            new MyService().deleteReceipt(receipt);
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
