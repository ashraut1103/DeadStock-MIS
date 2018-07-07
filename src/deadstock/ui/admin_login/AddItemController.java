package deadstock.ui.admin_login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import deadstock.ui.dialog_box.SuccessfullController;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.bson.Document;

public class AddItemController implements Initializable 
{

    @FXML
    private JFXTextField item;
    @FXML
    private JFXButton add;
    @FXML
    private AnchorPane anchor;
    
    private ArrayList<JFXTextField> specsArray;
    private ArrayList<JFXComboBox> typeArray;
    int i;
    @FXML
    private VBox vbox;
    @FXML
    private JFXButton delete;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        i=0;
        specsArray = new ArrayList<>();
        typeArray = new ArrayList<>();
    }    

    @FXML
    private void add(ActionEvent event) {
        if(i!=0)
        {
            vbox.getChildren().remove(i);
        }
        
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
    
        specsArray.add(specs);
        typeArray.add(type);
        i++;
        
        HBox hbox = new HBox(30);
        hbox.setPadding(new Insets(15,0,12,15));
        hbox.getChildren().addAll(specs,type);
        hbox.setPrefHeight(51);
        
        vbox.setSpacing(10);
        anchor.setPrefHeight(anchor.getPrefHeight()+51);       
        vbox.getChildren().add(hbox);
        
        JFXButton confirm = new JFXButton();
        confirm.setPrefWidth(88);
        confirm.setPrefHeight(27);
        confirm.setText("Confirm");
        confirm.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                    MongoClient client = new MongoClient();
                    MongoDatabase db = client.getDatabase("deadstock");
                    MongoCollection<Document> col = db.getCollection("items");
                    Document document = new Document();
                    BasicDBObject one = new BasicDBObject();
                    for(int j=0;j<i;j++)
                    {
                        one.append(specsArray.get(j).getText(),typeArray.get(j).getValue().toString());
                    }
                    document.put("name", item.getText());
                    document.put("specs", one);
                    col.insertOne(document);
                    
                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/deadstock/ui/dialog_box/successful.fxml"));
                    Parent root = (Parent)fxmlloader.load();
                    SuccessfullController msg = fxmlloader.getController();
                    msg.setMessage("Item Added Successfully !");
            
                    Stage stage = new Stage();
                    stage.setTitle("Deadstock MIS");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root));
                    stage.show();
                    
                    Stage close = (Stage)confirm.getScene().getWindow();
                    close.close();
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        });
        
        JFXButton cancel = new JFXButton();
        cancel.setPrefWidth(88);
        cancel.setPrefHeight(27);
        cancel.setText("Cancel");
        cancel.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Stage close = (Stage)cancel.getScene().getWindow();
                close.close();
            }
        });
        
        HBox buttonHbox = new HBox(50);
        buttonHbox.setPadding(new Insets(30,0,10,80));
        buttonHbox.getChildren().addAll(confirm,cancel);
        anchor.setPrefHeight(anchor.getPrefHeight()+15);
        vbox.getChildren().add(buttonHbox);
    }

    @FXML
    private void deleteSpec(ActionEvent event) {
        if(i!=0)
        {
            vbox.getChildren().remove(i-1);
            anchor.setPrefHeight(anchor.getPrefHeight()-51);
            i--;
        }
    }
}