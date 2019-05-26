/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import conexion.FicherosBinarios;
import conexion.objetos.Recurso;
//import static controladores.BaseController.baseStack;
import static controladores.IdentificarController.usuarioActual;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class AnadirRecursoController implements Initializable {

    @FXML
    private JFXButton btnSeleccionar;
    @FXML
    private JFXToggleButton tgVisibilidad;
    @FXML
    private JFXButton btnSubir;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private TextArea txtAreaDescripcion;
    @FXML
    private Label lbVisibilidad;
    @FXML
    private Label lbSubido;
    @FXML
    private AnchorPane vistaSubirRecurso;
    @FXML
    private JFXButton btnEtiquetar;
    @FXML
    private FlowPane flowEtiquetas;
    @FXML
    private JFXTextField txtEtiquetar;

    final FileChooser fileChooser = new FileChooser();
    private Stage stage;
    private Parent parent;
    private File file;
    private Recurso r;
    private FlowPane flow;    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Resize para componetes, pero son demasiados...pppffffffff
//        vistaSubirRecurso.heightProperty().addListener(new ChangeListener(){
//            @Override
//            public void changed (ObservableValue arg0, Object arg1, Object arg2){
//                double height =(double) arg2;
//                btnSeleccionar.setPrefHeight(height/2);
//                tgVisibilidad.setPrefHeight(height/2);
//                btnSubir.setPrefHeight(height/2);
//                btnCancelar.setPrefHeight(height/5);
//                txtAreaDescripcion.setPrefHeight(height/2);
//                lbVisibilidad.setPrefHeight(height/2);
//                lbSubido.setPrefHeight(height/2);
//                btnEtiquetar.setPrefHeight(height/2);
//                flowEtiquetas.setPrefHeight(height/2);
//                txtEtiquetar.setPrefHeight(height/2);
//            }
//
////            @Override
////            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
////                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
////            }
//        });
//        vistaSubirRecurso.widthProperty().addListener(new ChangeListener(){
//            @Override
//            public void changed (ObservableValue arg0, Object arg1, Object arg2){
//                double width =(double) arg2;
//                btnSeleccionar.setPrefHeight(width/2);
//                tgVisibilidad.setPrefHeight(width/2);
//                btnSubir.setPrefHeight(width/2);
//                btnCancelar.setPrefHeight(width/2);
//                txtAreaDescripcion.setPrefHeight(width/2);
//                lbVisibilidad.setPrefHeight(width/2);
//                lbSubido.setPrefHeight(width/2);
//                btnEtiquetar.setPrefHeight(width/2);
//                flowEtiquetas.setPrefHeight(width/2);
//                txtEtiquetar.setPrefHeight(width/2);
//            }
//        });
    }    

    @FXML
    private void seleccionar(ActionEvent event) {
        lbSubido.setText("");
        file = fileChooser.showOpenDialog(stage);
        
    }

    @FXML
    private void subir(ActionEvent event) {
        if(Objects.nonNull(file)){
            r=new Recurso();
            r.setIdUsuario(usuarioActual.getIdUsuario());
            r.setDescripcion(txtAreaDescripcion.getText().replace(' ', '+'));//Validar el textarea. Sólo letras, números, puntos, comas y espacios.
            r.setVisibilidad(tgVisibilidad.isSelected());
            if(FicherosBinarios.subir(file, r)){
               lbSubido.setText("Archivo subido correctamente.");
            }else{
               lbSubido.setText("Fallo durante la subida del archivo.");
            }
            //Limpiamos el fichero y los campos.
            file=null;
            tgVisibilidad.setSelected(false);
            txtAreaDescripcion.setText("");
        }
           
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
                
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(AnadirRecursoController.class.getResource("/fxml/base.fxml"));
//        StackPane stack = loader.load();
        
         parent = FXMLLoader.load(getClass().getResource("/fxml/PanelUsuario.fxml"));
        vistaSubirRecurso.getChildren().clear();
        vistaSubirRecurso.getChildren().add(parent);
        
    }

    @FXML
    private void visibilidad(ActionEvent event) {
        
        if(tgVisibilidad.isSelected())
            lbVisibilidad.setText("Pública");
        else
            lbVisibilidad.setText("Privada");
    }

    @FXML
    private void limpiaLabel(InputMethodEvent event) {
        
       // lbSubido.setText("");
    }

    @FXML
    private void etiquetar(ActionEvent event) {
    }
    
}
