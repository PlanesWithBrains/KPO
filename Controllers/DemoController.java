package Controllers;

import java.io.*;
import java.net.URL;
import java.util.*;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import sample.*;


public class DemoController {
    static String PATH_INPUT;//= "/Users/antonablamsky/Projects/KPO_Git/testINPUT.json"; //поле пути к джсону
    static String PATH_OUTPUT;//= "/Users/antonablamsky/Projects/KPO_Git/testOUTPUT.json";

    //OPEN/CREATE
    static FinalTable[] tab;
    static boolean flagOpen;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<TablePlaneIN> TableIN; //окно таблицы

    //колонки таблицы

    @FXML
    private TableColumn<TablePlaneIN, String> columnTrip;

    @FXML
    private TableColumn<TablePlaneIN, String> columnDO;

    @FXML
    private TableColumn<TablePlaneIN, String> columnDirect;

    @FXML
    private TableColumn<TablePlaneIN, String> columnTime;

    @FXML
    private TableColumn<TablePlaneIN, Integer> columnCorNumb;

    @FXML
    private TableColumn<TablePlaneIN, String> columnCorSide;

    @FXML
    private TableColumn<TablePlaneIN, Integer> columnRunway;

    @FXML
    private TableColumn<TablePlaneIN, String> columnTimeFact;

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
            if (f!= null){
                Gson gson = new Gson();
                try(FileWriter wr = new FileWriter(f.getAbsoluteFile())) {
                    String str = gson.toJson(tab);
                    wr.write(str);
                }
                catch(IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else
                System.out.println((char)27 + "[32mWARNING: FileChooser do not save");

        });
        //привязка значений колонок к значениям табличного класса (создан специально для таблицы)
        columnTrip.setCellValueFactory(new PropertyValueFactory<TablePlaneIN, String>("trip"));
        columnDO.setCellValueFactory(new PropertyValueFactory<TablePlaneIN, String>("statusF"));
        columnDirect.setCellValueFactory(new PropertyValueFactory<TablePlaneIN, String>("direction"));
        columnCorNumb.setCellValueFactory(new PropertyValueFactory<TablePlaneIN, Integer>("corNumb"));
        columnCorSide.setCellValueFactory(new PropertyValueFactory<TablePlaneIN, String>("corSide"));
        columnRunway.setCellValueFactory(new PropertyValueFactory<TablePlaneIN, Integer>("runway"));
        columnTime.setCellValueFactory(new PropertyValueFactory<TablePlaneIN,String>("time"));
        columnTimeFact.setCellValueFactory(new PropertyValueFactory<TablePlaneIN,String>("timeFact"));


        /* вызов логики */
        if (!flagOpen) {
            Proccess pr = new Proccess(DemoController.getFlights(DemoController.PATH_INPUT, true), DemoController.getFlights(DemoController.PATH_OUTPUT, true));
            tab = pr.GetTable();
            }
        System.out.println("Рейс " + "\t" + "Действие" + "\t" + "Направление" + "\t" + "Время расп." + "\t" + "Корр." + " " + "Стор." + " " + "Полосa" + "\t" + "Факт.время");
        for (int i = 0; i < tab.length; i++) {
            System.out.println(tab[i].flight.getNumber() + "" + tab[i].flight.getCarrier() + "\t" + tab[i].statusF + "\t" + "\t" + tab[i].flight.dir_toString() + "\t" + "\t" + tab[i].flight.getTime() + "\t" + tab[i].cor.GetNumber() + "\t" + tab[i].cor.GetSide() + "\t" + tab[i].line + "\t" + tab[i].time);
        }

        TableIN.setItems(getFlights(tab)); //добавить записи








        //раскраска записей c экстренной ситуацией
        TableIN.setRowFactory((TableView<TablePlaneIN> paramP) -> new TableRow<TablePlaneIN>() {
            @Override
            protected void updateItem(TablePlaneIN tf, boolean paramBoolean) {
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


    //FinalTable to -> TablePlaneIN (класс логики антона -> класс вывода в таблицу)
    public ObservableList<TablePlaneIN> getFlights(FinalTable temp[]) {
        ObservableList<TablePlaneIN> buf = FXCollections.observableArrayList();
        for (int i = 0; i < temp.length; i++){
            TablePlaneIN bufTable = new sample.TablePlaneIN(temp[i]);
            buf.add(bufTable);
        }
        return buf;
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
}
