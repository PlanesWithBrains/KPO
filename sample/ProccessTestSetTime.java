package sample;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;

public class ProccessTestSetTime {
    @Test
    public void setTimes() {
        Flight[] flightA = new Flight[3];
        Flight[] fl;
        LocalTime time = LocalTime.now();
        boolean flag=false;
        for (int i = 0; i < flightA.length; i++) {
            Plane pl = new Plane("Airbus 380", 4000+(i*100), 200+(i*20));
            Direction[] dr = Direction.values();
            Airline[] ar = Airline.values();
            flightA[i] = new Flight(pl, ar[i], 100+i, dr[i],time , false, 60*(i+1), 1600*(i+1));
        }
        Proccess proc = new Proccess(flightA, flightA);
        fl = proc.SetTime(flightA);

        int t=0;
        for(int i=1;i<fl.length;i++){
            if(fl[i].time.isAfter(fl[i-1].time)){
               t++;
            }
        }
        if(t==fl.length-1){flag=true;}
        Assert.assertTrue(flag);
    }

}