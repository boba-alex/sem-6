package main.constants;

public class ConstantsSQL {
    public static final String URL_DATABASE = "jdbc:mysql://localhost:3306/receiptes_database?useSSL=false";
    public static final String LOGIN = "root";
    public static final String PASSWORD = "root";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String SQL_QUERY_ADD_RECEIPT = "INSERT INTO receipt (number,name,date_of_service, receipt_customer_id) VALUES (?,?,?,?)";
    public static final String SQL_QUERY_GET_RECEIPTS = "SELECT r.id, r.number, r.name, r.date_of_service , rc.name AS receipt_customer_name FROM receipt r INNER JOIN receipt_customer rc on r.receipt_customer_id=rc.id";
    public static final String SQL_QUERY_GET_RECEIPTS_IN_CURRENT_DAY = "SELECT * FROM receiptes_database.receipt where date_of_service = curdate()";
    public static final String SQL_QUERY_GET_RECEIPTS_IN_CURRENT_MONTH = "SELECT * FROM receiptes_database.receipt where date_of_service > LAST_DAY(DATE_SUB(CURDATE(), INTERVAL 1 MONTH))" +
            "AND date_of_service < DATE_ADD(LAST_DAY(CURDATE()), INTERVAL 1 DAY)";
    public static final String SQL_QUERY_GET_RECEIPTS_IN_CURRENT_CUARTER = "SELECT * FROM receiptes_database.receipt where year(date_of_service) = year(now()) and quarter(date_of_service)= quarter(now())";
    public static final String SQL_QUERY_DELETE_RECEIPT_BY_ID = "delete from receiptes_database.receipt  where number = '?'";

}
