package main.constants;

public class ConstantsSQL {
    public static final String URL_DATABASE = "jdbc:mysql://localhost:3306/cwdeals?useSSL=false";
    public static final String LOGIN = "root";
    public static final String PASSWORD = "root";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String SQL_QUERY_ADD_RECEIPT = "INSERT INTO receipt (receipt_service_id, date_of_receipt_service, receipt_customer_id) VALUES (?,?,?)";
    public static final String SQL_QUERY_ADD_RECEIPT_CUSTOMER = "INSERT INTO receipt_customer (name, surname) VALUES (?,?)";
    public static final String SQL_QUERY_GET_RECEIPTS = "SELECT r.id, r.receipt_service_id, r.date_of_receipt_service , r.receipt_customer_id, " +
            "rc.name AS receipt_customer_name, rc.surname AS receipt_customer_surname, rs.name AS receipt_service_name " +
            "FROM receipt r INNER JOIN receipt_customer rc on r.receipt_customer_id=rc.id " +
            "INNER JOIN receipt_service rs on r.receipt_service_id=rs.id ";
    public static final String SQL_QUERY_GET_RECEIPTS_IN_CURRENT_DAY = SQL_QUERY_GET_RECEIPTS + "where date_of_receipt_service = curdate()";
    public static final String SQL_QUERY_GET_RECEIPTS_IN_CURRENT_MONTH = SQL_QUERY_GET_RECEIPTS +"where date_of_receipt_service > LAST_DAY(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) " +
            "AND date_of_receipt_service < DATE_ADD(LAST_DAY(CURDATE()), INTERVAL 1 DAY)";
    public static final String SQL_QUERY_GET_RECEIPTS_IN_CURRENT_CUARTER = SQL_QUERY_GET_RECEIPTS +"where year(date_of_receipt_service) = year(now()) and quarter(date_of_receipt_service)= quarter(now())";
    public static final String SQL_QUERY_DELETE_RECEIPT_BY_ID = "delete from receipts_database.receipt where id = ?";
    public static final String SQL_QUERY_GET_RECEIPT_CUSTOMERS = "select * from receipt_customer";
    public static final String SQL_QUERY_GET_RECEIPT_SERVICES = "select * from receipt_service";

    public static final String SQL_QUERY_GET_RECEIPT_SERVICE_BY_ID = "select * from receipt_service where id = ?";
    public static final String SQL_QUERY_GET_RECEIPT_CUSTOMER_BY_ID = "select * from receipt_customer where id = ?";
    public static final String SQL_QUERY_GET_RECEIPT_BY_ID = SQL_QUERY_GET_RECEIPTS + "where r.id = ?";

    public static final String SQL_QUERY_GET_DEALS = "SELECT * FROM deal";
//    public static final String SQL_QUERY_ADD_STUDENT = "INSERT INTO student (surname, name, patronymic, course, student_group, average_mark, from_country) VALUES (?,?,?,?,?,?,?)";
    public static final String SQL_QUERY_COUNT_ALL_DEALS = "SELECT COUNT(*) FROM cwdeals.deal WHERE type='Купля'";
    public static final String SQL_QUERY_SUM_ALL_DEALS = "SELECT SUM(sum) FROM cwdeals.deal WHERE type='Купля'";

}
