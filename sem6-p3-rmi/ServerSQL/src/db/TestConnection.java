package db;

import constants.ConstantsSQL;
import entities.Receipt;
import entities.ReceiptCustomer;
import exceptions.MyDAOException;
import entities.ReceiptService;
import services.MySQLService;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>Класс для тестирования подключения к базе данных.</p>
 *
 * @author Polischuk Alexander
 * @version 1.0
 */
public class TestConnection {

    /**
     * Главный метод, который запускается в классе {@link TestConnection}.
     *
     * @param args параметры, передаваемые аргументами в коммандной строке
     */
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
            new MySQLService().deleteReceipt(receipt);
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MyDAOException e) {
            e.printStackTrace();
        }
    }

}
