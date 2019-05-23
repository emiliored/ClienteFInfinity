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
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
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
    
    FlowPane flow;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ObservableList<Recurso> lista = (ObservableList<Recurso>) Recurso.obtenerRecursos();
       cargar();
    }
    
    public void cargar(){
       
         AnchorPane box;
        List<Recurso> listaRecursos = RecursoClase.obtenerRecursos();
        System.out.println(listaRecursos);
        flow.getChildren().removeAll();
        flow.getChildren().clear();

        for (Recurso r : listaRecursos) {
            try {
                box = new AnchorPane();
                box = FXMLLoader.load(getClass().getResource("/fxml/PanelCentral.fxml"));
                
                
                btnVer.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        //BaseController.addGrid(r.getIdRecurso());
                    }
                });
                
                box.getChildren().add(btnVer);
                
                lblApodo.setText(superNombre);
                box.getChildren().add(lblApodo);
                
                lblRecurso.setText(r.getNombre());
                box.getChildren().add(lblRecurso);
                
                lblDescripcion.setText(r.getDescripcion());
                box.getChildren().add(lblDescripcion);
                
                flow.getChildren().add(box);
            } catch (IOException ex) {
                Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
