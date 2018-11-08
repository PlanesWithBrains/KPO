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
    Proccess(Flight[] flightA,Flight[] flightD){
        this.flight = flightA;
        this.flightD = flightD;
        MakingSingleRegister(flight,flightD);
        GetCommand();

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
        Corridors cor;
        Flight temp;
        FinalTable FT;

        boolean flag=false;
        time = flight[0].time;
        for(int i = 0;i < flight.length;i++){
            if(flight[i].time.isAfter(time)){
                airport.lines.replace(0,false);
                airport.lines.replace(1,false);
            }
            if ((Arrays.asList("NorthWest", "West")).contains(flight[i].direction.toString()) && (!time.isBefore(flight[i].time) || !airport.lines.get(0))) { // я не уверен во втором условии!
                cor = (airport.directions.get(flight[i].direction)); // информация о том,какой корридор занят самолетом
                //прибавлять время из расчета
                if (flight[i].hight != 0) {
                    time = time.plusMinutes(7);
                    //добавлять информацию о самолете(рейсе) + статусе + времени (time) в объект класса FinalTable
                    FT = new FinalTable(flight[i],cor,1,time);
                }
                if (flight[i].hight == 0) {
                    time = time.plusMinutes(6);
                    FT = new FinalTable(flight[i],cor,1,time);
                }

                airport.lines.replace(0,true);
            }
            if ((Arrays.asList("NorthEast", "East")).contains(flight[i].direction.toString()) && (!time.isBefore(flight[i].time) || !airport.lines.get(0))) {
                cor = (airport.directions.get(flight[i].direction));
                if (flight[i].hight != 0) {
                    time = time.plusMinutes(7);
                    FT = new FinalTable(flight[i],cor,1,time);
                }
                if (flight[i].hight == 0) {
                    time = time.plusMinutes(6);
                    FT = new FinalTable(flight[i],cor,1,time);
                }
                airport.lines.replace(0,true);
            }
            if ((Arrays.asList("SouthWest","West")).contains(flight[i].direction.toString()) && (!time.isBefore(flight[i].time) || !airport.lines.get(1))) {
                cor = (airport.directions.get(flight[i].direction)); // информация о том,какой корридор занят самолетом
                if (flight[i].hight != 0) {
                    time = time.plusMinutes(6);
                    FT = new FinalTable(flight[i],cor,2,time);
                }
                if (flight[i].hight == 0) {
                    time = time.plusMinutes(5);
                    FT = new FinalTable(flight[i],cor,2,time);
                }
                airport.lines.replace(1,true);
            }
            if ((Arrays.asList("SouthEast","East")).contains(flight[i].direction.toString())&& (!time.isBefore(flight[i].time) || !airport.lines.get(1))) {
                cor = (airport.directions.get(flight[i].direction)); // информация о том,какой корридор занят самолетом
                if (flight[i].hight != 0) {
                    time = time.plusMinutes(6);
                    FT = new FinalTable(flight[i],cor,2,time);
                }
                if (flight[i].hight == 0) {
                    time = time.plusMinutes(5);
                    FT = new FinalTable(flight[i],cor,2,time);
                }
                airport.lines.replace(1,true);
            }
            else{
                temp = flight[i];
                flight[i] = flight[i+1];
                flight[i+1] = temp;
                i--;
            }

        }
    }

}