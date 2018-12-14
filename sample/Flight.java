package sample;

import java.time.LocalDateTime;

enum Airline{
    S7,//S7,
    SU,//Аэрофлот,
    N4,//Nordwind,
    A4, //Азимут
    DP,//Победа,
    QR,//QatarAirways,
    EK//EmiratesAirline
}
enum Direction{
    NorthWest,NorthEast,East,West,SouthWest,SouthEast
}
/**
 *
 * @author Дмитрий Соловьев
 */
public class Flight{
    Airline carrier;
    int number;
    Plane plane;
    Direction direction;
    LocalDateTime time;
    boolean status;
    int distance;
    int hight;

    Flight(Plane P, Airline NameCompany, int numb, Direction dir, LocalDateTime t, boolean state, int dist, int hig){ //для посадки
        this.plane = P;
        this.carrier = NameCompany;
        this.number = numb;
        this.direction = dir;
        this.time = t;
        this.status = state;
        this.distance = dist;
        this.hight = hig;
    }
    Flight(Plane P, Airline NameCompany, int numb, Direction dir, LocalDateTime t){ //для взлета
        this.plane = P;
        this.carrier = NameCompany;
        this.number = numb;
        this.direction = dir;
        this.time = t;
        this.status = true;
        this.distance = 0;
        this.hight = 0;
    }

    public String getCarrier(){
        return carrier.name();
    }
    public String dir_toString(){
        return direction.name();
    }
    public int getNumber(){return  number;}
    public Direction getDirection(){return  direction;}
    public  LocalDateTime getTime(){return  time;}
}