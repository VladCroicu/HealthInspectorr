package healthinspectorproject.healthinspectorr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertySearchController implements Initializable {


    @FXML
    private TableView <PropertySearchModel> companyTableView;

    @FXML
    private TableColumn <PropertySearchModel,Integer>propertyIDTableColumn;
    @FXML
    private TableColumn <PropertySearchModel,String>managerUsernameTableColumn;
    @FXML
    private TableColumn <PropertySearchModel,String>companyNameTableColumn;
    @FXML
    private TableColumn <PropertySearchModel,String>propertyNameTableColumn;
    @FXML
    private TableColumn<PropertySearchModel,String>adressTableColumn;
    @FXML
    private TableColumn<PropertySearchModel,String>phoneNumberTableColumn;
    @FXML
    private TableColumn<PropertySearchModel,String>statusTableColumn;
    @FXML
    private TableColumn<PropertySearchModel,String>descriptionTableColumn;
    @FXML
    private TextField keywordsTextField;

    ObservableList<PropertySearchModel> propertySearchModelObservableList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {

        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.getDBConnection();
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

                propertySearchModelObservableList.add(new PropertySearchModel(queryID,queryManagerUsername,queryCompanyName,queryPropertyName,queryAdress,queryPhoneNumber,queryStatus,queryDescription));
            }
            propertyIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("propertyID"));
            managerUsernameTableColumn.setCellValueFactory(new PropertyValueFactory<>("managerUsername"));
            companyNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
            propertyNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("propertyName"));
            adressTableColumn.setCellValueFactory(new PropertyValueFactory<>("adress"));
            phoneNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            statusTableColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
            descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

            companyTableView.setItems(propertySearchModelObservableList);

            FilteredList <PropertySearchModel> filteredData=new FilteredList<>(propertySearchModelObservableList,b->true);
            keywordsTextField.textProperty().addListener((observable,oldValue,newValue)->{
                filteredData.setPredicate(productSearchModel -> {

                    if(newValue.isEmpty() || newValue.isBlank() || newValue== null){
                        return true;
                    }

                    String searchkeyword=newValue.toLowerCase();

                    if(productSearchModel.getManagerUsername().toLowerCase().indexOf(searchkeyword) > -1){
                        return true;
                    } else if (productSearchModel.getCompanyName().toLowerCase().indexOf(searchkeyword) > -1) {
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
                    }else {
                        return false;
                    }

                });
            });

            SortedList<PropertySearchModel> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(companyTableView.comparatorProperty());
            companyTableView.setItems(sortedData);
        }
        catch (SQLException e){
            Logger.getLogger(PropertySearchController.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
        }
    }
}
