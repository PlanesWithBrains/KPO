package sample;

import java.time.LocalTime;

public class FinalTable {
    Flight flight;//рейс самолет направление
    Corridors cor;//корридор
    int line;//полоса
    LocalTime time;//время посадки
    String statusF;
    FinalTable(Flight flight,Corridors cor,int line,LocalTime time,String statusF){
        this.flight = flight;
        this.cor = cor;
        this.line = line;
        this.time = time;
        this.statusF = statusF;
        //System.out.println(flight.number+""+flight.carrier+"\t"+statusF+"\t"+"\t"+flight.direction.toString()+"\t"+"\t"+flight.time+"\t"+cor.number+"\t"+cor.side+"\t"+line+"\t"+time);
    }
    /*FinalTable GetValues(FinalTable table){
        //FinalTable ft;
        this.flight = table.flight;
        this.cor = table.cor;
        this.line = table.line;
        this.time = table.time;
        this.statusF = table.statusF;
        return
    }*/

}
