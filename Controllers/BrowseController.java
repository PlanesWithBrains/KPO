package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
        File f = fc.showOpenDialog(null);
        if (f!= null){
            FieldPathIN.setText(f.getAbsolutePath()); //помещение пути в строку ввода пути файла
        }
    }

    @FXML
    void fileChooserOUT(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));
        File f = fc.showOpenDialog(null);
        if (f!= null){
            FieldPathOUT.setText(f.getAbsolutePath()); //помещение пути в строку ввода пути файла
        }
    }


    @FXML
    void initialize() { //инициализация окна
        BTNschedule.setOnAction(event -> { //если нажали кнопку сделать расписание
            BTNschedule.getScene().getWindow().hide();//старое окно убираем
            demoController.PATH_INPUT = FieldPathIN.getText(); //передаем путь в новое окно
            demoController.PATH_OUTPUT = FieldPathOUT.getText();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../FXML/demo.fxml")); //загружаем fxml нового окна
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root,1366,768); //выставляем его размеры
            Stage stage = new Stage(); //хуйня чисто для scene builder
            stage.setTitle("Planes with Branes ©Hobots inc (DEMO)"); //название окна

            stage.setScene(scene);
            stage.showAndWait(); //запустили и ждем с моря погоды
        });


    }

}