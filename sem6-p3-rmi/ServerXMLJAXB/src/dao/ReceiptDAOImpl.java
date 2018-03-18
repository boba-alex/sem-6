package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import constants.ConstantsXML;
import entities.Receipt;
import entities.WrapperReceipts;
import exceptions.MyDAOException;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDAOImpl implements ReceiptDAO {

    private String xmlLocation = ConstantsXML.RECEIPTS_XML;
    private String schemaLocation = ConstantsXML.RECEIPTS_XSD;

    private JAXBContext context;

    public ReceiptDAOImpl() throws MyDAOException {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaFile = new File(schemaLocation);
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(new File(xmlLocation));
            validator.validate(source);
            context = JAXBContext.newInstance(WrapperReceipts.class);
            System.out.println("ReceiptDAOImpl Created");
        } catch (SAXException | IOException | JAXBException e) {
            System.out.println("XML file is not valid.");
            throw new MyDAOException(e.getMessage());
        }
    }

    @Override
    public List<Receipt> getAllReceipts() throws MyDAOException {
        WrapperReceipts wrapperReceipts = new WrapperReceipts();
        try {
            File file = new File(xmlLocation);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            wrapperReceipts = (WrapperReceipts) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new MyDAOException(e.getMessage());
        }
        return wrapperReceipts.getReceipts();
    }

    @Override
    public List<Receipt> getReceiptsInCurrentDay() throws MyDAOException {
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
    public List<Receipt> getReceiptsInCurrentMonth() throws MyDAOException {
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
    public List<Receipt> getReceiptsInCurrentQuarter() throws MyDAOException {
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
    public void addReceipt(Receipt receipt) throws MyDAOException {
        try {
            File file = new File(xmlLocation);
            ArrayList<Receipt> receipts = (ArrayList<Receipt>) getAllReceipts();
            receipt.setId(getMaxId(receipts) + 1);
            receipts.add(receipt);

            WrapperReceipts wrapperReceipts = new WrapperReceipts();
            wrapperReceipts.setReceipts(receipts);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(wrapperReceipts, file);
        } catch (JAXBException e) {
            throw new MyDAOException(e.getMessage());
        }
    }

    @Override
    public void deleteReceipt(Receipt receipt) throws MyDAOException {
        try {
            File file = new File(xmlLocation);
            ArrayList<Receipt> receipts = (ArrayList<Receipt>) getAllReceipts();
            receipts.removeIf(receipt1 -> receipt1.getId() == receipt.getId());

            WrapperReceipts wrapperReceipts = new WrapperReceipts();
            wrapperReceipts.setReceipts(receipts);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(wrapperReceipts, file);
        } catch (JAXBException e) {
            throw new MyDAOException(e.getMessage());
        }
    }

    private int getMaxId(ArrayList<Receipt> receipts) {
        int maxElementNumber = 0;

        for (Receipt receipt : receipts) {
            int curId = receipt.getId();
            if (curId > maxElementNumber) {
                maxElementNumber = curId;
            }
        }
        return maxElementNumber;
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




