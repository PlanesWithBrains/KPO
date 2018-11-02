package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Time;
//класс для табличного вывода взлета
public class TableFlight {
    //обычные поля Flight + поля, которые присваивает сама программа
    //типо взлетная полоса, время взлета и т.д.
    //нельзя было сделать обычный класс, так как таблица работает с Simple
    private final SimpleStringProperty airline;
    private final SimpleIntegerProperty number;
    private final SimpleStringProperty plane;
    private final SimpleStringProperty runway;//VPP
    private final SimpleStringProperty route; //типо номер коридора транспортировки, забыл как называется
    private final SimpleStringProperty parking;//номер парковочного места
    private final SimpleStringProperty time;//время посадки
    private final SimpleStringProperty status; //есть ли экстр. ситуация

    public TableFlight(Flight f, String VPP, String rout, String park, Time time1){ //конструктор
        this.airline = new SimpleStringProperty(f.carrier.name());
        this.number = new SimpleIntegerProperty(f.number);
        this.plane = new SimpleStringProperty(f.plane.name);
        this.runway = new SimpleStringProperty(VPP);
        this.route = new SimpleStringProperty(rout);
        this.parking = new SimpleStringProperty(park);
        this.time = new SimpleStringProperty(time1.toString());
        this.status = new SimpleStringProperty(String.valueOf(f.status));
    }

    //ГЕТы
    public String getAirline(){return airline.get();}
    public String getPlane() {return plane.get();}
    public String getRunway(){return runway.get();}
    public String getRoute(){return route.get();}
    public String getParking() {return parking.get();}
    public String getTime() {return time.get();}
    public int getNumber(){return number.get();}
    public boolean getStatus(){return Boolean.valueOf(status.get());}

    //СЕТы
    public void setAirline(String a){ airline.set(a);}
    public void setTime(Time t){ airline.set(t.toString());}
    public void setNumber(int n) {
        number.set(n);}
    public void setPlane(String p) { plane.set(p);}
    public void setRunway(String s) {
        runway.set(s);}
    public void setRoute(String s){
        route.set(s);}
    public void setParking(String p) {
        parking.set(p);}
    public  void setStatus(boolean s){
        status.set(String.valueOf(s));
    }

    //ГЕТы (только для таблицы)
    public SimpleStringProperty airlineProperty() {return airline;}
    public SimpleStringProperty planeProperty() {return plane;}
    public SimpleStringProperty runwayProperty() {return runway;}
    public SimpleStringProperty routeProperty() {return route;}
    public SimpleStringProperty parkingProperty() {return parking;}
    public SimpleStringProperty timeProperty() {return time;}
    public SimpleIntegerProperty numberProperty() {return number;}
    public SimpleStringProperty statusProperty() {return status;}


}
