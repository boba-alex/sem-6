package constants;

/**
 * <p>Класс констант, необходимых для работы с XML-данными по технологии StAX.
 * Здесь имеются ссылки на схемы для проверки корректности данных, а также ссылки на XML-файлы.
 *
 * @author Polischuk Alexander
 * @version 1.0
 */
public class ConstantsXML {
    /**
     * Расположение XML баз данных и XSD схем
     */
    public static final String RECEIPT_SERVICES_XML = "ServerXMLStAX/xml/receipt_services.xml";
    public static final String RECEIPT_SERVICES_XSD = "ServerXMLStAX/xml/receipt_services.xsd";
    public static final String RECEIPT_CUSTOMERS_XML = "ServerXMLStAX/xml/receipt_customers.xml";
    public static final String RECEIPT_CUSTOMERS_XSD = "ServerXMLStAX/xml/receipt_customers.xsd";
    public static final String RECEIPTS_XML = "ServerXMLStAX/xml/receipts.xml";
    public static final String RECEIPTS_XSD = "ServerXMLStAX/xml/receipts.xsd";
}
