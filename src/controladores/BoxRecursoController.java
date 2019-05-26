/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
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
    
    public void setLabelText(String text1, String text2, String text3,int i){
        
        lblRecurso.setText(text1);
        lblDescripcion.setText(text2);
        lblApodo.setText(text3);
//        btnVer.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent e) {
//                   
//                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/base.fxml"));
//                        try {
//                            Flow parent = loader.load();
//                        } catch (IOException ex) {
//                            Logger.getLogger(BoxRecursoController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        BaseController con = (BaseController)loader.getController();
//                        con.addGrid(i);
//                        System.out.println(i);
//                        
//                }
//            });
        
    }

//    @FXML
//    private void verRecurso(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/base.fxml"));
//            Parent parent = loader.load();
//            BaseController con = (BaseController)loader.getController();
//            //con.addGrid();
//    }
//    
}
