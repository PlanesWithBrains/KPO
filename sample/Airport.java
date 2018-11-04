package sample;

import java.util.ArrayList;
import java.util.*;

enum Direction{
    North,East,West,South,NorthWest,NorthEast,SouthWest,SouthEast
}

public class Airport {
    ArrayList<Plane> planes; // массив самолетов (классов судов,которые принимает аэропорт)
    Vector<Integer> lines;
    Vector<String> directions; // маршруты для взлета
    Vector<Integer> corridors; // корридоры для посадки
    // возможно, стоит дописать отдельные класы для двух полей выше(?)
    void Airport(ArrayList<Plane> planes,Vector<Integer> directions,Vector<Integer> corridors){
        int numbOfLines = 2;
        this.planes = planes;
        Directions direct = new Directions();
        //this.directions = direct.getDirect();
        this.corridors = corridors;
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

class Directions {
    Direction direction;
    Vector<String> direct = new Vector<String>();
    Direction[] directL = Direction.values();
    public Vector<String> getDirect(){
        for (int i=0;i<8;i++){
            direction = directL[i];
            direct.add(direction.toString());// вектор хранящий направления для вылетов
        }
        return direct;
    }
}

class Corridors { // необходимо узнать: подкаждую ли полосу выделяются свои корридоры
    //либо же корридоры не зависят от полос
    //TODO создавать объект Directions в этом классе, необходима связь между корридором и направлением.
    int number;
    int lines_number;
    Directions direct = new Directions();
    //direct.

}

