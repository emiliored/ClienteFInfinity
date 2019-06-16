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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.PopupWindow;

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
    private static final String PASSWORD_PATTERN = "(^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{6,14}$)";  
    private boolean correcto1 = false;
    private boolean correcto2 = false;
    private static final String SQUARE_BUBBLE
            = "M24 1h-24v16.981h4v5.019l7-5.019h13z";
    private static final String ROUND_BUBBLE
            = "M12 1c-6.628 0-12 4.573-12 10.213 0 2.39.932 4.591 2.427 6.164l-2.427 5.623 7.563-2.26c9.495 2.598 16.437-3.251 16.437-9.527 0-5.64-5.372-10.213-12-10.213z";


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pattern = Pattern.compile(PASSWORD_PATTERN);
        password1();
        password2();
        toolTip();
        txtNombre2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if ((newValue.equals(" "))&&(!newValue.matches("([0-9A-Za-z]){1}([0-9A-Za-z\\s])*"))) {
                    txtNombre2.setText(oldValue);
                }
            }
        });
        txtApellidos2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if ((newValue.equals(" "))&&(!newValue.matches("([0-9A-Za-z]){1}([0-9A-Za-z\\s])*"))) {
                    txtApellidos2.setText(oldValue);
                }
            }
        });
        txtApodo2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if ((newValue.equals(" "))&&(!newValue.matches("([0-9A-Za-z]){0,25}"))) {
                    txtApodo2.setText(oldValue);
                }
            }
        });
    }

    @FXML
    private void RegistrarABase(ActionEvent event) {

        if ((!pass1.getText().equals(""))&&(!pass2.getText().equals(""))&&(correcto1 && correcto2) && 
              (!txtNombre2.getText().equals(""))&& (!txtApellidos2.getText().equals(""))&& (!txtApodo2.getText().equals(""))&& 
                (registroUsuario(txtNombre2.getText(), txtApellidos2.getText(), txtApodo2.getText(), pass2.getText())) == true) {
            lbErrorRegistro.setText("REGISTRO CORRECTO, POR FAVOR ACCEDA A LOGIN");
        } else {
            lbErrorRegistro.setText("ERROR EN EL REGISTRO, DATOS INCORRECTOS");
        }
    }

    private void password1() {

        pass1.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) -> {
            //Controlamos cuando pierde el foco
            if (!arg2) {
                if (validate(pass1.getText())) {
                    lb1.setText("Contraseña correcta");
                    lb1.setTextFill(Color.web("#229954"));
                    correcto1 = true;
                    System.out.println(pass1.getText());
                } else {
                    lb1.setText("Contraseña incorrecta");
                    lb1.setTextFill(Color.web("#E74C3C"));
                    correcto1 = false;
                }
            }
        });
    }

    private void password2() {
        pass2.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) -> {
            //Controlamos cuando pierde el foco
            if (!arg2) {
                if (validate(pass2.getText()) && pass1.getText().equals(pass2.getText())) {
                    lb2.setText("Contraseña correcta");
                    lb2.setTextFill(Color.web("#229954"));
                    correcto2 = true;
                    System.out.println(pass2.getText());
                } else {
                    lb2.setText("Contraseña incorrecta o no coinciden");
                    lb2.setTextFill(Color.web("#E74C3C"));
                    correcto2 = false;
                }
            }
        });
    }

    public boolean validate(final String password) {

        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private Tooltip formatoToolTip(Tooltip tooltip) {
           
        tooltip.setStyle("-fx-font: normal bold 14 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"); 
        return tooltip;      
    }

    private void toolTip() {//Metodo para cargar los Tooltip.

        txtNombre2.setTooltip(formatoToolTip(new Tooltip("INTRODUZCA SU NOMBRE")));
        txtApellidos2.setTooltip(formatoToolTip(new Tooltip("INTRODUZCA SUS APELLIDOS")));
        txtApodo2.setTooltip(formatoToolTip(new Tooltip("INTRODUZCA SU APODO,\nSOLO LETRAS Y NUMEROS")));
        btnAceptar2.setTooltip(formatoToolTip(new Tooltip("PULSE PARA REALIZAR EL REGISTRO")));
        pass1.setTooltip(formatoToolTip(new Tooltip("INTRODUZCA LA CONTRASEÑA,\nSOLO NUMEROS, MAYUSCULAS Y MINUSCULAS\n,MINIMO/MAXIMO 6/14 CARACTERES")));
        pass2.setTooltip(formatoToolTip(new Tooltip("REPITA LA CONTRASEÑA,\nSOLO NUMEROS, MAYUSCULAS Y MINUSCULAS\n,MINIMO/MAXIMO 6/14 CARACTERES")));
    }
}
