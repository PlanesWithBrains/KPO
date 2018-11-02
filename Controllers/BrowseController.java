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
    private ResourceBundle resources; //хуйня чисто для scene builder

    @FXML
    private URL location; //хуйня чисто для scene builder

    @FXML
    private TextArea FieldPath; //строка ввода пути к файлу


    @FXML
    private Button BTNschedule; //кнопка создания расписания

    @FXML
    void fileChooser(ActionEvent event) { //открытие проводника для выбора директории
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));
        File f = fc.showOpenDialog(null);
        if (f!= null){
            FieldPath.setText(f.getAbsolutePath()); //помещение пути в строку ввода пути файла
        }
    }

    @FXML
    void initialize() { //инициализация окна
    BTNschedule.setOnAction(event -> { //если нажали кнопку сделать расписание
        BTNschedule.getScene().getWindow().hide();//старое окно убираем
        demoController.PATH = FieldPath.getText(); //передаем путь в новое окно
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../FXML/demo.fxml")); //загружаем fxml нового окна
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root,1366,768); //выставляем его размеры
        Stage stage = new Stage(); //хуйня чисто для scene builder
        stage.setTitle("Planes with Branes ©Hobots inc (DEMO)"); //название окна

        stage.setScene(scene); //хуйня чисто для scene builder
        stage.showAndWait(); //запустили и ждем с моря погоды
    });


    }

}
