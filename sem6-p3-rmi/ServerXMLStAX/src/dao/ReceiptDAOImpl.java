package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import entities.Receipt;
import entities.ReceiptCustomer;
import entities.ReceiptService;
import constants.ConstantsXML;
import org.xml.sax.SAXException;

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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDAOImpl implements ReceiptDAO {

    private String xmlLocation = ConstantsXML.RECEIPTS_XML;
    private XMLInputFactory inputFactory;
    private XMLOutputFactory outputFactory;
    private XMLEventFactory eventFactory;
    private XMLEventWriter writer;
    private XMLEventReader reader;

    public ReceiptDAOImpl() {
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File schemaLocation = new File(ConstantsXML.RECEIPTS_XSD);
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
            System.out.println("ReceiptDAOImpl Created");
        } catch (SAXException | IOException e) {
            System.out.println("XML file is not valid.");
            throw new RuntimeException(e);
        }

        inputFactory = XMLInputFactory.newInstance();
        outputFactory = XMLOutputFactory.newInstance();
        eventFactory = XMLEventFactory.newInstance();
    }

    @Override
    public List<Receipt> getAllReceipts() {
        List<Receipt> receipts = new ArrayList<>();
        Receipt receipt = null;
        ReceiptService receiptService = null;
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
                    if (startElement.getName().getLocalPart().equals("Receipt")) {

                        receipt = new Receipt();
                        //get attribute id for each element ReceiptCustomer
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        if (idAttr != null) {
                            receipt.setId(Integer.parseInt(idAttr.getValue()));
                        }
                    } else if (startElement.getName().getLocalPart().equals("ReceiptService")) {
                        receiptService = new ReceiptService();
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        if (idAttr != null) {
                            receiptService.setId(Integer.parseInt(idAttr.getValue()));
                        }
                    } else if (startElement.getName().getLocalPart().equals("ReceiptCustomer")) {
                        receiptCustomer = new ReceiptCustomer();
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        if (idAttr != null) {
                            receiptCustomer.setId(Integer.parseInt(idAttr.getValue()));
                        }
                    } else if (startElement.getName().getLocalPart().equals("DateOfReceiptService")) {
                        xmlEvent = reader.nextEvent();
                        receipt.setDate(LocalDate.parse(xmlEvent.asCharacters().getData(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    } else if (startElement.getName().getLocalPart().equals("servicename")) {
                        xmlEvent = reader.nextEvent();
                        receiptService.setName(xmlEvent.asCharacters().getData());
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
                    if (endElement.getName().getLocalPart().equals("Receipt")) {
                        receipt.addReceiptService(receiptService);
                        receipt.addReceiptCustomer(receiptCustomer);
                        receipts.add(receipt);
                    }
                }
            }

        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return receipts;
    }

    @Override
    public List<Receipt> getReceiptsInCurrentDay() {
        ArrayList<Receipt> receipts = (ArrayList<Receipt>) getAllReceipts();
        ArrayList<Receipt> result = new ArrayList<>();
        for (Receipt receipt : receipts) {
            if (compareByDay(receipt.getDate(), LocalDate.now()) == 0) {
                result.add(receipt);
            }
        }
        return result;
    }

    @Override
    public List<Receipt> getReceiptsInCurrentMonth() {
        ArrayList<Receipt> receipts = (ArrayList<Receipt>) getAllReceipts();
        ArrayList<Receipt> result = new ArrayList<>();
        for (Receipt receipt : receipts) {
            if (compareByMonth(receipt.getDate(), LocalDate.now()) == 0) {
                result.add(receipt);
            }
        }
        return result;
    }

    @Override
    public List<Receipt> getReceiptsInCurrentQuarter() {
        ArrayList<Receipt> receipts = (ArrayList<Receipt>) getAllReceipts();
        ArrayList<Receipt> result = new ArrayList<>();
        for (Receipt receipt : receipts) {
            if (compareByQuarter(receipt.getDate(), LocalDate.now()) == 0) {
                result.add(receipt);
            }
        }
        return result;
    }

    @Override
    public void addReceipt(Receipt receipt) {
        int maxElementNumber = 0;
        try {
            reader = inputFactory.createXMLEventReader(new FileReader(xmlLocation));
            writer = outputFactory.createXMLEventWriter(new FileWriter(xmlLocation));
            while (reader.hasNext()) {
                System.out.println("NEXT");
                System.out.println(reader);
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLEvent.START_ELEMENT:
                        StartElement startElement1 = event.asStartElement();
                        String tagName1 = startElement1.getName().getLocalPart();
                        switch (tagName1) {
                            case "Receipt":
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
                            case "Receipts":
                                receipt.setId(maxElementNumber + 1);
                                writeReceipt(receipt);
                                writeSimpleTag("Receipts", false);
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

    @Override
    public void deleteReceipt(Receipt receipt) {
        try {
            reader = inputFactory.createXMLEventReader(new FileReader(xmlLocation));
            File bufferFile = new File(xmlLocation);
            writer = outputFactory.createXMLEventWriter(new FileWriter(bufferFile));
            String tagName;
            boolean flag = false;
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLEvent.START_ELEMENT:
                        if (flag) {
                            continue;
                        }
                        StartElement element = event.asStartElement();
                        tagName = element.getName().getLocalPart();
                        switch (tagName) {
                            case "Receipt":
                                long currId = Integer.parseInt(element.getAttributeByName(QName.valueOf("id")).getValue());
                                if (currId == receipt.getId()) {
                                    flag = true;
                                    continue;
                                }
                        }
                        break;
                    case XMLEvent.END_ELEMENT:
                        EndElement element1 = event.asEndElement();
                        if (flag) {
                            if ("Receipt".equals(element1.getName().getLocalPart())) {
                                flag = false;
                            }
                            continue;
                        }
                        break;
                    case XMLEvent.CHARACTERS:
                        if (flag) {
                            continue;
                        }
                        break;
                }
                writer.add(event);
            }
        } catch (XMLStreamException | IOException e) {
            System.err.println(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void writeReceipt(Receipt receipt) throws XMLStreamException, ParseException {
        XMLEvent newLineSequence = eventFactory.createCharacters("\n");
        XMLEvent tabSequence = eventFactory.createCharacters("\t");
        writer.add(tabSequence);
        writeSimpleTag("r:Receipt", true);
        XMLEvent event = null;
        event = eventFactory.createAttribute("id", Integer.toString(receipt.getId()));
        writer.add(event);
        writer.add(newLineSequence);
        if (receipt.getReceiptService().getId() == 0) {
            receipt.getReceiptService().setId(receipt.getId());
        }
        if (receipt.getReceiptCustomer().getId() == 0) {
            receipt.getReceiptCustomer().setId(receipt.getId());
        }
        writeReceiptService(receipt.getReceiptService());
        writeReceiptCustomer(receipt.getReceiptCustomer());
        writeSimpleElement("r:DateOfReceiptService", receipt.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), "\t\t");
        writer.add(newLineSequence);
        writer.add(tabSequence);
        writeSimpleTag("r:Receipt", false);
        writer.add(newLineSequence);
    }

    private void writeReceiptService(ReceiptService receiptService) throws XMLStreamException, ParseException {
        XMLEvent newLineSequence = eventFactory.createCharacters("\n");
        XMLEvent tabSequence = eventFactory.createCharacters("\t");
        writer.add(tabSequence);
        writer.add(tabSequence);
        writeSimpleTag("r:ReceiptService", true);
        XMLEvent event = null;
        event = eventFactory.createAttribute("id", Integer.toString(receiptService.getId()));
        writer.add(event);
        writer.add(newLineSequence);
        writeSimpleElement("rs:servicename", receiptService.getName(), "\t\t\t");
        writer.add(newLineSequence);
        writer.add(tabSequence);
        writer.add(tabSequence);
        writeSimpleTag("r:ReceiptService", false);
        writer.add(newLineSequence);
    }

    private void writeReceiptCustomer(ReceiptCustomer receiptCustomer) throws XMLStreamException, ParseException {
        XMLEvent newLineSequence = eventFactory.createCharacters("\n");
        XMLEvent tabSequence = eventFactory.createCharacters("\t");
        writer.add(tabSequence);
        writer.add(tabSequence);
        writeSimpleTag("r:ReceiptCustomer", true);
        XMLEvent event = null;
        event = eventFactory.createAttribute("id", Integer.toString(receiptCustomer.getId()));
        writer.add(event);
        writer.add(newLineSequence);
        writeSimpleElement("rc:name", receiptCustomer.getName(), "\t\t\t");
        writer.add(newLineSequence);
        writeSimpleElement("rc:surname", receiptCustomer.getSurname(), "\t\t\t");
        writer.add(newLineSequence);
        writer.add(tabSequence);
        writer.add(tabSequence);
        writeSimpleTag("r:ReceiptCustomer", false);
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

    private void writeSimpleElement(String tagName, String value, String controlSeq) throws XMLStreamException {
        XMLEvent controlSeq1 = eventFactory.createCharacters(controlSeq);
        writer.add(controlSeq1);
        writeSimpleTag(tagName, true);
        XMLEvent event = eventFactory.createCharacters(value);
        writer.add(event);
        writeSimpleTag(tagName, false);
    }

    private int compareByDay(LocalDate localDate1, LocalDate localDate2) {
        if (localDate1.getYear() != localDate2.getYear())
            return Integer.compare(localDate1.getYear(), localDate2.getYear());
        if (localDate1.getMonthValue() != localDate2.getMonthValue())
            return Integer.compare(localDate1.getMonthValue(), localDate2.getMonthValue());
        return Integer.compare(localDate1.getDayOfMonth(), localDate2.getDayOfMonth());
    }

    private int compareByMonth(LocalDate localDate1, LocalDate localDate2) {
        if (localDate1.getYear() != localDate2.getYear())
            return Integer.compare(localDate1.getYear(), localDate2.getYear());
        return Integer.compare(localDate1.getMonthValue(), localDate2.getMonthValue());
    }

    private int compareByQuarter(LocalDate localDate1, LocalDate localDate2) {
        if (localDate1.getYear() != localDate2.getYear())
            return Integer.compare(localDate1.getYear(), localDate2.getYear());
        return Integer.compare((localDate1.getMonthValue() - 1) / 3, (localDate2.getMonthValue() - 1) / 3);//по кварталам смотрим, начиная с 0 по 11 месяц.
    }
}




