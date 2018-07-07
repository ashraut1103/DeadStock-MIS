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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.bson.Document;

public class DeleteItemController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXTextField item;
    @FXML
    private JFXButton search;
    @FXML
    private Label msgLabel;
    @FXML
    private JFXButton confirm;
    @FXML
    private JFXButton cancel;

    MongoClient client;
    MongoDatabase db;
    MongoCollection<Document> col;
    String query;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void searchClicked(ActionEvent event) {
        client = new MongoClient();
        db = client.getDatabase("deadstock");
        col = db.getCollection("items");
        
        query = item.getText();
        FindIterable<Document> result = col.find(eq("name",query));
        Iterator<Document> iter = result.iterator();
        
        if(iter.hasNext())
        {
            msgLabel.setText("Item found !");
            msgLabel.setStyle("-fx-text-fill : #8BC34A");
            confirm.setVisible(true);
            cancel.setVisible(true);
        }
        else
        {
            msgLabel.setText("Item not found !");
            msgLabel.setStyle("-fx-text-fill : #F44336");
            confirm.setVisible(false);
            cancel.setVisible(false);
        }
    }

    @FXML
    private void confirmClick(ActionEvent event) throws IOException {
       col.deleteOne(eq("name",query));
       
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/deadstock/ui/dialog_box/successful.fxml"));
        Parent root = (Parent)loader.load();
        SuccessfullController msg = loader.getController();
        msg.setMessage("Item "+query+" deleted successfully !");
        
        Stage s = new Stage();
        s.initModality(Modality.APPLICATION_MODAL);
        s.setTitle("Deadstock MIS");
        s.setScene(new Scene(root));
        s.show();
        
        cancelClick(event);
    }

    @FXML
    private void cancelClick(ActionEvent event) {
        Stage close = (Stage)cancel.getScene().getWindow();
        close.close();
    }
    
}
