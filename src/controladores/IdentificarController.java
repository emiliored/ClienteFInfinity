/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.jfoenix.controls.JFXButton;
import static conexion.UsuarioLogin.loginUsuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    
    private Stage stage;    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        
    } 
    
    @FXML
    private void identificarABase(ActionEvent event) throws IOException {
        
        if((loginUsuario(txtApodo1.getText(),txtContrasena1.getText()))==true){
            
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/fxml/base.fxml"));
            Parent parent = myLoader.load();           
            
            stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setResizable(false);
            stage.setTitle("FInfinity");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/imagenes/icons8_infinity_large_40px.png")));
            stage.setScene(scene);            
            stage.show();
            cerrar();            
        }else {
            lbError.setText("EL USUARIO O LA CONTRASEÑA SON INCORRECTOS");            
        }        
    }

    @FXML
    private void limpiarLabel(MouseEvent event) {
        
        lbError.setText("");
    }
    
    public void cerrar(){//Metodo cerrar stage
  
        stage = (Stage)btnAceptar1.getScene().getWindow();
        stage.close();
    }
}
