package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
//"/t"+flight.time+"/t"+cor.number+"/t"+cor.side+"/t"+line+"/t"+time
import java.sql.Time;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//класс для табличного вывода взлета
public class TableSchedule {
    //обычные поля Flight + поля, которые присваивает сама программа
    //типо взлетная полоса, время взлета и т.д.
    //нельзя было сделать обычный класс, так как таблица работает с Simple
    private final SimpleStringProperty trip;
    private final SimpleStringProperty statusF;
    private final SimpleStringProperty direction;
    private final SimpleStringProperty time;//время посадки
    private final SimpleIntegerProperty corNumb;
    private final SimpleStringProperty corSide;//VPP
    private final SimpleIntegerProperty runway;//VPP
    private final SimpleStringProperty timeFact;//время посадки
    private final SimpleStringProperty status; //есть ли экстр. ситуация
    private final SimpleStringProperty date;

    public TableSchedule(FinalTable table){ //конструктор
        this.trip = new SimpleStringProperty(table.flight.number+table.flight.carrier.name());
        this.statusF = new SimpleStringProperty(table.statusF);
        this.direction = new SimpleStringProperty(table.flight.dir_toString());
        this.corNumb = new SimpleIntegerProperty(table.cor.GetNumber());
        this.corSide = new SimpleStringProperty(table.cor.GetSide());
        this.runway = new SimpleIntegerProperty(table.line);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.time = new SimpleStringProperty(table.flight.time.format(formatter));
        this.timeFact = new SimpleStringProperty(table.time.format(formatter));
        this.status = new SimpleStringProperty(String.valueOf(table.flight.status));

        //починить
        this.date = new SimpleStringProperty(table.flight.time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    //ГЕТы
    public String getTrip(){return trip.get();} //1
    public int getCorNumb() {return corNumb.get();} //2
    public  String getCorSide(){return  corSide.get();} //3
    public int getRunway(){return runway.get();} //4
    public String getTime() {return time.get();} //5
    public String getStatusF() {return  statusF.get();} //6
    public String getDirection() {return direction.get();} //7
    public String TimeFact() {return timeFact.get();} //8
    public boolean getStatus(){return Boolean.valueOf(status.get());} //9
    public LocalDate getDate() {return LocalDate.parse(date.get());}

    //СЕТы
    public void setTime(LocalTime t){ time.set(String.valueOf(t.getHour())+":"+String.valueOf(t.getHour()));}
    public void setTimeFact(LocalTime t){ time.set(String.valueOf(t.getHour())+":"+String.valueOf(t.getHour()));}
    public void setRunway(int s) {
        runway.set(s);}
    public void setStatusF(String s){statusF.set(s);}
    public  void setStatus(boolean s){
        status.set(String.valueOf(s));
    }
    public void setDirection(Direction d) {direction.set(d.name());}
    public void setTrip(int number, Airline a){trip.set(String.valueOf(number)+a.name());}
    public void setCorNumb(int numb) {corNumb.set(numb);}
    public void setCorSide(String s) {corSide.set(s);}
    public void setDate(LocalDate d){date.set(d.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));}

    //ГЕТы (только для таблицы)
    public SimpleStringProperty tripProperty() {return trip;}
    public SimpleIntegerProperty runwayProperty() {return runway;}
    public SimpleIntegerProperty corNumbProperty() {return  corNumb;}
    public SimpleStringProperty timeProperty() {return time;}
    public SimpleStringProperty corSideProperty() {return  corSide;}
    public SimpleStringProperty statusProperty() {return status;}
    public SimpleStringProperty timeFactProperty() {return timeFact;}
    public SimpleStringProperty directionProperty() {return  direction;}
    public SimpleStringProperty statusFProperty() {return  statusF;}
    public SimpleStringProperty dateProperty() { return date;}


}