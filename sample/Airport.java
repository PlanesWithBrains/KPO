package sample;

import java.util.*;

enum Direction{
    NorthWest,NorthEast,East,West,SouthWest,SouthEast
}

public class Airport { // Класс для описания аэропорта (ТОЛЬКО ДЛЯ ЭТОГО)
    //ArrayList<Plane> planes; // массив самолетов (классов судов,которые принимает аэропорт)
    //Vector<Lines> lines;// вектор с информацией о полосах
    HashMap<Integer,Boolean> lines;
    HashMap<Direction,Corridors> directions; // маршруты для взлета

    // возможно, стоит дописать отдельные класы для двух полей выше(?)
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
    boolean status;

    HashMap<Integer,Boolean> GetLines(int count){ // count количество полос, longL их длина
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
    int NW = 100,NE = 100, SW = 200, SE = 200;
    HashMap<Direction,Corridors> directs = new HashMap<Direction,Corridors>();
    public HashMap<Direction,Corridors> getDirect(){
        for (int i=0;i<6;i++){
            if((Arrays.asList("NorthWest","West")).contains(directL[i].toString()) ){
                Corridors cor = new Corridors("NW",1);
                directs.put(directL[i],cor);
                NW++;
            }
            if((Arrays.asList("NorthEast","East")).contains(directL[i].toString()) ){
                Corridors cor = new Corridors("NE",1);
                directs.put(directL[i],cor);
                NE++;
            }

            if((Arrays.asList("SouthWest","West")).contains(directL[i].toString()) ){
                Corridors cor = new Corridors("SW",2);
                directs.put(directL[i],cor);
                SW++;
            }
            if((Arrays.asList("SouthEast","East")).contains(directL[i].toString()) ){
                Corridors cor = new Corridors("SE",2);
                directs.put(directL[i],cor);
                SE++;
            }
            else{
                System.out.println("Ошибка в функции getDirect");
            }
        }//TODO на билде, удалить строку перед ретюрном
        System.out.println(directs.toString());
        return directs;
    }
}

class Corridors {
    int number;// номер корридора, задается в трехзначном формате "101"
    //1 (103) - означает западный торец (W), 2 (203) - восточный (E), где 03 - номер корридора
    int lines_number;// номер полосы
    String side;// какой торец полосы используется
    boolean status;
    Corridors(String s,int lines){
        if (s.equals("NW")){
            side = "W1";
        }
        if (s.equals("NE")){
            side = "E1";
        }
        if (s.equals("SW")){
            side = "W2";
        }
        if (s.equals("SE")){
            side = "E2";
        }
        else{
            System.out.println("Задан неправильнй номер полосы!");
        }

    }
}

