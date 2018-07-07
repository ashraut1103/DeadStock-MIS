package deadstockmis;

import deadstock.database.DatabaseHandler;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DeadstockMIS extends Application {
    
    @Override
    public void start(Stage stage) throws IOException 
    {
        new DatabaseHandler();
        
        Parent root = FXMLLoader.load(getClass().getResource("/deadstock/ui/login/login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Login");
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
