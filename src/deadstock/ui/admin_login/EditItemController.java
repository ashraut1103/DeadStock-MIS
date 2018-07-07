package deadstock.ui.admin_login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.bson.Document;

public class EditItemController implements Initializable {

    @FXML
    private AnchorPane scrollAnchorPane;
    @FXML
    private JFXButton proceed;
    @FXML
    private JFXButton cancel;
    @FXML
    private VBox vBox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorPane;
    
    ArrayList<JFXRadioButton> checkBoxArray = new ArrayList<>();
    ToggleGroup group ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        group = new ToggleGroup();
        
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("deadstock");
        MongoCollection<Document> col = db.getCollection("items");
        FindIterable<Document> result = col.find();
        Iterator<Document> iter = result.iterator();
        
        int index = 0;
        
        while(iter.hasNext())
        {
            Document d = iter.next();
            String name = (String)d.get("name");
            JFXRadioButton item_name = new JFXRadioButton();
            item_name.setText(name);
            vBox.getChildren().add(item_name);
            checkBoxArray.add(item_name);
            item_name.setToggleGroup(group);
            index++;
        }
        
        vBox.setPrefHeight(27+(index-1)*32);
        System.out.println(vBox.getPrefHeight());
        
        scrollAnchorPane.setPrefHeight(vBox.getPrefHeight()+10);
        if(index<=9)
        {
            scrollPane.setPrefHeight(scrollAnchorPane.getPrefHeight()+10);
            proceed.setLayoutY(scrollPane.getLayoutY()+scrollPane.getPrefHeight()+22);
            cancel.setLayoutY(scrollPane.getLayoutY()+scrollPane.getPrefHeight()+22);
            anchorPane.setPrefHeight(proceed.getPrefHeight()+proceed.getLayoutY()+50);
        }
        else
        {
            scrollPane.setPrefHeight(295);
            proceed.setLayoutY(387);
            cancel.setLayoutY(387);
            anchorPane.setPrefHeight(463);
        }
    }    

    @FXML
    private void proceedClicked(ActionEvent event)
    {
        String name = (String)group.getSelectedToggle().getUserData();
    }

    @FXML
    private void cancelClicked(ActionEvent event) 
    {
        Stage close = (Stage)cancel.getScene().getWindow();
        close.close();
    }
    
}
