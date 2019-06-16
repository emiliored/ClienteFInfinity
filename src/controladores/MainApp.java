package controladores;


import conexion.Conectar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//finNuevo



public class MainApp extends Application {

    public static Boolean isSplashLoaded = false;    
    private Stage stage;
   
    
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));

        Parent root = myLoader.load();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/estilos/estilos.css");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/imagenes/icons8_infinity_large_40px.png")));
        stage.setScene(scene);
        stage.show();       
    }   


    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.trustStore", "keystore.jks");
        Conectar.IPSERVER=args[0];
        launch(args);
    }   
    

}
