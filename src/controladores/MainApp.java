package controladores;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class MainApp extends Application {

    static Stage stage;
    public static Boolean isSplashLoaded = false;
   
    
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

    public static void main(String[] args) {
        launch(args);
    }   
    

}
