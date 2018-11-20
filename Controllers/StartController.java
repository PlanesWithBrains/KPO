package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnOpen;

    @FXML
    void initialize() {
        //кнопка создания нового расписания
        btnCreate.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../FXML/Browse.fxml")); //загружаем fxml нового окна
                Scene scene = new Scene(root); //выставляем его размеры
                Stage stage = new Stage(); //хуйня чисто для scene builder
                stage.setTitle("Planes with Branes ©Hobots inc (DEMO)"); //название окна

                //обработка закрытия окна
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        stage.close();
                    }
                });
                stage.setScene(scene);
                //блокировка окна
                stage.initModality(Modality.APPLICATION_MODAL);

                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnOpen.setOnAction(event -> {
            System.out.println("В Разработке!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Открытие существующего расписания");
            alert.setHeaderText("Функция находится на стадии разработки");
            alert.setContentText("В скором времени здесь будет что-то крутое :)");
            alert.showAndWait();
        });

    }
}
