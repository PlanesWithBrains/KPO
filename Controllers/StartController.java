package Controllers;


import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import Log.Log;
import com.google.gson.Gson;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.util.Date;
import sample.FinalTable;
import sample.Main;


public class StartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnGenerator;

    @FXML
    private Button btnOpen;

    @FXML
    private Label labelName;
    @FXML
    private Button btnRecent1;

    @FXML
    private Button btnRecent2;

    @FXML
    private Button btnRecent3;

    @FXML
    private Button btnRecent4;

    @FXML
    private Button btnRecent5;

    @FXML
    private Button btnRecent6;

    @FXML
    private Button btnRecent7;

    @FXML
    private Button btnRecent8;


    @FXML
    void initialize() {
        Font font = Font.loadFont(getClass().getResourceAsStream("/Fonts/Label.ttf"), 31);
        labelName.setFont(font);
        File fileLog = new File(getClass().getResource("../Log/log.json").getPath());
        Log log[] = DemoController.getLog(fileLog.getAbsolutePath());
        int selected = log == null ? 0 : log.length;
        switch (selected){
            case 1: {
                btnRecent1.setText(log[0].dateTime+"\n"+log[0].nameFile);
                btnRecent2.setVisible(false);
                btnRecent3.setVisible(false);
                btnRecent4.setVisible(false);
                btnRecent5.setVisible(false);
                btnRecent6.setVisible(false);
                btnRecent7.setVisible(false);
                btnRecent8.setVisible(false);
                break;
            }
            case 2: {
                btnRecent1.setText(log[0].dateTime+"\n"+log[0].nameFile);
                btnRecent2.setText(log[1].dateTime+"\n"+log[1].nameFile);
                btnRecent3.setVisible(false);
                btnRecent4.setVisible(false);
                btnRecent5.setVisible(false);
                btnRecent6.setVisible(false);
                btnRecent7.setVisible(false);
                btnRecent8.setVisible(false);
                break;
            }
            case 3: {
                btnRecent1.setText(log[0].dateTime+"\n"+log[0].nameFile);
                btnRecent2.setText(log[1].dateTime+"\n"+log[1].nameFile);
                btnRecent3.setText(log[2].dateTime+"\n"+log[2].nameFile);
                btnRecent4.setVisible(false);
                btnRecent5.setVisible(false);
                btnRecent6.setVisible(false);
                btnRecent7.setVisible(false);
                btnRecent8.setVisible(false);
                break;
            }
            case 4: {
                btnRecent1.setText(log[0].dateTime+"\n"+log[0].nameFile);
                btnRecent2.setText(log[1].dateTime+"\n"+log[1].nameFile);
                btnRecent3.setText(log[2].dateTime+"\n"+log[2].nameFile);
                btnRecent4.setText(log[3].dateTime+"\n"+log[3].nameFile);
                btnRecent5.setVisible(false);
                btnRecent6.setVisible(false);
                btnRecent7.setVisible(false);
                btnRecent8.setVisible(false);
                break;
            }
            case 5: {
                btnRecent1.setText(log[0].dateTime+"\n"+log[0].nameFile);
                btnRecent2.setText(log[1].dateTime+"\n"+log[1].nameFile);
                btnRecent3.setText(log[2].dateTime+"\n"+log[2].nameFile);
                btnRecent4.setText(log[3].dateTime+"\n"+log[3].nameFile);
                btnRecent5.setText(log[4].dateTime+"\n"+log[4].nameFile);
                btnRecent6.setVisible(false);
                btnRecent7.setVisible(false);
                btnRecent8.setVisible(false);
                break;
            }
            case 6: {
                btnRecent1.setText(log[0].dateTime+"\n"+log[0].nameFile);
                btnRecent2.setText(log[1].dateTime+"\n"+log[1].nameFile);
                btnRecent3.setText(log[2].dateTime+"\n"+log[2].nameFile);
                btnRecent4.setText(log[3].dateTime+"\n"+log[3].nameFile);
                btnRecent5.setText(log[4].dateTime+"\n"+log[4].nameFile);
                btnRecent6.setText(log[5].dateTime+"\n"+log[5].nameFile);
                btnRecent7.setVisible(false);
                btnRecent8.setVisible(false);
                break;
            }
            case 7: {
                btnRecent1.setText(log[0].dateTime + "\n" + log[0].nameFile);
                btnRecent2.setText(log[1].dateTime + "\n" + log[1].nameFile);
                btnRecent3.setText(log[2].dateTime + "\n" + log[2].nameFile);
                btnRecent4.setText(log[3].dateTime + "\n" + log[3].nameFile);
                btnRecent5.setText(log[4].dateTime + "\n" + log[4].nameFile);
                btnRecent6.setText(log[5].dateTime + "\n" + log[5].nameFile);
                btnRecent7.setText(log[6].dateTime + "\n" + log[6].nameFile);
                btnRecent8.setVisible(false);
                break;
                 }
            case 0:{
                btnRecent1.setVisible(false);
                btnRecent2.setVisible(false);
                btnRecent3.setVisible(false);
                btnRecent4.setVisible(false);
                btnRecent5.setVisible(false);
                btnRecent6.setVisible(false);
                btnRecent7.setVisible(false);
                btnRecent8.setVisible(false);
            }
        }
        btnRecent1.setOnAction(event -> {if(!openRecent(log,0)) btnRecent1.setVisible(false);});
        btnRecent2.setOnAction(event -> {if(!openRecent(log,1)) btnRecent2.setVisible(false);});
        btnRecent3.setOnAction(event -> {if(!openRecent(log,2)) btnRecent3.setVisible(false);});
        btnRecent4.setOnAction(event -> {if(!openRecent(log,3)) btnRecent4.setVisible(false);});
        btnRecent5.setOnAction(event -> {if(!openRecent(log,4)) btnRecent5.setVisible(false);});
        btnRecent6.setOnAction(event -> {if(!openRecent(log,5)) btnRecent6.setVisible(false);});
        btnRecent7.setOnAction(event -> {if(!openRecent(log,6)) btnRecent7.setVisible(false);});
        btnRecent8.setOnAction(event -> {if(!openRecent(log,7)) btnRecent8.setVisible(false);});
        //кнопка создания нового расписания
        btnCreate.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../FXML/Browse.fxml")); //загружаем fxml нового окна
                Scene scene = new Scene(root); //выставляем его размеры
                Stage stage = new Stage(); //хуйня чисто для scene builder
                stage.setTitle("Planes with Branes"); //название окна
                stage.getIcons().add(new Image(Main.class.getResourceAsStream("../Images/icon.png")));
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
                DemoController.NAME = f.getName().substring(0,f.getName().length()-5);
                Date last = new Date(f.lastModified());
                DemoController.DATE = LocalDateTime.ofInstant(last.toInstant(), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm"));
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
                stage.setTitle("Planes with Branes"); //название окна
                stage.getIcons().add(new Image(Main.class.getResourceAsStream("../Images/icon.png")));
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
        btnGenerator.setOnAction(event -> {
            //this.btnGenerator.getScene().getWindow().hide();
            Process process = null;
            try {
                process = Runtime.getRuntime().exec ("java -jar " + System.getProperty("user.dir")+"\\src\\Jar\\PlaneGenerator.jar");
                process.getInputStream().close();
                process.getOutputStream().close();
                process.getErrorStream().close();
                } catch (java.io.IOException e) {
                    System.out.println("Cannot execute jar");
                }
                Thread.currentThread().interrupt();
        });
    }
    Log[] deleteLog(Log log[], int index){
        Log buf[] = new Log[log.length-1];
        for (int i = 0; i < index; i++) buf[i] = log[i];
        for (int i = index; i < log.length - 1; ++i)
        {
            buf[i] = log[i + 1];
        }
        File fileLog = new File(getClass().getResource("../Log/log.json").getPath());
        try {
            DemoController.saveLog(buf,fileLog.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buf;
    }
    void Open(String path){
        DemoController.flagOpen = true;

        File f = new File(path);
        if (f != null) {
            DemoController.NAME = f.getName().substring(0,f.getName().length()-5);
            Date last = new Date(f.lastModified());
            DemoController.DATE = LocalDateTime.ofInstant(last.toInstant(), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm"));
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
            stage.setTitle("Planes with Branes"); //название окна
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("../Images/icon.png")));

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
    }
    boolean isExtst(String path){
        File f = new File(path);
        return f.exists();
    }
    void allerExist(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Open recent");
        alert.setHeaderText("Данное расписание не найдено!");
        alert.setContentText("Попробуйте перезапустить программу для обновления информации о последних проектах.");
        alert.showAndWait();
    }
    boolean openRecent(Log log[], int index){
        if (isExtst(log[index].nameFile)){
            Open(log[index].nameFile);
            return true;
        }
        else{
            deleteLog(log,index);
            allerExist();
            return false;
        }
    }
}

