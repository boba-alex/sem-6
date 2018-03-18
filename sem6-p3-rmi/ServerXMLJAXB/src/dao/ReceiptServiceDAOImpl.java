package dao;

import constants.ConstantsXML;
import entities.ReceiptService;
import entities.WrapperReceiptServices;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by User on 20.02.2018.
 */
public class ReceiptServiceDAOImpl implements ReceiptServiceDAO {
    private String xmlLocation = ConstantsXML.RECEIPT_SERVICES_XML;
    private String schemaLocation = ConstantsXML.RECEIPT_SERVICES_XSD;

    private JAXBContext context;

    public ReceiptServiceDAOImpl() {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaFile = new File(schemaLocation);
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(new File(xmlLocation));
            validator.validate(source);
            context = JAXBContext.newInstance(WrapperReceiptServices.class);
            System.out.println("ReceiptServiceDAOImpl Created");
        } catch (SAXException | IOException | JAXBException e) {
            System.out.println("XML file is not valid.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ReceiptService> getReceiptServices() {
        WrapperReceiptServices wrapperReceiptServices = new WrapperReceiptServices();
        try {
            File file = new File(xmlLocation);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            wrapperReceiptServices = (WrapperReceiptServices) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return wrapperReceiptServices.getReceiptServices();
    }

    public static void main(String[] args) {
        System.out.println(new ReceiptServiceDAOImpl().getReceiptServices());
    }

}
