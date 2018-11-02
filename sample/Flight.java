package sample;

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
    Flight(Plane P, Airline NameCompany, int numb, boolean state){
        this.plane = P;
        this.carrier = NameCompany;
        this.number = numb;
        this.status = state;
    }
    String getCarrier(){ //toString
        return carrier.name();
    }

}


