import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.CurrentClient;

import java.io.IOException;

public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //SettingPage.fxml LoginPage.fxml UserMiniProfileComponent TimeLinePage
        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        CurrentClient currentClient =new CurrentClient();
        launch(args);
    }
}
