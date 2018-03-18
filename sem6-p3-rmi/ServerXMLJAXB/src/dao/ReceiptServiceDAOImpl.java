package dao;

import constants.ConstantsXML;
import entities.ReceiptService;
import entities.WrapperReceiptServices;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 20.02.2018.
 */
public class ReceiptServiceDAOImpl implements ReceiptServiceDAO {
    private String xmlLocation = ConstantsXML.RECEIPT_SERVICES_XML;
    private String schemaLocation = ConstantsXML.RECEIPT_SERVICES_XSD;

    private JAXBContext context;

    public ReceiptServiceDAOImpl() {
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
            System.out.println("ReceiptServiceDAOImpl Created");
        } catch (SAXException | IOException e) {
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

}
