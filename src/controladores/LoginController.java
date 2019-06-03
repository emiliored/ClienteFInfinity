/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ALF
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane loginParent;
    @FXML
    private StackPane baseContainer;
    @FXML
    private VBox vbox;
    
    private Parent parent;
    private Stage stage;
           
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if (!MainApp.isSplashLoaded) {
            loadSplashScreen();
        }
        
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 22);
        t.play();
        t.setOnFinished((e) -> {
            try {
                parent = FXMLLoader.load(getClass().getResource("/fxml/Identificar.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(parent);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
       
    }  
    // nuevo metodo establece stage
    public void setStageLogin(Stage stage){
        
        this.stage=stage;
    }//fin metodo

    @FXML
    private void registrar(ActionEvent event) {
        
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(20);
        t.play();
        t.setOnFinished((e) -> {
            try {
                parent = FXMLLoader.load(getClass().getResource("/fxml/Registrar.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(parent);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void identificar(ActionEvent event) {
        
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 22);
        t.play();
        t.setOnFinished((e) -> {
            try {
                parent = FXMLLoader.load(getClass().getResource("/fxml/Identificar.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(parent);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }
    
    private void loadSplashScreen() {
        try {
            MainApp.isSplashLoaded = true;

            StackPane pane = FXMLLoader.load(getClass().getResource(("/fxml/Splash.fxml")));
            loginParent.getChildren().setAll(pane);

            javafx.animation.FadeTransition fadeIn = new javafx.animation.FadeTransition(Duration.seconds(4), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            javafx.animation.FadeTransition fadeOut = new javafx.animation.FadeTransition(Duration.seconds(1), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("/fxml/Login.fxml")));
                    loginParent.getChildren().setAll(parentContent);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cerrar() {
        
       
    }
}
