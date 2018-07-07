package deadstock.ui.admin_login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import deadstock.ui.dialog_box.SuccessfullController;
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
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.bson.Document;

public class DeleteUserController implements Initializable {

    @FXML
    private JFXTextField find;
    @FXML
    private Label nameLabel;
    @FXML
    private Label userLabel;
    @FXML
    private Label labLabel;
    @FXML
    private JFXButton confirm;
    @FXML
    private JFXButton cancel;
    @FXML
    private Label name;
    @FXML
    private Label username;
    @FXML
    private Label lab;
    @FXML
    private Label errorLabel;
    @FXML
    private JFXButton ok;
    @FXML
    private JFXButton search;
    
    private MongoClient mongo;
    private MongoDatabase db;
    private MongoCollection<Document> col;
    private static String query;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mongo = new MongoClient();
        db = mongo.getDatabase("deadstock");
    }    

    @FXML
    private void confirm(ActionEvent event) throws IOException {
        col = db.getCollection("users");
        col.deleteOne(eq("username",query));
        
        col = db.getCollection("login");
        col.deleteOne(eq("user",query));
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deadstock/ui/dialog_box/successful.fxml"));
        Parent root = (Parent)loader.load();
        SuccessfullController msg = loader.getController();
        msg.setMessage("User "+query+" deleted successfully !");
        
        Stage s = new Stage();
        s.initModality(Modality.APPLICATION_MODAL);
        s.setTitle("Deadstock MIS");
        s.setScene(new Scene(root));
        s.show();
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage s = (Stage)cancel.getScene().getWindow();
        s.close();
    }

    @FXML
    private void okClicked(ActionEvent event) {
        Stage s = (Stage)ok.getScene().getWindow();
        s.close();
    }

    @FXML
    private void search(ActionEvent event) {
        query = find.getText();
        col = db.getCollection("users");
        FindIterable<Document> result = col.find(eq("username",query));
        Iterator<Document> iter = result.iterator();
        
        if(iter.hasNext())
        {
            nameLabel.setVisible(true);
            userLabel.setVisible(true);
            labLabel.setVisible(true);
            confirm.setVisible(true);
            cancel.setVisible(true);
            Document cur = iter.next();
            name.setText(cur.getString("fname")+" "+cur.getString("lname"));
            username.setText(cur.getString("username"));
            lab.setText(cur.getString("lab"));
        }
        else
        {
            nameLabel.setVisible(false);
            userLabel.setVisible(false);
            labLabel.setVisible(false);
            confirm.setVisible(false);
            cancel.setVisible(false);
            errorLabel.setVisible(true);
            ok.setVisible(true);
        }
    }
    
}
