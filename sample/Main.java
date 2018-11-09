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
        /* вызов логики */
        System.out.println("Рейс "+"\t"+"Направление"+"\t"+"Действие"+"\t"+"Время расп."+"\t"+"Корр."+"\t"+"Сторона"+"\t"+"Полоса"+"\t"+"Факт.время");
        Proccess pr = new Proccess(demoController.getFlights(demoController.PATH_INPUT, true),demoController.getFlights(demoController.PATH_OUTPUT, true));

    }
}
