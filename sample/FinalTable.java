package sample;

        import java.time.LocalTime;

public class FinalTable {
    public Flight flight;//рейс самолет направление
    public Corridors cor;//корридор
    public int line;//полоса
    public LocalTime time;//время посадки
    public String statusF;
    FinalTable(Flight flight,Corridors cor,int line,LocalTime time,String statusF){
        this.flight = flight;
        this.cor = cor;
        this.line = line;
        this.time = time;
        this.statusF = statusF;
    }


}
