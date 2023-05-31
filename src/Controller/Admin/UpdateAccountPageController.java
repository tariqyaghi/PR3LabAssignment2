
package Controller.Admin;

import Model.Accounts;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class UpdateAccountPageController implements Initializable {
    private Accounts oldAcc;
    @FXML
    private TextField usernameTF;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField userIdTF;
    @FXML
    private TextField accNumTF;
    @FXML
    private Button updateAccountBtn;
    @FXML
    private TextField currencyTF;
    @FXML
    private TextField balanceTF;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankPU");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.oldAcc = Controller.Admin.AccountsManagmentController.selectedAccToUpdate;
        usernameTF.setText(oldAcc.getUsername());
        userIdTF.setText(String.valueOf(oldAcc.getUserId()));
        accNumTF.setText(String.valueOf(oldAcc.getAccountNumber()));
        currencyTF.setText(oldAcc.getCurrency());
        balanceTF.setText(String.valueOf(oldAcc.getBalance()));
    }    

    @FXML
    private void updateAccount(ActionEvent event) throws Exception {
         AccountsJpaController accs = new AccountsJpaController(emf);
         Integer id = oldAcc.getId();
         String username = usernameTF.getText();
         int userId = Integer.parseInt(userIdTF.getText());
         int accNum = Integer.parseInt(accNumTF.getText());
         double balance = Double.parseDouble(balanceTF.getText());
         String currency = currencyTF.getText();
         Accounts newAcc = new Accounts(id, userId, accNum, username, currency, balance);
         accs.edit(newAcc);
    }
    @FXML
    private void cancelUpdate(ActionEvent event) {
        Controller.Admin.UsersManagmentController.updateStage.close();
    }
    
}
