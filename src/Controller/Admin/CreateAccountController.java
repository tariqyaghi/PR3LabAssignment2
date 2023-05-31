/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import Model.Accounts;
import View.ViewManager;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author yaghi
 */
public class CreateAccountController implements Initializable{

    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button saveNewAccountBtn;
    @FXML
    private TextField userIdTF;
    @FXML
    private TextField AccNumTF;
    @FXML
    private TextField userNameTF;
    @FXML
    private TextField currencyTF;
    @FXML
    private TextField balanceTF;
    private EntityManagerFactory emf;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emf = Persistence.createEntityManagerFactory("BankPU");

    }

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToAccountsManagment();
    }

    @FXML
    private void showOperationsPage(ActionEvent event) {
    }


    @FXML
    private void cancelUserCreation(ActionEvent event) {
        ViewManager.adminPage.changeSceneToAccountsManagment();
    }

    @FXML
    private void saveNewAccount(ActionEvent event) throws Exception {
       AccountsJpaController acc = new AccountsJpaController(emf);
       int userId= Integer.parseInt(userIdTF.getText());
       int accNum= Integer.parseInt(AccNumTF.getText());
       String username= userNameTF.getText();
       String currency= currencyTF.getText();
       double balance = Double.parseDouble(balanceTF.getText());
       Accounts account = new Accounts(null, userId, accNum, username, currency, balance);
       acc.create(account);
       
       ViewManager.adminPage.changeSceneToAccountsManagment();
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setTitle("Account inserted");
       alert.setContentText("Account inserted");
       alert.showAndWait();
    }
    
}
