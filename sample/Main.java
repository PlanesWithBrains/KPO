package sample;
import Controllers.demoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/formBrowse.fxml")); //загружаем fxml стартового окна

        Scene scene = new Scene(root);
        stage.setTitle("Planes with Branes ©Hobots inc (DEMO)"); //название окна
        stage.setScene(scene);
        stage.show(); //запускаем окно
    }


    public static void main(String[] args) {
        launch(args);
        FinalTable[] tab;
        FinalTable temp;
        /* вызов логики */
        System.out.println("Рейс " + "\t" + "Действие" + "\t" + "Направление" + "\t" + "Время расп." + "\t" + "Корр." + " " + "Стор." + " " + "Полосa" + "\t" + "Факт.время");
        Proccess pr = new Proccess(demoController.getFlights(demoController.PATH_INPUT, true), demoController.getFlights(demoController.PATH_OUTPUT, true));
        tab = pr.GetTable();
        int n = tab.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                temp = tab[i];
                int j;
                for (j = i; j >= gap && tab[j - gap].time.isAfter(temp.time); j -= gap)
                    tab[j] = tab[j - gap];
                tab[j] = temp;
            }
        }
        for (int i = 0; i < tab.length; i++) {
            System.out.println(tab[i].flight.number + "" + tab[i].flight.carrier + "\t" + tab[i].statusF + "\t" + "\t" + tab[i].flight.direction.toString() + "\t" + "\t" + tab[i].flight.time + "\t" + tab[i].cor.number + "\t" + tab[i].cor.side + "\t" + tab[i].line + "\t" + tab[i].time);
        }
    }

}
