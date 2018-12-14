package sample;

import java.time.LocalDateTime;


public class FinalTable {
    public Flight flight;//рейс самолет направление
    public Corridors cor;//корридор
    public int line;//полоса
    public LocalDateTime time;//время посадки
    public String statusF;
    FinalTable(Flight flight,Corridors cor,int line,LocalDateTime time,String statusF){
        this.flight = flight;
        this.cor = cor;
        this.line = line;
        this.time = time;
        this.statusF = statusF;
    }


}
