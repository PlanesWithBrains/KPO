package Controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.sql.Time;
import java.util.*;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Flight;
import sample.TableFlight;


public class demoController {
    static String PATH; //поле пути к джсону

    @FXML
    private ResourceBundle resources; //хуйня чисто для scene builder

    @FXML
    private URL location;  //хуйня чисто для scene builder

    @FXML
    private TableView<TableFlight> TableIN; //окно таблицы

    //колонки таблицы
    @FXML
    private TableColumn<TableFlight, String> columnAirline;

    @FXML
    private TableColumn<TableFlight, Integer> columnNumber;

    @FXML
    private TableColumn<TableFlight, String> columnPlane;

    @FXML
    private TableColumn<TableFlight, String> columnTime;

    @FXML
    private TableColumn<TableFlight, String> columnRunway;

    @FXML
    private TableColumn<TableFlight, String> columnRoute;

    @FXML
    private TableColumn<TableFlight, String> columnParking;

    //инициализация окна
    @FXML
    void initialize() {
        //привязка значений колонок к значениям табличного класса (создан специально для таблицы)
        columnAirline.setCellValueFactory(new PropertyValueFactory<TableFlight, String>("airline"));
        columnNumber.setCellValueFactory(new PropertyValueFactory<TableFlight, Integer>("number"));
        columnPlane.setCellValueFactory(new PropertyValueFactory<TableFlight, String>("plane"));
        columnRunway.setCellValueFactory(new PropertyValueFactory<TableFlight, String>("runway"));
        columnRoute.setCellValueFactory(new PropertyValueFactory<TableFlight, String>("route"));
        columnParking.setCellValueFactory(new PropertyValueFactory<TableFlight, String>("parking"));
        columnTime.setCellValueFactory(new PropertyValueFactory<TableFlight,String>("time"));

        //добавление в таблицу записей
        TableIN.setItems(getFlights(demoController.PATH));
        //раскраска записей
        TableIN.setRowFactory((TableView<TableFlight> paramP) -> new TableRow<TableFlight>() {
            @Override
            protected void updateItem(TableFlight tf, boolean paramBoolean) {
                if (tf != null) {
                    if (tf.getStatus() == false) //если есть экстренная ситуация - красим в красный
                        setStyle("-fx-background-color: LIGHTCORAL; -fx-text-background-color: black;");
                    else //если нет - в серый
                        setStyle("-fx-background-color: LIGHTGREY; -fx-text-background-color: black;");

                } else {
                    setStyle(null);
                }
                super.updateItem(tf, paramBoolean);
            }
        });

    }

    //считывам джсон
    public ObservableList<TableFlight> getFlights(String PATH) {
        Gson gson = new Gson(); //сашкина либа для десериализации
        Flight[] temp = new Flight[1]; // буфер
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PATH));
             temp = gson.fromJson(reader, Flight[].class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //делаем из полетов, которые считали, лист для табличной работы
        ObservableList<TableFlight> buf = FXCollections.observableArrayList();
        for (int i = 0; i < temp.length; i++){
            //ВОТ ЗДЕСЬ НЕОБХОДИМО ДОБАВИТЬ ЛОГИКУ
            //типо сортировку, в зависимости от которой он будет выставлять полосу, путь, парковочное место и время посадки
            TableFlight bufTable = new sample.TableFlight( temp[i], "VPP", "DEFrout", "DEFpark",new Time(20,20,0));
            buf.add(bufTable);
        }
        return buf;
    }
}
