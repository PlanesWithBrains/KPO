package sample;

import java.util.Arrays;
import java.time.*;

public class Proccess{
    Flight[] flightA;// прилетающие самолеты
    Flight[] flightD;
    static LocalTime time1;
    static LocalTime time2;
    private Corridors cor;
    private String statusF;
    FinalTable[] Table;//
    FinalTable table;//
    private Airport airport = new Airport();
    Proccess(Flight[] flightA,Flight[] flightD){
        this.flightA = flightA;
        this.flightD = flightD;
        try {
            this.flightA = SetTime(flightA);
        }
        catch (Exception e ){e.printStackTrace();}
        Flight[] flight = new Flight[flightA.length+flightD.length];
        try {
            flight = MakingSingleRegister(flightA, flightD);
        }
        catch (Exception e ){e.printStackTrace();}
        this.Table = new FinalTable[flight.length];
        try {
            GetCommand(flight);
        }
        catch (Exception e ){e.printStackTrace();}

    }
    public Flight[] SetTime(Flight[] flightA){
        LocalTime time;
        Flight temp;
        time = flightA[0].time;
        time = time.plusMinutes(2);
        for(int i=1;i<flightA.length;i++){
            int dist = (flightA[i].distance-flightA[i-1].distance)*1000;
            dist = (int)(dist/(500/3.6));
            time = time.plusSeconds(dist);
            time = time.minusMinutes(1);
            flightA[i].time = time;
        }
        return flightA;
    }

    public Flight[] MakingSingleRegister(Flight[] flightA,Flight[] flightD){ // вызывать первой
        Flight temp;
        Flight[] flight = new Flight[flightA.length+flightD.length];
        for (int j = 0; j < flightA.length;j++){
           flight[j] = flightA[j]; // записываем все в один массив
        }
        for (int i = flightA.length,j = 0; j < flightD.length; i++,j++){
            flight[i] = flightD[j]; // записываем все в один массив
        }
        int n = flight.length;
        for (int gap = n/2; gap > 0; gap /= 2)
        {
            for (int i = gap; i < n; i += 1)
            {
                temp = flight[i];
                int j;
                for (j = i; j >= gap && flight[j - gap].time.isAfter(temp.time); j -= gap)
                    flight[j] = flight[j - gap];
                flight[j] = temp;
            }
        }
        return flight;
    }
    public void GetCommand(Flight[] flight){
        boolean flag;
        Flight temp,ttemp;
        int fflag = 0;
        for(int i = 0;i < flight.length;i++) {
            boolean f1 = false, f2 = false;  //флаги для первой и второй полосы по приоритетности
            flag = false;
            if ((Arrays.asList("SouthWest", "West", "SouthEast", "East")).contains(flight[i].direction.toString())) {
                // определение времени для полос
                f2 = true;
                if(i == 0 || i == 1) {
                    if (i == 0)
                        time2 = flight[i].time;
                    if (i == 1)
                        time1 = flight[i].time;
                }
            }
            if ((Arrays.asList("NorthWest", "West", "NorthEast", "East")).contains(flight[i].direction.toString())) {
                f1 = true;
                if(i == 0 || i == 1) {
                    if (i == 0)
                        time1 = flight[i].time;
                    if (i == 1)
                        time2 = flight[i].time;
                }

            }
            if ((Arrays.asList("East", "West")).contains(flight[i].direction.toString())) {
                if (time1.isBefore(time2) && !flag) {
                    f1 = true;// приоритет присвоен первой полосе
                    f2 = false;
                }
                if (time1.isAfter(time2) && !flag) {
                    f2 = true;// приоритет присвоен второй полосе
                    f1 = false;
                }
            }
            if ((Arrays.asList("SouthWest", "West")).contains(flight[i].direction.toString()) && !flag && f2) {
                flag=GetInstructions(flight,i,flag,f2);// возвращает статус занятости корридора
            }
            if ((Arrays.asList("SouthEast", "East")).contains(flight[i].direction.toString()) && !flag && f2) {
                flag=GetInstructions(flight,i,flag,f2);
            }
            if ((Arrays.asList("NorthWest", "West")).contains(flight[i].direction.toString()) && !flag && f1) {
                //на вид вызовы схожи, но оставлены оба, для дальнейшей TODO работы с соответствием торцов!
                flag=GetInstructions(flight,i,flag,f1);
            }
            if ((Arrays.asList("NorthEast", "East")).contains(flight[i].direction.toString()) && !flag && f1) {
                flag=GetInstructions(flight,i,flag,f1);
            }
            if (!flag) {
                temp = flight[i];
                flight[i] = flight[i + 1];
                flight[i + 1] = temp;
                fflag++;
            }
            if (fflag == 2) {
                temp = flight[i];
                ttemp = flight[i + 1];
                flight[i] = flight[i + 2];
                flight[i + 1] = temp;
                flight[i + 2] = ttemp;
                time1 = time1.plusMinutes(1);
                time2 = time2.plusMinutes(1);
                fflag = 0;
            }
            if (!flag && fflag!=2) {
                i--;
            }
        }
    }
    boolean GetInstructions(Flight[] flight,int i,boolean flag,boolean f){
        boolean corStatus = false;
        if((Arrays.asList("NorthWest", "West","NorthEast", "East")).contains(flight[i].direction.toString())&& f) {
            cor = (airport.directions.get(flight[i].direction));
            if (flight[i].hight != 0) {
                time1 = time1.plusMinutes(7);
                statusF = "Посадка";
                table = new FinalTable(flight[i], cor, 1, time1, statusF);
                Table[i] = table;
            }
            if (flight[i].hight == 0) {
                time1 = time1.plusMinutes(6);
                statusF = "Взлет";
                table = new FinalTable(flight[i], cor, 1, time1, statusF);
                Table[i] = table;
            }
            airport.lines.replace(0, true);
            cor.status = true;//присваеваем полосе статус "занятости"
            corStatus = cor.status;// перезапись для того,чтобы вернуть и сохранить логику
            //flag = true;
        }
        if ((Arrays.asList("SouthWest", "West", "SouthEast", "East")).contains(flight[i].direction.toString())&& f) {
            cor = (airport.directions.get(flight[i].direction)); // информация о том,какой корридор занят самолетом
            if (flight[i].hight != 0) {
                time2 = time2.plusMinutes(6);
                statusF = "Посадка";
                table = new FinalTable(flight[i], cor, 2, time2, statusF);
                Table[i] = table;
            }
            if (flight[i].hight == 0) {
                time2 = time2.plusMinutes(5);
                statusF = "Взлет";
                table = new FinalTable(flight[i], cor, 2, time2, statusF);
                Table[i] = table;
            }
            airport.lines.replace(1, true);
            cor.status = true;
            corStatus = cor.status;
        }
        return corStatus;
    }
    FinalTable[] GetTable(){
        return Table;
    }

}
