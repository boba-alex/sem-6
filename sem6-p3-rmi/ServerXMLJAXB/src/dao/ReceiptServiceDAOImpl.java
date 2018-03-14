package dao;

import constants.ConstantsXML;
import entities.ReceiptService;
import org.xml.sax.SAXException;

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

    public ReceiptServiceDAOImpl() {
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File schemaLocation = new File(ConstantsXML.RECEIPT_SERVICES_XSD);
        Schema schema;
        try {
            schema = factory.newSchema(schemaLocation);
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
        List<ReceiptService> receiptServices = new ArrayList<>();
        ReceiptService receiptService = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        try {
            //initialize reader and feed it with xml-file
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(xmlLocation));
            // go for all elements of xml-file
            while (reader.hasNext()) {
                //getting event(element) and destruct it
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("ReceiptService")) {
                        receiptService = new ReceiptService();
                        //get attribute id for each element ReceiptService
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        if (idAttr != null) {
                            receiptService.setId(Integer.parseInt(idAttr.getValue()));
                        }
                    } else if (startElement.getName().getLocalPart().equals("servicename")) {
                        xmlEvent = reader.nextEvent();
                        receiptService.setName(xmlEvent.asCharacters().getData());
                    }
                }
                //if we find closed ReceiptService, add it to collection
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("ReceiptService")) {
                        receiptServices.add(receiptService);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return receiptServices;
    }

}
