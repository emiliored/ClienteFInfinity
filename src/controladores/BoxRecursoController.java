/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Alfredo
 */
public class BoxRecursoController  {

    @FXML
    private HBox vistaRecursos;
    @FXML
    private JFXButton btnVer;
    @FXML
    private Label lblApodo;
    @FXML
    private Label lblRecurso;
    @FXML
    private Label lblDescripcion;

    /**
     * Initializes the controller class.
     */
    
    public void initialize() {
        // TODO
    }    
    
    public void setLabelText(String text1, String text2, String text3){
        
        lblRecurso.setText(text1);
        lblDescripcion.setText(text2);
        lblApodo.setText(text3);
        
    }
}
