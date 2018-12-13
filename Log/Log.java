package Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    public String dateTime;
    public String nameFile;

    public Log(LocalDateTime dt, String name){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy | HH:mm");
        this.dateTime = dt.format(formatter);
        this.nameFile = name;
    }


}
