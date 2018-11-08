package sample;

import java.time.LocalTime;

public class FinalTable {
    Flight flight;//рейс самолет направление
    Corridors cor;//корридор
    int line;//полоса
    LocalTime time;//время посадки
    FinalTable(Flight flight,Corridors cor,int line,LocalTime time){
        System.out.println(flight.number+"/t"+flight.carrier+"/t"+flight.direction.toString()+"/t"+flight.time+"/t"+cor.number+"/t"+cor.side+"/t"+line+"/t"+time);

    }

}
