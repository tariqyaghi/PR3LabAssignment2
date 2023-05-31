/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Controller.Admin.exceptions.NonexistentEntityException;
import Model.Accounts;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class AccountsManagmentController implements Initializable{
    public static Accounts selectedAccToUpdate;
    public static Stage updateStage;
    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button createNewAccountrBtn;
    @FXML
    private Button updateSelectedAccountBtn;
    @FXML
    private Button deleteSelectedAccountBtn;
    @FXML
    private Button showAllAccounts;
    @FXML
    private TableView<Accounts> accountsTableView;
    @FXML
    private TableColumn<Accounts, Integer> accID;
    @FXML
    private TableColumn<Accounts, Integer> accNum;
    private TableColumn<Accounts, Integer> userId;
    @FXML
    private TableColumn<Accounts, String> currency;
    @FXML
    private TableColumn<Accounts, Double> balance;
    @FXML
    private TableColumn<Accounts, Date> date;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankPU");
    @FXML
    private TableColumn<Accounts, String> userName;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accID.setCellValueFactory(new PropertyValueFactory("id"));
        userId.setCellValueFactory(new PropertyValueFactory("userId"));
        accNum.setCellValueFactory(new PropertyValueFactory("accountNumber"));
        userName.setCellValueFactory(new PropertyValueFactory("username"));
        currency.setCellValueFactory(new PropertyValueFactory("currency"));
        balance.setCellValueFactory(new PropertyValueFactory("balance"));
        date.setCellValueFactory(new PropertyValueFactory("creationDate"));
    }    

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
         ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
    }

    @FXML
    private void showOperationsPage(ActionEvent event) {
    }

    @FXML
    private void showAccountCreationPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToCreateAccount();
    }

    @FXML
    private void showAllAccounts(ActionEvent event) {
        AccountsJpaController accs = new AccountsJpaController(emf);
        ObservableList<Accounts> accsList = FXCollections.observableArrayList(accs.findAccountsEntities());
        accountsTableView.setItems(accsList);
    }

    @FXML
    private void updateSelectedAccount(ActionEvent event) throws IOException {
        if (accountsTableView.getSelectionModel().getSelectedItem()!=null){
            selectedAccToUpdate = accountsTableView.getSelectionModel().getSelectedItem();
            FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/View/AdminFXML/UpdateAccountPage.fxml"));
            Parent rootUpdate = loaderUpdate.load();
            Scene scene = new Scene(rootUpdate);
            updateStage = new Stage();
            updateStage.setScene(scene);
            updateStage.setTitle("Update Account");
            updateStage.show();
        }
    }

    @FXML
    private void deleteSelectedAccount(ActionEvent event) throws NonexistentEntityException {
        AccountsJpaController accs = new AccountsJpaController(emf);
        if (accountsTableView.getSelectionModel().getSelectedItem() != null) {
            Accounts acc= accountsTableView.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Account?");
            alert.setContentText("Are you sure you want to delete this account?");
            alert.showAndWait().ifPresent(response ->{
            if (response == ButtonType.OK) {
                try {
                    accs.destroy(acc.getId());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(AccountsManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        }
    }  
}
