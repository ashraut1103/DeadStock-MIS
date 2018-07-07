package deadstock.ui.admin_login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.bson.Document;

public class SpecChangeController implements Initializable 
{

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox vBox;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton cancel;
    
    String item;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("Deadstock");
        MongoCollection col = db.getCollection("items");
        FindIterable<Document> find = col.find(eq("name",item));
        Iterator iter = find.iterator();
        
        while(iter.hasNext())
        {
            Document doc = (Document)iter.next();
            BasicDBObject subDoc = (BasicDBObject) doc.get("specs");
            
            JFXTextField specs = new JFXTextField();
            specs.setLabelFloat(true);
            specs.setPromptText("Name");
            specs.setPrefWidth(202);
            specs.setPrefHeight(31);
        
            ObservableList<String> list = FXCollections.observableArrayList("String","Integer");
            JFXComboBox type = new JFXComboBox(list);
            type.setPromptText("Type");
            type.setPrefWidth(100);
            type.setPrefHeight(24);
    
            HBox hbox = new HBox(30);
            hbox.setPadding(new Insets(15,0,12,15));
            hbox.getChildren().addAll(specs,type);
            hbox.setPrefHeight(51);
        }
    }    

    @FXML
    private void saveClick(ActionEvent event) {
    }

    @FXML
    private void cancelClick(ActionEvent event) {
    }
    
    public void setItemName(String s)
    {
        item = s;
    }
}
