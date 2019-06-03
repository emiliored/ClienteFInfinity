package controladores;


//import javafx.application.Application;
//import static javafx.application.Application.launch;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;

//nuevo
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//finNuevo



public class MainApp extends Application {

    //static Stage stage;
    public static Boolean isSplashLoaded = false;
    
    //Nuevo
   static Stage stage;
   private AnchorPane rootLayout;
   
    
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));

        Parent root = myLoader.load();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/estilos/estilos.css");
        stage.setScene(scene);
        stage.show();       
    }

    
//    //nuevo metodo q obtiene stage
//    public Stage getStage() {
//        return stage;
//    }
//    //fin obtiene stage
//    public void cerrar(Stage stage){
//        this.stage.close();
//    }

    public static void main(String[] args) {
        launch(args);
    }   
    

}
