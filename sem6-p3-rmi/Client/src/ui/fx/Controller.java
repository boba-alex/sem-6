package ui.fx;

import entities.Receipt;
import entities.ReceiptCustomer;
import entities.ReceiptService;
import services.MyRemoteService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * <p>Класс контроллер на основе JavaFX, реализующий интерфейс {@link Initializable}
 * служит для <b>хранения процедур</b>,
 * к которым можно обращаться средствами <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>.</p>
 *
 * @author Polischuk Alexander
 * @version 1.0
 */

public class Controller implements Initializable {

    /**
     * Remote интерфейс {@link MyRemoteService}  служит для связи с сервером и
     * вызова функций обращения к <b>данным</b>.
     */
    private MyRemoteService myService;

    /**
     * GUI-список для отображения загруженных данных.
     */
    @FXML
    ListView<Receipt> list1 = new ListView<>();

    /**
     * GUI-список для отображения выборки из загруженного списка согласно некторым критериям.
     */
    @FXML
    ListView<Receipt> list2 = new ListView<>();

    /**
     * Загрузка квитанций из базы данных.
     */
    @FXML
    private void loadReceiptsFromDatabase() {
        try {
            updateList1((ArrayList<Receipt>) myService.getAllReceipts());
            clearFormAfterUpdateList1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для выхода из приложения.
     */
    @FXML
    private void exitApp() {
        Platform.exit();
    }

    /**
     * Метод для обновления GUI-списка квитанций за текущий день.
     */
    @FXML
    private void loadReceiptsInCurrentDay() {
        try {
            updateList2((ArrayList<Receipt>) myService.getReceiptsInCurrentDay());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для обновления GUI-списка квитанций за текущий месяц.
     */
    @FXML
    private void loadReceiptsInCurrentMonth() {
        try {
            updateList2((ArrayList<Receipt>) myService.getReceiptsInCurrentMonth());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для обновления GUI-списка квитанций за текущий квартал.
     */
    @FXML
    private void loadReceiptsInCurrentQuarter() {
        try {
            updateList2((ArrayList<Receipt>) myService.getReceiptsInCurrentQuarter());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для удаления выбранной квитанции.
     */
    @FXML
    private void deleteChosenReceipt() {
        try {
            String selection = list1.getFocusModel().getFocusedItem().toString();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + selection + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                Receipt chosenReceipt = list1.getFocusModel().getFocusedItem();
                myService.deleteReceipt(chosenReceipt);
                updateList1((ArrayList<Receipt>) myService.getAllReceipts());
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Please, u should load smth", ButtonType.YES);
            alert.showAndWait();
        }

    }

    /**
     * Метод для добавления нового клиента.
     * При этом создается новое модальное диалоговое окно для ввода требуемых параметров инициализации клиента.
     */
    @FXML
    private void addCustomer() {
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
            if (!name.isEmpty() && !surname.isEmpty()) {
                ReceiptCustomer receiptCustomer = new ReceiptCustomer(name, surname);
                try {
                    myService.addReceiptCustomer(receiptCustomer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Empty field", ButtonType.YES);
                alert.showAndWait();
            }
        }

    }

    /**
     * Метод для добавления новой квитанции.
     * При этом создается новое модальное диалоговое окно для ввода требуемых параметров инициализации квитанции.
     */
    @FXML
    private void addReceipt() {
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
                null;
        try {
            receiptServices = FXCollections.observableArrayList(myService.getReceiptServices());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ComboBox<ReceiptService> comboBox1 = new ComboBox(receiptServices);
        ObservableList<ReceiptCustomer> receiptCustomers =
                null;
        try {
            receiptCustomers = FXCollections.observableArrayList(myService.getReceiptCurstomers());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            try {
                int day = Integer.parseInt(text1.getText().replaceAll(" ", ""));
                int month = Integer.parseInt(text2.getText().replaceAll(" ", ""));
                int year = Integer.parseInt(text3.getText().replaceAll(" ", ""));

                ReceiptService receiptService = comboBox1.getSelectionModel().getSelectedItem();
                ReceiptCustomer receiptCustomer = comboBox2.getSelectionModel().getSelectedItem();

                if (receiptService != null && receiptCustomer != null) {
                    myService.addReceipt(new Receipt(receiptService, receiptCustomer, LocalDate.of(year, month, day)));
                    updateList1(((ArrayList<Receipt>) myService.getAllReceipts()));
                } else throw new NumberFormatException();

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Please, u should input correct data", ButtonType.YES);
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод для обновления GUI-списка квитанций.
     *
     * @param receipts новый список квитанций
     */
    private void updateList1(ArrayList<Receipt> receipts) {
        list1.setItems(FXCollections.observableArrayList(receipts));
    }

    /**
     * Метод для очищения всех элементов, отображение которых зависело
     * от GUI-списка, который отображает загруженные данные из базы данных.
     */
    private void clearFormAfterUpdateList1() {
        list2.getItems().clear();
    }

    /**
     * Метод для обновления GUI-списка квитанций, который отображается согласно определенному правилу.
     *
     * @param receipts новый список квитанций
     */
    private void updateList2(ArrayList<Receipt> receipts) {
        list2.setItems(FXCollections.observableArrayList(receipts));
    }

    /**
     * Метод инициализации {@link Controller}. Создание экземпляра сервиса клиента.
     *
     * @param location  место хранения ресурсов
     * @param resources для связывания ресурсов
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Registry registry = null;
        try {
            //registry = LocateRegistry.getRegistry("192.168.43.34", 4396);
            registry = LocateRegistry.getRegistry("localhost", 4396);
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
        try {
            assert registry != null;
            System.out.println(registry.lookup("local/MyService").getClass());
            myService = (MyRemoteService) registry.lookup("local/MyService");
        } catch (RemoteException | NotBoundException e) {
            System.err.println("error " + e.getMessage());

            System.exit(0);
        }
    }
}
