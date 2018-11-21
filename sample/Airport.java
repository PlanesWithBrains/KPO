package sample;

import java.util.*;

public class Airport { // Класс для описания аэропорта (ТОЛЬКО ДЛЯ ЭТОГО)
    HashMap<Integer,Boolean> lines;
    HashMap<Direction,Corridors> directions; // маршруты для взлета
     Airport(){
        int numbOfLines = 2;
        Directions direct = new Directions();
        this.directions = direct.getDirect();
        Lines linesl = new Lines();
        lines = linesl.GetLines(numbOfLines);
    }
}
class Lines {
    int numb;
    HashMap<Integer,Boolean> GetLines(int count){
        HashMap<Integer,Boolean> longV = new HashMap<Integer, Boolean>();
         for(int i=0;i<count;i++){
             this.numb = i;
             longV.put(numb,false); // вектор с данными по длинам полос; индекс+1 - номер полосы
         }
         return longV;
    }
}

class Directions {
    Direction[] directL = Direction.values();
    int NW = 100,NE = 110, SW = 200, SE = 210;
    HashMap<Direction,Corridors> directs = new HashMap<Direction,Corridors>();
    public HashMap<Direction,Corridors> getDirect(){
        for (int i=0;i<6;i++){
            if((Arrays.asList("NorthWest","West")).contains(directL[i].toString()) ){
                Corridors cor = new Corridors("NW",1,NW);
                directs.put(directL[i],cor);
                NW++;
            }
            if((Arrays.asList("NorthEast","East")).contains(directL[i].toString()) ){
                Corridors cor = new Corridors("NE",1,NE);
                directs.put(directL[i],cor);
                NE++;
            }
            if((Arrays.asList("SouthWest","West")).contains(directL[i].toString()) ){
                Corridors cor = new Corridors("SW",2,SW);
                directs.put(directL[i],cor);
                SW++;
            }
            if((Arrays.asList("SouthEast","East")).contains(directL[i].toString()) ){
                Corridors cor = new Corridors("SE",2,SE);
                directs.put(directL[i],cor);
                SE++;
            }
        }
        return directs;
    }
}

