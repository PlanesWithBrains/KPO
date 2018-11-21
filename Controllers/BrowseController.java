package Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BrowseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea FieldPathIN;

    @FXML
    private Button BTNbrowseIN;

    @FXML
    private Button BTNschedule;

    @FXML
    private TextArea FieldPathOUT;

    @FXML
    private Button BTNbrowseOUT;

    @FXML
    void fileChooserIN(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));

        //директория по умолчанию
        try {
            File initialDirectory = new File(System.getProperty("user.home") + "\\Documents\\");
            fc.setInitialDirectory(initialDirectory);
        }
        catch (Exception exp){ //если MAC os
            System.out.println((char)27 + "[32m"+exp.getMessage());
        }

        File f = fc.showOpenDialog(null);
        if (f!= null){
            FieldPathIN.setText(f.getAbsolutePath()); //помещение пути в строку ввода пути файла
        }
    }

    @FXML
    void fileChooserOUT(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));

        //директория по умолчанию
        try {
            File initialDirectory = new File(System.getProperty("user.home") + "\\Documents\\");
            fc.setInitialDirectory(initialDirectory);
        }
        catch (Exception exp){ //если MAC os
            System.out.println((char)27 + "[32m"+exp.getMessage());
        }

        File f = fc.showOpenDialog(null);
        if (f!= null){
            FieldPathOUT.setText(f.getAbsolutePath()); //помещение пути в строку ввода пути файла
        }
    }


    @FXML
    void initialize() { //инициализация окна
        try {
            FieldPathIN.setText(System.getProperty("user.home") + "\\Documents\\" + "testINPUT.json");
            FieldPathOUT.setText(System.getProperty("user.home") + "\\Documents\\" + "testOUTPUT.json");
        }
        catch (Exception exp){
            System.out.println((char)27 + "[32m"+exp.getMessage());
        }

        BTNschedule.setOnAction(event -> { //если нажали кнопку сделать расписание
            BTNschedule.getScene().getWindow().hide();//старое окно убираем
            DemoController.PATH_INPUT = FieldPathIN.getText(); //передаем путь в новое окно
            DemoController.PATH_OUTPUT = FieldPathOUT.getText();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../FXML/Demo.fxml")); //загружаем fxml нового окна
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root); //выставляем его размеры
            Stage stage = new Stage(); //хуйня чисто для scene builder
            stage.setTitle("Planes with Branes ©Hobots inc (DEMO)"); //название окна

            //обработка закрытия окна
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Выход");
                    alert.setHeaderText("Вы собираетесь закрыть окно без сохранения результатов");
                    alert.setContentText("Вы точно хотите сделать это?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        stage.close();
                    } else {
                        alert.close();
                        stage.showAndWait();
                    }


                }
            });
            stage.setScene(scene);
            //блокировка окна
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.show();
        });


    }

}