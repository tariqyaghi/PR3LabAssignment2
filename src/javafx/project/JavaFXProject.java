
package javafx.project;


import View.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;


public class JavaFXProject extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       ViewManager.openRegisterPage();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
