package constants;

/**
 * <p>Класс констант, необходимых для работы с XML-данными по технологии JAXB.
 * Здесь имеются ссылки на схемы для проверки корректности данных, а также ссылки на XML-файлы.
 *
 * @author Polischuk Alexander
 * @version 1.0
 */
public class ConstantsXML {
    /**
     * Расположение XML баз данных и XSD схем
     */
    public static final String RECEIPT_SERVICES_XML = "ServerXMLJAXB/xml/receipt_services.xml";
    public static final String RECEIPT_SERVICES_XSD = "ServerXMLJAXB/xml/receipt_services.xsd";
    public static final String RECEIPT_CUSTOMERS_XML = "ServerXMLJAXB/xml/receipt_customers.xml";
    public static final String RECEIPT_CUSTOMERS_XSD = "ServerXMLJAXB/xml/receipt_customers.xsd";
    public static final String RECEIPTS_XML = "ServerXMLJAXB/xml/receipts.xml";
    public static final String RECEIPTS_XSD = "ServerXMLJAXB/xml/receipts.xsd";
}
