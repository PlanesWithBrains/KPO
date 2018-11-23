package sample;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.*;


public class ProccessTest {
    @Test
    public void makingSingleRegister() {
        LocalTime time = LocalTime.now();
        Flight[] flightA = new Flight[2];
        Flight[] flightD = new Flight[3];
        Flight[] fl;
        for(int i=0;i<flightA.length;i++){
            Plane pl = new Plane("Airbus 380", 4000, 200);
            Direction[] dr = Direction.values();
            Airline[] ar = Airline.values();
            flightA[i] = new Flight(pl, ar[i], 100, dr[i], time, false, 100, 2000);
            time = time.plusMinutes(1);

        }
        for(int i=0;i<flightD.length;i++){
            Plane pl = new Plane("Boeing 777", 3000, 300);
            Direction[] dr = Direction.values();
            Airline[] ar = Airline.values();
            flightD[i] = new Flight(pl, ar[i], 125, dr[i], time, false, 0, 0);
            time = time.plusMinutes(1);
        }
        Proccess proc = new Proccess(flightA, flightD);
        fl=proc.MakingSingleRegister(flightA,flightD);
        int t=0;
        boolean f=false;
        for (int i=1;i<fl.length;i++){
            if (fl[i].time.isAfter(fl[i-1].time)||fl[i].time.equals(fl[i-1].time))t++;
        }
        if(t==fl.length-1)f=true;
        Assert.assertTrue(f);

    }
}

