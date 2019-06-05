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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    private PasswordField txtContrasena1;
    @FXML
    private JFXButton btnAceptar1;
    @FXML
    private Label lbError;
    @FXML
    private Label lbError1;

    private Stage stage;
//    private Pattern pattern;
//    private Matcher matcher;
//    private static final String PASSWORD_PATTERN = "(^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{6,30}$)";
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        toolTip();
        txtContrasena1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("([0-9A-Za-z]){0,30}")) {
                    txtContrasena1.setText(oldValue);
                }
            }
        });
//        txtContrasena1.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) -> {
//            //Controlamos cuando pierde el foco
//            if (!arg2 && txtContrasena1.getText()!="") {
//                if (validate(txtContrasena1.getText())) {
//                    lbError1.setText("Contraseña correcta");
//                    lbError1.setTextFill(Color.web("#229954"));
//                    System.out.println(txtContrasena1.getText());
//                } else {
//                    lbError1.setText("Contraseña incorrecta");
//                    lbError1.setTextFill(Color.web("#E74C3C"));
//                    
//                }
//            }
//        });
    }

    @FXML
    private void identificarABase(ActionEvent event) throws IOException {

        if ((!txtApodo1.getText().equals("")&&!txtContrasena1.getText().equals(""))&&(loginUsuario(txtApodo1.getText(), txtContrasena1.getText())) == true) {

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
        } else {
            lbError.setText("LOS DATOS INTRODUCIDOS SON INCORRECTOS");
        }
    }

    @FXML
    private void limpiarLabel(MouseEvent event) {

        lbError.setText("");
    }

//    public boolean validate(final String password) {
//
//        matcher = pattern.matcher(password);
//        return matcher.matches();
//    }

    public void cerrar() {//Metodo cerrar stage

        stage = (Stage) btnAceptar1.getScene().getWindow();
        stage.close();
    }
    
    private Tooltip formatoToolTip(Tooltip tooltip) {
           
        tooltip.setStyle("-fx-font: normal bold 14 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;");
        return tooltip;      
    }

    private void toolTip() {//Metodo para cargar los Tooltip.

        txtApodo1.setTooltip(formatoToolTip(new Tooltip("INTRODUZCA SU APODO")));
        btnAceptar1.setTooltip(formatoToolTip(new Tooltip("PULSE PARA ACCEDER")));
        txtContrasena1.setTooltip(formatoToolTip(new Tooltip("INTRODUZCA LA CONTRASEÑA")));
       
    }
}
