/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.jfoenix.controls.JFXButton;
import conexion.UsuarioCliente;
import conexion.UsuarioLogin;
import static conexion.UsuarioLogin.loginUsuario;
import conexion.objetos.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ALF
 */
public class IdentificarController implements Initializable {

    @FXML
    private VBox parentContainer;
    @FXML
    private TextField txtApodo1;
    @FXML
    private TextField txtContrasena1;
    @FXML
    private JFXButton btnAceptar1;
    @FXML
    private Label lbError;

    //nuevo
    private StackPane stack;
    private Stage stage;
    MainApp main;
    //finNuevo
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        
    } 
    
    @FXML
    private void identificarABase(ActionEvent event) throws IOException {
        
        if((loginUsuario(txtApodo1.getText(),txtContrasena1.getText()))==true){
            
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/fxml/base.fxml"));
            Parent parent = myLoader.load();           
            
            Stage stage = new Stage();
            Scene scene = new Scene(parent);        
            stage.setScene(scene);            
            stage.show();
            cerrar();
//            MainApp.stage.close();
            
//            FXMLLoader myLoader = new FXMLLoader();
//            myLoader.setLocation(BaseController.class.getResource("/fxml/base.fxml"));
//            stack = (StackPane)myLoader.load();
//            Scene scene = new Scene(stack);
//            scene.getStylesheets().add("/estilos/estilos.css");
//            stage.setScene(scene);
//            BaseController controller = (BaseController)myLoader.getController();
//            controller.setStageBase(stage);
            
        }else {
            lbError.setText("EL USUARIO O LA CONTRASEÑA SON INCORRECTOS");            
        }        
    }

    @FXML
    private void limpiarLabel(MouseEvent event) {
        
        lbError.setText("");
    }
    public void cerrar(){
  
        stage = (Stage)btnAceptar1.getScene().getWindow();
        stage.close();
    }
}
