package Controllers;

import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.FinalTable;
import sample.Flight;
import sample.TablePlaneIN;

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

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnOpen.setOnAction(event -> {
            DemoController.flagOpen = true;

            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));

            //директория по умолчанию
            try {
                File initialDirectory = new File(System.getProperty("user.home") + "\\Documents\\");
                fc.setInitialDirectory(initialDirectory);
            } catch (Exception exp) { //если MAC os
                System.out.println((char) 27 + "[32m" + exp.getMessage());
            }

            File f = fc.showOpenDialog(null);
            if (f != null) {

                Gson gson = new Gson(); //сашкина либа для десериализации
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(f.getAbsoluteFile()));
                    DemoController.tab = gson.fromJson(reader, FinalTable[].class);
                    reader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


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
                        if (result.get() == ButtonType.OK) {
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
            }
        });
    }
}

