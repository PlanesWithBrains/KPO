package sample;

import java.sql.Time;

enum Airline{
    SBI,//S7,
    AFL,//Аэрофлот,
    NWS,//Nordwind,
    AZO, //Азимут
    PBD,//Победа,
    QTR,//QatarAirways,
    UAE//EmiratesAirline
}
/**
 *
 * @author Дмитрий Соловьев
 */
public class Flight{
    Airline carrier; //авиакомпания
    int number; //номер рейса
    Plane plane;//самолет
    boolean status; //есть ли экстренная ситуация
    int distance;
    int hight;
    Time time;
    boolean landingStatus;//совершена ли посадка
    Direction direction;
    String arrivalFrom;// TODO для демки в генератор необходиом добавить направление,с которого прибывает самолет
    //TODO направления аналогичны тем,что указаны в классе Airport
    Flight(Plane P, Airline NameCompany, int numb, boolean state, int distanc, int Hight, String arrival){
        this.plane = P;
        this.carrier = NameCompany;
        this.number = numb;
        this.status = state;
        this.distance = distanc;
        this.hight = Hight;
        this.arrivalFrom = arrival;
    }

    String getCarrier(){ //toString
        return carrier.name();
    }

}


