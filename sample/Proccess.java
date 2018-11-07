package sample;

import java.util.Arrays;
import java.util.Vector;

public class Proccess {// класс для обработки объектов Plane, те осуществление ранжирования по приоритетам
    // для посадки. Первый приоритет высота (от наим к наиб), второй - удаленность (аналогично),
    // макс удаленность(200 км), минимальная высота (1200м.) макс (2.700м.)
    Flight flight;// прилетающие самолеты
    Flight flightD;//вылетающие самолеты
    Airport airport = new Airport();
    Proccess(){

    }
    public void GetCommand(){
        Vector<Corridors> cor = new Vector<Corridors>();
        boolean flag=false;int i=0;
        while (!flag){
            if ((Arrays.asList("North", "NorthWest", "West", "SouthWest")).contains(flight.direction.toString())) {
                //Corridors cor;
                cor.addElement(airport.directions.get(flight.direction)); // информация о том,какой корридор занят самолетом
            }
            if ((Arrays.asList("South", "NorthEast", "East", "SouthEast")).contains(flight.direction.toString())) {
                //Corridors cor;
                cor.add(airport.directions.get(flight.direction)); // информация о том,какой корридор занят самолетом
            }
            cor.get(i).status = true;// означает, что корридор занят
            i++;
            flag = true;//выполнить инструкцию,когда работа с объектами закончена
        }
    }

}
