package deadstock.ui.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import deadstock.ui.dialog_box.ErrorController;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle; 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.bson.Document;

public class LoginController implements Initializable 
{

    @FXML
    private JFXTextField user;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private JFXButton login;
    MongoClient mongo;
    MongoDatabase db;
    MongoCollection<Document> col;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        mongo = new MongoClient( "localhost" , 27017 );
        db = mongo.getDatabase("deadstock");
        col = db.getCollection("login");
    }    

    @FXML
    private void login(ActionEvent event) throws IOException 
    {
        String username = user.getText();
        String password = pass.getText();
        
        FindIterable<Document> cursor = col.find(and(eq("user",username),eq("pass",password)));
        Iterator<Document> iter = cursor.iterator();
        
        if(iter.hasNext())
        {
            try
            {
                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/deadstock/ui/admin_login/main.fxml"));
                Stage loginStage = (Stage)login.getScene().getWindow();
                loginStage.close();
                Parent root = (Parent)fxmlloader.load();
                Stage stage = new Stage();
                stage.setTitle("Deadstock MIS");
                stage.setScene(new Scene(root));
                stage.show();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/deadstock/ui/dialog_box/error.fxml"));
            Parent root = (Parent)fxmlloader.load();
            ErrorController msg = fxmlloader.getController();
            msg.setMessage("Username or Password doesn't match!");
            
            Stage stage = new Stage();
            stage.setTitle("Deadstock MIS");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}
