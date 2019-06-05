/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXToggleButton;
import conexion.FicherosBinarios;
import conexion.RecursoClase;
import conexion.objetos.Recurso;
import static controladores.BaseController.usuarioInicio;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private AnchorPane bt;
    @FXML
    private JFXListView<Recurso> listaArchivos;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private Label lbEliminar;
    @FXML
    private Label lbEminado;
    
    private JFXToggleButton tgVisibilidadEtiqueta;
    private Label lbVisibilidadEtiqueta;
    final FileChooser fileChooser = new FileChooser();
    private Stage stage;
    private Parent parent;
    private File file;
    private String nombreRe;
    private int numIdRe;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtAreaDescripcion.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if ((newValue.equals(" "))&&(!newValue.matches("([0-9A-Za-z]){1}([0-9A-Za-z\\s\\.\\,\\_\\-\\:])*"))) {
                    txtAreaDescripcion.setText(oldValue);
                }
            }
        });
        cargaListaRecurso();
        toolTip();
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
            r.setDescripcion(txtAreaDescripcion.getText().replace(' ', '+'));
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
    
    @FXML
    private void eliminar(ActionEvent event) {
    }
    
    public void cargaListaRecurso() {
        int i = 0;
        
        List<Recurso> listaRe = RecursoClase.obtenerRecursos();
        listaArchivos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);        
        for ( Recurso r : listaRe) {
            nombreRe = r.getNombre();
            numIdRe = r.getIdRecurso();
            listaArchivos.getItems().add(i, r);
        }
        listaArchivos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Recurso>() {
            @Override
            public void changed(ObservableValue<? extends Recurso> observable, Recurso oldValue, Recurso newValue) {
//                Recurso re = null;
//                lbEliminar.setText(re.getNombre());
            }
        });
    }
    
    private Tooltip formatoToolTip(Tooltip tooltip) {
           
        tooltip.setStyle("-fx-font: normal bold 14 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"); 
        return tooltip;      
    }

    private void toolTip() {//Metodo para cargar los Tooltip.

        btnSeleccionar.setTooltip(formatoToolTip(new Tooltip("PULSE PARA SELECCIONAR ARCHIVO")));
        tgVisibilidadRecurso.setTooltip(formatoToolTip(new Tooltip("PULSE PARA SELECCIONAR PUBLICO/PRIVADO")));
        btnSubir.setTooltip(formatoToolTip(new Tooltip("PULSE PARA SUBIR ARCHIVO")));
        txtAreaDescripcion.setTooltip(formatoToolTip(new Tooltip("ESCRIBA PARA AÑADIR DESCRIPCIÓN")));
        listaArchivos.setTooltip(formatoToolTip(new Tooltip("SELECCCIONE EL ARCHIVO\nQUE DESEA ELIMINAR")));
        btnEliminar.setTooltip(formatoToolTip(new Tooltip("DESPUES DE SELECCIONAR EL ARCHIVO\nPULSE PARA ELIMINARLO")));
    }
}
