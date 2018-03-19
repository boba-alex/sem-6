package dao;

import constants.ConstantsXML;
import entities.WrapperReceiptServices;
import exceptions.MyDAOException;
import entities.ReceiptService;
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

    /**
     * Контекст класса-обертки
     *
     * @see WrapperReceiptServices ,JAXBContext
     */
    private JAXBContext context;

    /**
     * Проверка валидности XML по схеме и получение контекста
     *
     * @see WrapperReceiptServices,JAXBContext
     * @throws MyDAOException при неправильном XML файле
     */
    public ReceiptServiceDAOImpl() throws MyDAOException {
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
            throw new MyDAOException(e.getMessage());
        }
    }

    @Override
    public List<ReceiptService> getReceiptServices() throws MyDAOException {
        WrapperReceiptServices wrapperReceiptServices = new WrapperReceiptServices();
        try {
            File file = new File(xmlLocation);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            wrapperReceiptServices = (WrapperReceiptServices) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new MyDAOException(e.getMessage());
        }
        return wrapperReceiptServices.getReceiptServices();
    }

}
