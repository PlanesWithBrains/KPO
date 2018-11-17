package sample;

public class Corridors {
    int number;// номер корридора, задается в трехзначном формате "101"
    //1 (103) - означает западный торец (NW - направление(0)), где 3 - номер корридора
    int lines;// номер полосы
    String side;// какой торец полосы используется
    boolean status;
    Corridors(String s,int lines,int number){
        this.lines = lines;
        this.number = number;
        if (s.equals("NW"))
            side = "W1";
        if (s.equals("NE"))
            side = "E1";
        if (s.equals("SW"))
            side = "W2";
        if (s.equals("SE"))
            side = "E2";
    }
    public int GetNumber(){return number;}
    public int GetLines(){return  lines;}
    public String GetSide(){return side;}
    public boolean GetStatus() {return status;}
}
