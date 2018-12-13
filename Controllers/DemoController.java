package Controllers;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Log.Log;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import sample.*;


public class DemoController {
    static String PATH_INPUT;//= "/Users/antonablamsky/Projects/KPO_Git/testINPUT.json"; //поле пути к джсону
    static String PATH_OUTPUT;//= "/Users/antonablamsky/Projects/KPO_Git/testOUTPUT.json";
    static String NAME = "Новое расписание";
    static String DATE = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyy | HH:mm"));

    //OPEN/CREATE
    static FinalTable[] tab;
    static boolean flagOpen;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<TableSchedule> TableIN; //окно таблицы

    //колонки таблицы

    @FXML
    private TableColumn<TableSchedule, String> columnTrip;

    @FXML
    private TableColumn<TableSchedule, String> columnDO;

    @FXML
    private TableColumn<TableSchedule, String> columnDirect;

    @FXML
    private TableColumn<TableSchedule, String> columnTime;

    @FXML
    private TableColumn<TableSchedule, Integer> columnCorNumb;

    @FXML
    private TableColumn<TableSchedule, String> columnCorSide;

    @FXML
    private TableColumn<TableSchedule, Integer> columnRunway;

    @FXML
    private TableColumn<TableSchedule, String> columnTimeFact;

    @FXML
    private TableColumn<TableSchedule, String> columnDate;

    @FXML
    private Label lbl_Date;

    @FXML
    private Label lbl_Title;

    @FXML
    private Button btnSave;
    //инициализация окна
    @FXML
    void initialize() {
        btnSave.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));

            //директория по умолчанию
            try {
                File initialDirectory = new File(System.getProperty("user.home") + "\\Documents\\");
                fc.setInitialDirectory(initialDirectory);
                fc.setInitialFileName("testSAVE.json");
            }
            catch (Exception exp){ //если MAC os
                System.out.println((char)27 + "[32m"+exp.getMessage());
            }

            File f = fc.showSaveDialog(null);
            if (f!= null) {
                Gson gson = new Gson();
                try (FileWriter wr = new FileWriter(f.getAbsoluteFile())) {
                    String str = gson.toJson(tab);
                    wr.write(str);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                File fileLog = new File(getClass().getResource("../Log/log.json").getPath());
                Log[] log = getLog(fileLog.getAbsolutePath());
                if (log == null) {
                    log = new Log[1];
                }
                Log buf[] = new Log[log.length+1];
                for (int i = 0; i < log.length; i++){
                    buf[i+1] = log[i];
                }
                buf[0] = new Log(LocalDateTime.now(), f.getAbsolutePath());
                try {
                    saveLog(buf, fileLog.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                lbl_Title.setText(f.getName().substring(0,f.getName().length()-5));
                lbl_Date.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyy | HH:mm")));
                lbl_Date.setVisible(true);
            }
            else
                System.out.println((char)27 + "[32mWARNING: FileChooser do not save");

        });
        initializeROW();
        if (!flagOpen) {
            Proccess pr = new Proccess(getFlights(PATH_INPUT, true), getFlights(PATH_OUTPUT, true));
            tab = pr.GetTable();
            NAME = "Новое расписание";
            lbl_Date.setVisible(false);
            }
        else lbl_Date.setVisible(true);

        lbl_Title.setText(NAME);
        lbl_Date.setText(DATE);
        System.out.println("Рейс " + "\t" + "Действие" + "\t" + "Направление" + "\t" + "Время расп." + "\t" + "Корр." + " " + "Стор." + " " + "Полосa" + "\t" + "Факт.время");
        for (int i = 0; i < tab.length; i++) {
            System.out.println(tab[i].flight.getNumber() + "" + tab[i].flight.getCarrier() + "\t" + tab[i].statusF + "\t" + "\t" + tab[i].flight.dir_toString() + "\t" + "\t" + tab[i].flight.getTime() + "\t" + tab[i].cor.GetNumber() + "\t" + tab[i].cor.GetSide() + "\t" + tab[i].line + "\t" + tab[i].time);
        }

        TableIN.setItems(getFlights(tab)); //добавить записи

        //раскраска записей c экстренной ситуацией
        TableIN.setRowFactory((TableView<TableSchedule> paramP) -> new TableRow<TableSchedule>() {
            @Override
            protected void updateItem(TableSchedule tf, boolean paramBoolean) {
                if (tf != null) {
                    if (!tf.getStatus()) //если есть экстренная ситуация - красим в красный
                        setStyle("-fx-background-color: LIGHTCORAL; -fx-text-background-color: black;");
                    //else //если нет - в серый
                        //setStyle("-fx-background-color: WHITE; -fx-text-background-color: black;");

                } else {
                    setStyle(null);
                }
                super.updateItem(tf, paramBoolean);
            }
        });

    }
    static public void saveLog(Log lg[], String PATH) throws IOException {
        File log = new File(PATH);
        Gson gson = new Gson();
        try(FileWriter wr = new FileWriter(log.getAbsoluteFile())) {
            String str = gson.toJson(lg);
            wr.write(str);
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //FinalTable to -> TableSchedule (класс логики антона -> класс вывода в таблицу)
    public ObservableList<TableSchedule> getFlights(FinalTable temp[]) {
        ObservableList<TableSchedule> buf = FXCollections.observableArrayList();
        for (int i = 0; i < temp.length; i++){
            TableSchedule bufTable = new TableSchedule(temp[i]);
            buf.add(bufTable);
        }
        return buf;
    }
    //считываем лог
    static public Log[] getLog(String PATH) { //ДЕССЕРИАЛИЗАТОР
        Gson gson = new Gson(); //сашкина либа для десериализации
        Log[] temp = new Log[1]; // буфер
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PATH));
            temp = gson.fromJson(reader, Log[].class);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }
    //считывам джсон
    static public Flight[] getFlights(String PATH, boolean flag) { //ДЕССЕРИАЛИЗАТОР
        Gson gson = new Gson(); //сашкина либа для десериализации
        Flight[] temp = new Flight[1]; // буфер
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PATH));
            temp = gson.fromJson(reader, Flight[].class);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public void initializeROW(){
        //привязка значений колонок к значениям табличного класса (создан специально для таблицы)
        columnTrip.setCellValueFactory(new PropertyValueFactory<TableSchedule, String>("trip"));
        columnDO.setCellValueFactory(new PropertyValueFactory<TableSchedule, String>("statusF"));
        columnDirect.setCellValueFactory(new PropertyValueFactory<TableSchedule, String>("direction"));
        columnCorNumb.setCellValueFactory(new PropertyValueFactory<TableSchedule, Integer>("corNumb"));
        columnCorSide.setCellValueFactory(new PropertyValueFactory<TableSchedule, String>("corSide"));
        columnRunway.setCellValueFactory(new PropertyValueFactory<TableSchedule, Integer>("runway"));
        columnTime.setCellValueFactory(new PropertyValueFactory<TableSchedule,String>("time"));
        columnTimeFact.setCellValueFactory(new PropertyValueFactory<TableSchedule,String>("timeFact"));
        columnDate.setCellValueFactory(new PropertyValueFactory<TableSchedule, String>("date"));
    }
}
