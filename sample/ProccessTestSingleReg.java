package sample;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class ProccessTestSingleReg {

    @Test
    public void TestSingleRegister() throws Exception {
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
            flightD[i] = new Flight(pl, ar[i], 125, dr[i], time, false, 200, 4000);
            time = time.plusMinutes(1);
        }
        Proccess proc = new Proccess(flightA, flightD);
        fl=proc.MakingSingleRegister(flightA,flightD);
        assertEquals(fl.length,flightA.length+flightD.length);

    }
}