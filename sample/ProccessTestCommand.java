package sample;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.*;

public class ProccessTestCommand {
    static Flight[] fl;
    @Test
    public void TestGetCommand(){
            LocalTime time = LocalTime.now();
            Flight[] flightA = new Flight[2];
            Flight[] flightD = new Flight[3];
            fl = new Flight[flightA.length+flightD.length];
            Flight[] fl1 = new Flight[flightA.length+flightD.length];
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
            Proccess pr = new Proccess(flightA,flightD);
            fl=pr.MakingSingleRegister(flightA,flightD);
            for(int i=0;i<fl.length;i++) fl1[i] = fl[i];
            pr.GetCommand(fl);
            int t=0;
            boolean flag=false;
            for(int i=0;i<fl.length;i++){
                if(fl[i].time.isAfter(fl1[i].time)||fl[i].time.equals(fl1[i].time)){
                    t++;
                }
            }
            if (t==fl.length){
                flag=true;
            }
            Assert.assertTrue(flag);
    }


}