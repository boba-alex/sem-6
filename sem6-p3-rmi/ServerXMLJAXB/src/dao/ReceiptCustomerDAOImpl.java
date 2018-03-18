package dao;

import constants.ConstantsXML;
import entities.ReceiptCustomer;
import entities.ReceiptService;
import entities.WrapperReceiptCustomers;
import entities.WrapperReceiptServices;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 20.02.2018.
 */
public class ReceiptCustomerDAOImpl implements ReceiptCustomerDAO {

    private String xmlLocation = ConstantsXML.RECEIPT_CUSTOMERS_XML;
    private String schemaLocation = ConstantsXML.RECEIPT_CUSTOMERS_XSD;

    private JAXBContext context;

    public ReceiptCustomerDAOImpl

    {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File schemaFile = new File(schemaLocation);
        Schema schema;
        try {
            schema = factory.newSchema(schemaFile);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        Validator validator = schema.newValidator();
        Source source = new StreamSource(new File(xmlLocation));
        try {
            validator.validate(source);
            System.out.println("ReceiptCustomerDAOImpl Created");
        } catch (SAXException | IOException e) {
            System.out.println("XML file is not valid.");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        ReceiptService receiptService = new ReceiptService("AAA");
//
//        try {
//            JAXBContext context = JAXBContext.newInstance(ReceiptService.class);
//            Marshaller marshaller = context.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            marshaller.marshal(receiptService, new File(ConstantsXML.RECEIPT_SERVICES_XML));
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
        new ReceiptServiceDAOImpl();
    }

    @Override
    public List<ReceiptCustomer> getReceiptCustomers() {
        WrapperReceiptCustomers wrapperReceiptCustomers = new WrapperReceiptCustomers();
        try {
            File file = new File(xmlLocation);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            wrapperReceiptCustomers = (WrapperReceiptCustomers) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return wrapperReceiptCustomers.getReceiptCustomers();
    }

    @Override
    public void addReceiptCustomer(ReceiptCustomer receiptCustomer) {
        WrapperReceiptCustomers wrapperReceiptCustomers = new WrapperReceiptCustomers();
        try {
            File file = new File(xmlLocation);
            ArrayList<ReceiptCustomer> receiptCustomers = (ArrayList<ReceiptCustomer>) getReceiptCustomers();
            receiptCustomer.setId(getMaxId(receiptCustomers) + 1);
            receiptCustomers.add(receiptCustomer);

            wrapperReceiptCustomers.setReceiptCustomers(receiptCustomers);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(wrapperReceiptCustomers, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    private int getMaxId(ArrayList<ReceiptCustomer> receiptCustomers) {
        int maxElementNumber = 0;

        for (ReceiptCustomer receiptCustomer : receiptCustomers) {
            int curId = receiptCustomer.getId();
            if (curId > maxElementNumber) {
                maxElementNumber = curId;
            }
        }
        return maxElementNumber;
    }


}
