package sample;
import Controllers.StartController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(Main.class.getResource("../FXML/Start.fxml")); //загружаем fxml стартового окна

        Scene scene = new Scene(root);
        stage.setTitle("Planes with Branes"); //название окна
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("../Images/icon.png")));
        stage.show(); //запускаем окно
    }


    public static void main(String[] args) {
        launch(args);

    }

}
