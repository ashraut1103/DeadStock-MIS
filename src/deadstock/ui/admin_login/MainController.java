package deadstock.ui.admin_login;

import com.jfoenix.controls.JFXButton;
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

public class MainController implements Initializable 
{

    @FXML
    private JFXButton addUser;
    @FXML
    private JFXButton deleteUser;
    @FXML
    private JFXButton showUsers;
    @FXML
    private JFXButton logout;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }

    @FXML
    private void add_user(ActionEvent event) throws IOException 
    {
        try
        {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/deadstock/ui/admin_login/addUser.fxml"));
            Parent root = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Deadstock MIS");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void delete_user(ActionEvent event) {
        try
        {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/deadstock/ui/admin_login/deleteUser.fxml"));
            Parent root = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Deadstock MIS");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void show_users(ActionEvent event) {
    }

    @FXML
    private void logoutClick(ActionEvent event) throws IOException {
        Stage adminLoginStage = (Stage)logout.getScene().getWindow();
        adminLoginStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/deadstock/ui/login/login.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Login");
    }

    @FXML
    private void addItem(ActionEvent event) {
        try
        {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/deadstock/ui/admin_login/addItem.fxml"));
            Parent root = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Deadstock MIS");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteItem(ActionEvent event) {
        try
        {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/deadstock/ui/admin_login/deleteItem.fxml"));
            Parent root = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Deadstock MIS");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void editItem(ActionEvent event) {
        try
        {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/deadstock/ui/admin_login/editItem.fxml"));
            Parent root = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Deadstock MIS");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
