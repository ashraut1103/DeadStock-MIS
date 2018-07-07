package deadstock.ui.admin_login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import deadstock.ui.dialog_box.SuccessfullController;
import java.io.IOException;
import java.net.URL;
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

public class Add_userController implements Initializable 
{

    @FXML
    private JFXTextField fname;
    @FXML
    private JFXTextField lname;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField lab;
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton cancel;
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    

    @FXML
    private void addClick(ActionEvent event) throws IOException 
    {
        String first = fname.getText();
        String last = lname.getText();
        String user = username.getText();
        String assignedLab = lab.getText();
        
            MongoClient mongo = new MongoClient();
            MongoDatabase db = mongo.getDatabase("deadstock");
            MongoCollection<Document> col = db.getCollection("users");
            Document userDoc = new Document();
            userDoc.put("fname", first);
            userDoc.put("lname", last);
            userDoc.put("username", user);
            userDoc.put("lab", assignedLab);
            col.insertOne(userDoc);
            
            MongoCollection<Document> col1 = db.getCollection("login");
            Document loginDoc = new Document();
            loginDoc.put("user",user);
            loginDoc.put("pass", "12345678");
            loginDoc.put("security", "default");
            col1.insertOne(loginDoc);
        
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/deadstock/ui/dialog_box/successful.fxml"));
            Parent root = (Parent)fxmlloader.load();
            SuccessfullController msg = fxmlloader.getController();
            msg.setMessage("User added successfully!");
            
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Deadstock MIS");
            stage.setScene(new Scene(root));
            stage.show();
    }

    @FXML
    private void cancelClick(ActionEvent event) {
        Stage stage = (Stage)cancel.getScene().getWindow();
        stage.close();
    }
    
}
