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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
    private Label lbErrorRegistro;
    @FXML
    private JFXButton btnAceptar2;
    @FXML
    private PasswordField pass1;
    @FXML
    private Label lb1;
    @FXML
    private PasswordField pass2;
    @FXML
    private Label lb2;

    private Pattern pattern;
    private Matcher matcher;
    private static final String PASSWORD_PATTERN = "(^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{6,14}$)"; //"((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pattern = Pattern.compile(PASSWORD_PATTERN);
        password1();
        password2();
    }

    @FXML
    private void RegistrarABase(ActionEvent event) {

        if ((registroUsuario(txtNombre2.getText(), txtApellidos2.getText(), txtApodo2.getText(), pass2.getText())) == true) {
            lbErrorRegistro.setText("REGISTRO CORRECTO, POR FAVOR ACCEDA A LOGIN");
        } else {
            lbErrorRegistro.setText("ERROR EN EL REGISTRO, EL USUARIO EXISTE");
        }
    }
    
    private void password1(){
        
        pass1.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) -> {
            //Controlamos cuando dimX pierde el foco
            if (!arg2) {
                if (validate(pass1.getText())) {
                    lb1.setText("Contraseña correcta");
                    lb1.setTextFill(Color.web("#229954"));
                    System.out.println(pass1.getText());
                } else {
                    lb1.setText("Contraseña incorrecta");
                    lb1.setTextFill(Color.web("#E74C3C"));
                    
                }
            }
        });
    }
    
    private void password2() {
        pass2.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) -> {
            //Controlamos cuando dimX pierde el foco
            if (!arg2) {
                if (validate(pass2.getText()) && pass1.getText().equals(pass2.getText())) {
                    lb2.setText("Contraseña correcta");
                    lb2.setTextFill(Color.web("#229954"));
                    System.out.println(pass2.getText());
                } else {
                    lb2.setText("Contraseña incorrecta o no coinciden");
                    lb2.setTextFill(Color.web("#E74C3C"));
                }
            }
        });
    }

    public boolean validate(final String password) {

        matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
