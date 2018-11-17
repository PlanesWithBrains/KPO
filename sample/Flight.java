package sample;

import java.time.LocalTime;

enum Airline{
    SBI,//S7,
    AFL,//Аэрофлот,
    NWS,//Nordwind,
    AZO, //Азимут
    PBD,//Победа,
    QTR,//QatarAirways,
    UAE//EmiratesAirline
}
enum Direction{
    NorthWest,NorthEast,East,West,SouthWest,SouthEast
}
/**
 *
 * @author Дмитрий Соловьев
 */
public class Flight{
    public Airline carrier;
    public int number;
    Plane plane;
    public Direction direction;
    public LocalTime time;
    boolean status;
    int distance;
    int hight;

    Flight(Plane P, Airline NameCompany, int numb, Direction dir, LocalTime t, boolean state, int dist, int hig){ //для посадки
        this.plane = P;
        this.carrier = NameCompany;
        this.number = numb;
        this.direction = dir;
        this.time = t;
        this.status = state;
        this.distance = dist;
        this.hight = hig;
    }

    Flight(Plane P, Airline NameCompany, int numb, Direction dir, LocalTime t){ //для взлета
        this.plane = P;
        this.carrier = NameCompany;
        this.number = numb;
        this.direction = dir;
        this.time = t;
        this.status = true;
        this.distance = 0;
        this.hight = 0;
    }
    String getCarrier(){
        return carrier.name();
    }
    public String dir_toString(){
        return direction.name();
    }
}