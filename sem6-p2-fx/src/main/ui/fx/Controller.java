package main.ui.fx;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.GridPane;
import main.entities.Receipt;
import main.entities.ReceiptCustomer;
import main.entities.ReceiptService;
import main.services.MyService;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller {

    private MyService myService = new MyService();

    @FXML
    ListView<Receipt> list1 = new ListView<>();

    @FXML
    ListView<Receipt> list2 = new ListView<>();

    @FXML
    private void loadReceiptsFromDatabase() {
        updateList1((ArrayList<Receipt>) myService.getAllReceipts());
        clearFormAfterUpdateList1();
    }

    @FXML
    private void exitApp() {
        Platform.exit();
    }

    @FXML
    private void loadReceiptsInCurrentDay() {
        updateList2((ArrayList<Receipt>) myService.getReceiptsInCurrentDay());
    }

    @FXML
    private void loadReceiptsInCurrentMonth() {
        updateList2((ArrayList<Receipt>) myService.getReceiptsInCurrentMonth());
    }

    @FXML
    private void loadReceiptsInCurrentQuarter() {
        updateList2((ArrayList<Receipt>) myService.getReceiptsInCurrentQuarter());
    }

    @FXML
    private void deleteChosenReceipt() {
        try{
            String selection = list1.getFocusModel().getFocusedItem().toString();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + selection + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                Receipt chosenReceipt = list1.getFocusModel().getFocusedItem();
                myService.deleteReceipt(chosenReceipt);
                updateList1((ArrayList<Receipt>) myService.getAllReceipts());
            }
        }
        catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Please, u should load smth", ButtonType.YES);
            alert.showAndWait();
        }

    }

    @FXML
    private void addCustomer(){
        Dialog dialog = new Dialog();
        dialog.setTitle("Add customer");
        dialog.setHeaderText("This is a custom dialog. Enter info and \n" +
                "press Okay (or click title bar 'X' for cancel).");
        dialog.setResizable(true);
        Label label1 = new Label("Name: ");
        Label label2 = new Label("Surname: ");
        TextField text1 = new TextField();
        TextField text2 = new TextField();
        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(text1, 2, 1);
        grid.add(label2, 1, 2);
        grid.add(text2, 2, 2);
        dialog.getDialogPane().setContent(grid);
        ButtonType buttonTypeOk = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.showAndWait();
        if (dialog.getResult() == buttonTypeOk) {
            String name = text1.getText().replaceAll(" ", "");
            String surname = text2.getText().replaceAll(" ", "");
            if(!name.isEmpty() && !surname.isEmpty()){
                ReceiptCustomer receiptCustomer = new ReceiptCustomer(name, surname);
                new MyService().addReceiptCustomer(receiptCustomer);
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Empty field", ButtonType.YES);
                alert.showAndWait();
            }
        }

    }

    @FXML
    private void addReceipt(){
        Dialog dialog = new Dialog();
        dialog.setTitle("Add receipt");
        dialog.setHeaderText("This is a custom dialog. Enter info and \n" +
                "press Okay (or click title bar 'X' for cancel).");
        dialog.setResizable(true);
        Label label1 = new Label("Day: ");
        Label label2 = new Label("Month: ");
        Label label3 = new Label("Year: ");
        Label label4 = new Label("Choose service: ");
        Label label5 = new Label("Choose customer: ");
        TextField text1 = new TextField();
        TextField text2 = new TextField();
        TextField text3 = new TextField();
        ObservableList<ReceiptService> receiptServices =
                FXCollections.observableArrayList(myService.getReceiptServices());
        ComboBox<ReceiptService> comboBox1 = new ComboBox(receiptServices);
        ObservableList<ReceiptCustomer> receiptCustomers =
                FXCollections.observableArrayList(myService.getReceiptCurstomers());
        ComboBox<ReceiptCustomer> comboBox2 = new ComboBox(receiptCustomers);

        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(text1, 2, 1);
        grid.add(label2, 1, 2);
        grid.add(text2, 2, 2);
        grid.add(label3, 1, 3);
        grid.add(text3, 2, 3);
        grid.add(label4, 1, 4);
        grid.add(comboBox1, 2, 4);
        grid.add(label5, 1, 5);
        grid.add(comboBox2, 2, 5);
        dialog.getDialogPane().setContent(grid);
        ButtonType buttonTypeOk = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.showAndWait();
        if (dialog.getResult() == buttonTypeOk) {
            try{
                int day = Integer.parseInt(text1.getText().replaceAll(" ", ""));
                int month = Integer.parseInt(text2.getText().replaceAll(" ", ""));
                int year = Integer.parseInt(text3.getText().replaceAll(" ", ""));

                ReceiptService receiptService = (ReceiptService)comboBox1.getSelectionModel().getSelectedItem();
                ReceiptCustomer receiptCustomer = (ReceiptCustomer)comboBox2.getSelectionModel().getSelectedItem();

                if(receiptService != null && receiptCustomer != null) {
                    new MyService().addReceipt(new Receipt(receiptService, receiptCustomer, LocalDate.of(year, month, day)));
                    updateList1(((ArrayList<Receipt>) new MyService().getAllReceipts()));
                }
                else throw new NumberFormatException();

            }
            catch(NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Please, u should input correct data", ButtonType.YES);
                alert.showAndWait();
            }
        }
    }
    private void updateList1(ArrayList<Receipt> receipts) {
        list1.setItems(FXCollections.observableArrayList(receipts));
    }

    private void clearFormAfterUpdateList1() {
        list2.getItems().clear();
        //jComboBox1.setSelectedIndex(0);
    }

    private void updateList2(ArrayList<Receipt> receipts) {
        list2.setItems(FXCollections.observableArrayList(receipts));
    }

//    private void fillComboBoxCustomer(){
//        ArrayList<ReceiptCustomer> receiptCustomers = (ArrayList<ReceiptCustomer>)new MyService().getReceiptCurstomers();
//        for(ReceiptCustomer receiptCustomer : receiptCustomers){
//            jComboBox3.addItem(receiptCustomer);
//        }
//    }


//    private void fillComboBoxService(){
//        ArrayList<ReceiptService> receiptServices = (ArrayList<ReceiptService>)new MyService().getReceiptServices();
//        for(ReceiptService receiptService : receiptServices){
//            jComboBox2.addItem(receiptService);
//        }
//    }

}
