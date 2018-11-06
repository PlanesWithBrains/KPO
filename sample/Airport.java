package sample;

import java.util.ArrayList;
import java.util.*;

enum Direction{
    North,East,West,South,NorthWest,NorthEast,SouthWest,SouthEast
}

public class Airport { // Класс для описания аэропорта (ТОЛЬКО ДЛЯ ЭТОГО)
    ArrayList<Plane> planes; // массив самолетов (классов судов,которые принимает аэропорт)
    Vector<Integer> lines;// вектор с информацией о полосах
    HashMap<Direction,Corridors> directions; // маршруты для взлета

    // возможно, стоит дописать отдельные класы для двух полей выше(?)
     Airport(ArrayList<Plane> planes,Vector<Integer> corridors){
        int numbOfLines = 2;
        this.planes = planes;
        Directions direct = new Directions();
        this.directions = direct.getDirect();

        Lines linesl = new Lines();
        this.lines = linesl.getlongL(numbOfLines);
    }
}
class Lines {
    int longL;

    public Vector<Integer> getlongL(int count){ // count количество полос, longL их длина
        Random rand = new Random();
        Vector<Integer> longV = new Vector <Integer>();
         for(int i=0;i<count;i++){
             longL=rand.nextInt(4000-1000);
             longV.add(longL); // вектор с данными по длинам полос; индекс+1 - номер полосы
         }
         return longV;
    }

}
// логичнее создавать корридоры для направлений(?) те. направления выше в иерархии
class Directions {
    Direction[] directL = Direction.values();
    int w=100,e=200;
    HashMap<Direction,Corridors> directs = new HashMap<Direction,Corridors>();
    public HashMap<Direction,Corridors> getDirect(){
        for (int i=0;i<8;i++){
            //if(directL[i].toString() == "North" || directL[i].toString() == "NorthWest" || directL[i].toString() == "West" || directL[i].toString() == "SouthWest"){
            if((Arrays.asList("North","NorthWest","West","SouthWest")).contains(directL[i].toString()) ){
                Corridors cor = new Corridors(w,1);
                directs.put(directL[i],cor);
                w++;
            }
            if((Arrays.asList("South","NorthEast","East","SouthEast")).contains(directL[i].toString()) ){
                Corridors cor = new Corridors(e,2);
                directs.put(directL[i],cor);
                e++;
            }
            else{
                System.out.println("Ошибка в функции getDirect");
            }
        }//TODO удалить строку перед ретюрном
        System.out.println(directs.toString());
        return directs;
    }
}

class Corridors {
    int number;// номер корридора, задается в трехзначном формате "101"
    //1 (103) - означает западный торец (W), 2 (203) - восточный (E), где 03 - номер корридора
    int lines_number;// номер полосы
    char side;// какой торец полосы используется
    Corridors(int number,int lines){
        if (number>=100){
            side = 'W';
        }
        if (number>=200){
            side = 'E';
        }
        else{
            System.out.println("Задан неправильнй номер полосы!");
        }

    }
}

