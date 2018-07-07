package deadstock.ui.dialog_box;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SuccessfullController implements Initializable {

    @FXML
    private JFXButton ok;
    @FXML
    private Label message;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    public void setMessage(String a){
        message.setText(a);
    }
    
    @FXML
    private void okClicked(ActionEvent event) {
        Stage stage = (Stage)ok.getScene().getWindow();
        stage.close();
    }
    
}
