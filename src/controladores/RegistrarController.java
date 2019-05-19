/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.jfoenix.controls.JFXButton;
import static conexion.UsuarioLogin.registroUsuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Alfredo
 */
public class RegistrarController implements Initializable {

    @FXML
    private VBox container;
    @FXML
    private TextField txtNombre2;
    @FXML
    private TextField txtApellidos2;
    @FXML
    private TextField txtApodo2;
    @FXML
    private TextField txtContrasena2;
    @FXML
    private TextField txtConfirmarContarsena;
    @FXML
    private Label lbErrorRegistro;
    @FXML
    private JFXButton btnAceptar2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RegistrarABase(ActionEvent event) {
        
        if((registroUsuario(txtNombre2.getText(),txtApellidos2.getText(),txtApodo2.getText(),txtContrasena2.getText()))==true){
            
            lbErrorRegistro.setText("REGISTRO CORRECTO, POR FAVOR ACCEDA A LOGIN");
//            FXMLLoader loader = new FXMLLoader();
//            Parent parent = loader.load(getClass().getResource("/fxml/base.fxml"));
//            Stage stage = new Stage();
//            Scene scene = new Scene(parent);
//            stage.setScene(scene);
//            stage.show();
        }else{
            lbErrorRegistro.setText("ERROR EN EL REGISTRO");
        }
    }
    
}
