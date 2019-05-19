/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.jfoenix.controls.JFXButton;
import conexion.RecursoClase;
import conexion.objetos.Recurso;
import static controladores.IdentificarController.superNombre;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author ALF
 */
public class PanelCentralController implements Initializable {

    @FXML
    private HBox vistaRecursos;
    @FXML
    private Label lblApodo;
    @FXML
    private Label lblRecurso;
    @FXML
    private Label lblDescripcion;
    @FXML
    private JFXButton btnVer;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ObservableList<Recurso> lista = (ObservableList<Recurso>) Recurso.obtenerRecursos();
       cargar();
    }
    
    public void cargar(){
       
    }
}
