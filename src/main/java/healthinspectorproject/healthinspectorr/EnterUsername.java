package healthinspectorproject.healthinspectorr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EnterUsername {

    @FXML
    private Button bt_continue;
    @FXML
    private TextField tf_username;


    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent event) throws IOException {

        String username = tf_username.getText();

        if(tf_username.getText().trim().isEmpty()){
            System.out.println("Please fill in all information");
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all information");
            alert.show();

        }else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("managerpropertyfinder.fxml"));
            root = loader.load();

            ManagerPropertyController managerPropertyController = loader.getController();
            managerPropertyController.displayName(username);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }
}
