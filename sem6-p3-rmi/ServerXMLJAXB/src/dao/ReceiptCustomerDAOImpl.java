package dao;

import constants.ConstantsXML;
import entities.ReceiptCustomer;
import entities.WrapperReceiptCustomers;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 20.02.2018.
 */
public class ReceiptCustomerDAOImpl implements ReceiptCustomerDAO {

    private String xmlLocation = ConstantsXML.RECEIPT_CUSTOMERS_XML;
    private String schemaLocation = ConstantsXML.RECEIPT_CUSTOMERS_XSD;

    private JAXBContext context;

    public ReceiptCustomerDAOImpl() {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaFile = new File(schemaLocation);
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(new File(xmlLocation));
            validator.validate(source);
            context = JAXBContext.newInstance(WrapperReceiptCustomers.class);
            System.out.println("ReceiptCustomerDAOImpl Created");
        } catch (SAXException | IOException | JAXBException e) {
            System.out.println("XML file is not valid.");
            throw new RuntimeException(e);
        }
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
        try {
            File file = new File(xmlLocation);
            ArrayList<ReceiptCustomer> receiptCustomers = (ArrayList<ReceiptCustomer>) getReceiptCustomers();
            receiptCustomer.setId(getMaxId(receiptCustomers) + 1);
            receiptCustomers.add(receiptCustomer);

            WrapperReceiptCustomers wrapperReceiptCustomers = new WrapperReceiptCustomers();
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
