package Controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.*;


public class DemoController {
    static public String PATH_INPUT;//= "/Users/antonablamsky/Projects/KPO_Git/testINPUT.json"; //поле пути к джсону
    static public String PATH_OUTPUT;//= "/Users/antonablamsky/Projects/KPO_Git/testOUTPUT.json";
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

    //инициализация окна
    @FXML
    void initialize() {
        //привязка значений колонок к значениям табличного класса (создан специально для таблицы)
        columnTrip.setCellValueFactory(new PropertyValueFactory<TablePlaneIN, String>("trip"));
        columnDO.setCellValueFactory(new PropertyValueFactory<TablePlaneIN, String>("statusF"));
        columnDirect.setCellValueFactory(new PropertyValueFactory<TablePlaneIN, String>("direction"));
        columnCorNumb.setCellValueFactory(new PropertyValueFactory<TablePlaneIN, Integer>("corNumb"));
        columnCorSide.setCellValueFactory(new PropertyValueFactory<TablePlaneIN, String>("corSide"));
        columnRunway.setCellValueFactory(new PropertyValueFactory<TablePlaneIN, Integer>("runway"));
        columnTime.setCellValueFactory(new PropertyValueFactory<TablePlaneIN,String>("time"));
        columnTimeFact.setCellValueFactory(new PropertyValueFactory<TablePlaneIN,String>("timeFact"));


        //добавление в таблицу записей
        FinalTable[] tab;
        FinalTable temp;

        /* вызов логики */
        System.out.println("Рейс " + "\t" + "Действие" + "\t" + "Направление" + "\t" + "Время расп." + "\t" + "Корр." + " " + "Стор." + " " + "Полосa" + "\t" + "Факт.время");
        Proccess pr = new Proccess(DemoController.getFlights(DemoController.PATH_INPUT, true), DemoController.getFlights(DemoController.PATH_OUTPUT, true));
        tab = pr.GetTable();

        for (int i = 0; i < tab.length; i++) {
            System.out.println(tab[i].flight.number + "" + tab[i].flight.carrier + "\t" + tab[i].statusF + "\t" + "\t" + tab[i].flight.dir_toString() + "\t" + "\t" + tab[i].flight.time + "\t" + tab[i].cor.GetNumber() + "\t" + tab[i].cor.GetSide() + "\t" + tab[i].line + "\t" + tab[i].time);
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
