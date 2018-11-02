package sample;

import java.util.ArrayList;

public class Plane {
    String name; //название судна
    int lengthRunway; //длина необходимой взлетки
    int countPassangers;

    Plane(String Name, int LengthRun, int CountPass){
        this.name = Name;
        this.lengthRunway = LengthRun;
        this.countPassangers = CountPass;
    }

}
