package sample;

import java.util.Arrays;
import java.util.Vector;
import java.time.*;

public class Proccess {// класс для обработки объектов Plane, те осуществление ранжирования по приоритетам
    // для посадки. Первый приоритет высота (от наим к наиб), второй - удаленность (аналогично),
    // макс удаленность(200 км), минимальная высота (1200м.) макс (2.700м.)
    Flight[] flight;// прилетающие самолеты
    Flight[] flightD;//вылетающие самолеты
    LocalTime time;
    Airport airport = new Airport();
    Proccess(){

    }
    public void MakingSingleRegister(Flight[] flight,Flight[] flightD){ // вызывать первой
        Flight temp;
        for (int i = flight.length + 1,j = 0; j < flightD.length; i++,j++){
           flight[i] = flightD[j]; // записываем все в один массив
        }
        for (int j = 0;j < flight.length; j++) {
            for (int i = 0; i < flight.length - 1; i++) {
                if (flight[i].time.isAfter(flight[i + 1].time)) { // сортировка по времени от меньшего к большему
                    temp = flight[i];
                    flight[i] = flight[i + 1];// оптимизировать сортировку
                    flight[i + 1] = temp;
                }
            }
        }
    }
    public void GetCommand(){
        Vector<Corridors> cor = new Vector<Corridors>();
        Vector<Lines> line = new Vector<>();
        boolean flag=false;int j=0;
        time = flight[0].time;
        for(int i = 0;i < flight.length;i++){
            if ((Arrays.asList("NorthWest", "West")).contains(flight[i].direction.toString()) && (time.isAfter(flight[i].time) || time.equals(flight[i].time))) { // я не уверен во втором условии!
                cor.addElement(airport.directions.get(flight[i].direction)); // информация о том,какой корридор занят самолетом
                //прибавлять время из расчета
                if (flight[i].hight != 0) {
                    time.plusMinutes(6);
                    //добавить проверку на свободность полосы!!!
                    //если полоса занята,то можно перевести на другую,если NW+SW~
                    //добавлять информацию о самолете(рейсе) + статусе + времени (time) в объект класса FinalTable
                }
                if(){

                }
                line.get(j).status = true;
            }
            if ((Arrays.asList("NorthEast", "East")).contains(flight[i].direction.toString())) {
                cor.addElement(airport.directions.get(flight[i].direction)); // информация о том,какой корридор занят самолетом
            }
            if ((Arrays.asList("SouthWest","West")).contains(flight[i].direction.toString())) {
                cor.addElement(airport.directions.get(flight[i].direction)); // информация о том,какой корридор занят самолетом
            }
            if ((Arrays.asList("SouthEast","East")).contains(flight[i].direction.toString())) {
                cor.addElement(airport.directions.get(flight[i].direction)); // информация о том,какой корридор занят самолетом
            }

            cor.get(j).status = true;// означает, что корридор занят
            j++;
           // if (i == flight.length)// это будут массивы объектов Flight
           // {break;}
        }
    }

}
