package dao;

import constants.ConstantsXML;
import entities.ReceiptCustomer;
import entities.ReceiptService;
import org.xml.sax.SAXException;

import javax.swing.*;
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
import java.rmi.RemoteException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 20.02.2018.
 */
public class ReceiptCustomerDAOImpl implements ReceiptCustomerDAO {

    private String xmlLocation = ConstantsXML.RECEIPT_CUSTOMERS_XML;
    private XMLInputFactory inputFactory;
    private XMLOutputFactory outputFactory;
    private XMLEventFactory eventFactory;
    private XMLEventWriter writer;
    private XMLEventReader reader;

    public ReceiptCustomerDAOImpl() {
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File schemaLocation = new File(ConstantsXML.RECEIPT_CUSTOMERS_XSD);
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
            System.out.println("ReceiptCustomerDAOImpl Created");
        } catch (SAXException | IOException e) {
            System.out.println("XML file is not valid.");
            throw new RuntimeException(e);
        }

        inputFactory = XMLInputFactory.newInstance();
        outputFactory = XMLOutputFactory.newInstance();
        eventFactory = XMLEventFactory.newInstance();
    }

    @Override
    public List<ReceiptCustomer> getReceiptCustomers() {
        List<ReceiptCustomer> receiptCustomers = new ArrayList<>();
        ReceiptCustomer receiptCustomer = null;
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
                    if (startElement.getName().getLocalPart().equals("ReceiptCustomer")) {

                        receiptCustomer = new ReceiptCustomer();
                        //get attribute id for each element ReceiptCustomer
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        if (idAttr != null) {
                            receiptCustomer.setId(Integer.parseInt(idAttr.getValue()));
                        }
                    } else if (startElement.getName().getLocalPart().equals("name")) {
                        xmlEvent = reader.nextEvent();
                        receiptCustomer.setName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("surname")) {
                        xmlEvent = reader.nextEvent();
                        receiptCustomer.setSurname(xmlEvent.asCharacters().getData());
                    }
                }
                //if we find closed ReceiptCustomer, add it to collection
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("ReceiptCustomer")) {
                        receiptCustomers.add(receiptCustomer);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return receiptCustomers;
    }

    @Override
    public void addReceiptCustomer(ReceiptCustomer receiptCustomer) {
        int maxElementNumber = 0;
        try {
            reader = inputFactory.createXMLEventReader(new FileReader(xmlLocation));
            writer = outputFactory.createXMLEventWriter(new FileWriter(xmlLocation));
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLEvent.START_ELEMENT:
                        StartElement startElement1 = event.asStartElement();
                        String tagName1 = startElement1.getName().getLocalPart();
                        switch (tagName1) {
                            case "ReceiptCustomer":
                                Attribute attribute = startElement1.getAttributeByName(QName.valueOf("id"));
                                int buf = Integer.valueOf(attribute.getValue());
                                System.out.println("buf: " + buf);
                                if (buf > maxElementNumber) {
                                    maxElementNumber = buf;
                                }
                                break;
                            default:
                                break;
                        }
                        //and now break because START_ELEMENT We should break;
                        break;
                    case XMLEvent.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        String tagName = endElement.getName().getLocalPart();
                        switch (tagName) {
                            case "ReceiptCustomers":
                                receiptCustomer.setId(maxElementNumber + 1);
                                writeReceiptCustomer(receiptCustomer);
                                writeSimpleTag("ReceiptCustomers", false);
                                //and now return so we must not read anymore!
                                return;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
                writer.add(event);
            }
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (XMLStreamException e) {
                    System.err.println(e.getMessage());
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (XMLStreamException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    private void writeReceiptCustomer(ReceiptCustomer receiptCustomer) throws XMLStreamException, ParseException {
        XMLEvent newLineSequence = eventFactory.createCharacters("\n");
        XMLEvent tabSequence = eventFactory.createCharacters("\t");
        writer.add(tabSequence);
        writeSimpleTag("ReceiptCustomer", true);
        XMLEvent event = null;
        if (receiptCustomer.getId() == 0) {
            event = eventFactory.createAttribute("id", Integer.toString(getReceiptCustomers().size()));
        } else {
            event = eventFactory.createAttribute("id", Integer.toString(receiptCustomer.getId()));
        }
        writer.add(event);
        writer.add(newLineSequence);
        writeSimpleElement("name", receiptCustomer.getName());
        writer.add(newLineSequence);
        writeSimpleElement("surname", receiptCustomer.getSurname());
        writer.add(newLineSequence);
        writer.add(tabSequence);
        writeSimpleTag("ReceiptCustomer", false);
        writer.add(newLineSequence);
    }

    private void writeSimpleTag(String tagName, boolean isOpen) throws XMLStreamException {
        XMLEvent event;
        if (isOpen) {
            QName name = QName.valueOf(tagName);
            event = eventFactory.createStartElement(name.getPrefix(), name.getNamespaceURI(), name.getLocalPart());
        } else {
            QName name = QName.valueOf(tagName);
            event = eventFactory.createEndElement(name.getPrefix(), name.getNamespaceURI(), name.getLocalPart());
        }
        writer.add(event);
    }

    private void writeSimpleElement(String tagName, String value) throws XMLStreamException {
        XMLEvent controlSeq1 = eventFactory.createCharacters("\t\t");
        writer.add(controlSeq1);
        writeSimpleTag(tagName, true);
        XMLEvent event = eventFactory.createCharacters(value);
        writer.add(event);
        writeSimpleTag(tagName, false);
    }
}
