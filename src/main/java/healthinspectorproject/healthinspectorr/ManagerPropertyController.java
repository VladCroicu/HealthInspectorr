package healthinspectorproject.healthinspectorr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagerPropertyController implements Initializable {


    @FXML
    private TableView <ManagerPropertyModel> companyTableView;

    @FXML
    private TableColumn <ManagerPropertyModel,Integer>propertyIDTableColumn;
    @FXML
    private TableColumn <ManagerPropertyModel,String>managerUsernameTableColumn;
    @FXML
    private TableColumn <ManagerPropertyModel,String>companyNameTableColumn;
    @FXML
    private TableColumn <ManagerPropertyModel,String>propertyNameTableColumn;
    @FXML
    private TableColumn<ManagerPropertyModel,String>adressTableColumn;
    @FXML
    private TableColumn<ManagerPropertyModel,String>phoneNumberTableColumn;
    @FXML
    private TableColumn<ManagerPropertyModel,String>statusTableColumn;
    @FXML
    private TableColumn<ManagerPropertyModel,String>descriptionTableColumn;
    @FXML
    private Label keywordsTextField;

    @FXML
    private Button bt_back;

    @FXML
    Label nameLabel;

    ObservableList<ManagerPropertyModel> ManagerPropertyModelObservableList= FXCollections.observableArrayList();
String uzer;
    public void displayName(String username) {
        keywordsTextField.setText(username);
        nameLabel.setText("Hello,"+username+"!");
        //System.out.println(uzer);
    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        //System.out.println(uzer);


        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.getDBConnection();
        System.out.println(nameLabel.getText());
                String productViewQuery="SELECT company_id,ManagerUsername,CompanyName,PropertyName,Adress,PhoneNumber,Status,Description FROM companies;";



        try{
            Statement statement=connectDB.createStatement();
            ResultSet queryOutput=statement.executeQuery(productViewQuery);


            while(queryOutput.next()){

                Integer queryID=queryOutput.getInt("company_id");
                String queryManagerUsername=queryOutput.getString("ManagerUsername");
                String queryCompanyName=queryOutput.getString("CompanyName");
                String queryPropertyName=queryOutput.getString("PropertyName");
                String queryAdress=queryOutput.getString("Adress");
                String queryPhoneNumber=queryOutput.getString("PhoneNumber");
                String queryStatus=queryOutput.getString("Status");
                String queryDescription=queryOutput.getString("Description");

                ManagerPropertyModelObservableList.add(new ManagerPropertyModel(queryID,queryManagerUsername,queryCompanyName,queryPropertyName,queryAdress,queryPhoneNumber,queryStatus,queryDescription));
            }
            propertyIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("propertyID"));
            managerUsernameTableColumn.setCellValueFactory(new PropertyValueFactory<>("managerUsername"));
            companyNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
            propertyNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("propertyName"));
            adressTableColumn.setCellValueFactory(new PropertyValueFactory<>("adress"));
            phoneNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            statusTableColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
            descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

            companyTableView.setItems(ManagerPropertyModelObservableList);

            FilteredList <ManagerPropertyModel> filteredData=new FilteredList<>(ManagerPropertyModelObservableList,b->true);
            keywordsTextField.textProperty().addListener((observable,oldValue,newValue)->{
                filteredData.setPredicate(productSearchModel -> {

                    if(newValue.isEmpty() || newValue.isBlank() || newValue== null){
                        return true;
                    }

                    String searchkeyword=newValue.toLowerCase();

                    if(productSearchModel.getManagerUsername().toLowerCase().indexOf(searchkeyword) > -1){
                        return true;
                    } /*else if (productSearchModel.getCompanyName().toLowerCase().indexOf(searchkeyword) > -1) {
                        return true;
                    } else if (productSearchModel.getPropertyName().toLowerCase().indexOf(searchkeyword) > -1) {
                        return true;
                    } else if (productSearchModel.getAdress().toLowerCase().indexOf(searchkeyword) > -1) {
                        return true;
                    }else if(productSearchModel.getPhoneNumber().toLowerCase().indexOf(searchkeyword) > -1){
                        return true;
                    }else if(productSearchModel.getStatus().toLowerCase().indexOf(searchkeyword) > -1){
                        return true;
                    }else if(productSearchModel.getDescription().toLowerCase().indexOf(searchkeyword) > -1){
                        return true;
                    }*/else {
                        return false;
                    }

                });
            });

            SortedList<ManagerPropertyModel> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(companyTableView.comparatorProperty());
            companyTableView.setItems(sortedData);
        }
        catch (SQLException e){
            Logger.getLogger(ManagerPropertyController.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
        }

        bt_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "loggedinmanager.fxml","Log in!",null,null);

            }
        });


    }




}
