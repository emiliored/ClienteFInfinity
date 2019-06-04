/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import conexion.FicherosBinarios;
import conexion.objetos.Recurso;
import static controladores.BaseController.usuarioInicio;
import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AnadirRecursoController implements Initializable {

    @FXML
    private JFXButton btnSeleccionar;
    @FXML
    private JFXToggleButton tgVisibilidadRecurso;
    @FXML
    private JFXButton btnSubir;
    @FXML
    private TextArea txtAreaDescripcion;
    @FXML
    private Label lbVisibilidadRecurso;
    @FXML
    private Label lbSubido;
    
    private JFXToggleButton tgVisibilidadEtiqueta;
    private Label lbVisibilidadEtiqueta;
    final FileChooser fileChooser = new FileChooser();
    private Stage stage;
    private Parent parent;
    private File file;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void seleccionar(ActionEvent event) {        
        lbSubido.setText("");
        file = fileChooser.showOpenDialog(stage);
    }

    @FXML
    private void subir(ActionEvent event) {
        
        if (Objects.nonNull(file)) {
            Recurso r = new Recurso();
            r.setIdUsuario(usuarioInicio.getIdUsuario());
            r.setDescripcion(txtAreaDescripcion.getText().replace(' ', '+'));//Validar el textarea. Sólo letras, números, puntos, comas y espacios.
            r.setVisibilidad(tgVisibilidadRecurso.isSelected());
            if (FicherosBinarios.subir(file, r)) {
                lbSubido.setText("Archivo subido correctamente.");
            } else {
                lbSubido.setText("Fallo durante la subida del archivo.");
            }
            //Limpiamos el fichero y los campos.
            file = null;
            tgVisibilidadRecurso.setSelected(true);
            txtAreaDescripcion.setText("");
        }

    }

    @FXML
    private void visibilidadRecurso(ActionEvent event) {

        if (tgVisibilidadRecurso.isSelected()) {
            lbVisibilidadRecurso.setText("Pública");
            lbVisibilidadRecurso.setStyle("-fx-text-fill: #3CE4A8");
        } else {
            lbVisibilidadRecurso.setText("Privada");
            lbVisibilidadRecurso.setStyle("-fx-text-fill: #8f000f");
        }
    }

    @FXML
    private void limpiaLabel(InputMethodEvent event) {

        // lbSubido.setText("");
    }   
}
