/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.jfoenix.controls.JFXButton;
import com.pepperonas.fxiconics.FxIconicsLabel;
import com.pepperonas.fxiconics.MaterialColor;
import com.pepperonas.fxiconics.gmd.FxFontGoogleMaterial;
import conexion.EtiquetaUsuario;
import conexion.FicherosBinarios;
import conexion.RecursoClase;
import static conexion.RecursoClase.obtenerRecursosPorId;
import conexion.objetos.Etiqueta;
import conexion.objetos.Recurso;
import static controladores.IdentificarController.superNombre;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static conexion.EtiquetaUsuario.obtenerEtiquetasGenerales;
import conexion.objetos.RecursoCliente;
import javafx.application.Platform;
import javafx.scene.control.ContentDisplay;

/**
 * FXML Controller class
 *
 * @author Alfredo
 */
public class BaseController implements Initializable {

    @FXML
    private Accordion acordeonIzq;
    @FXML
    private BorderPane border;
    @FXML
    private Button btnAnadirRecurso;
    @FXML
    private Label lblApodoUsuario;
    @FXML
    private FlowPane flow;
    @FXML
    private Accordion acordeonDer;
    @FXML
    private StackPane baseStack;
    @FXML
    private JFXButton btnAnadirRecEnBase;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TitledPane POPULARES;
    @FXML
    private TitledPane MASVALORADAS;
    @FXML
    private TitledPane NOVEDADES;
    @FXML
    private TitledPane GENERAL;
    @FXML
    private TitledPane SINETIQUETAR;
    @FXML
    private TitledPane PUBLICAS;
    @FXML
    private TitledPane PRIVADAS;

    //------------------------------
    ListView<String> lista = new ListView<>();
    TextField textComentar = new TextField();
    Parent parent;
    private Stage stage;
    //Vista HBox recursosDinamicos
    private HBox vistaRecursos;
    private Label lblApodo;
    private Label lblRecurso;
    private Label lblDescripcion;
    private JFXButton btnVer;
    Image cerrarTag = new Image("/imagenes/delete3.png");
    //-----------------------------
    @FXML
    private JFXButton btnSalir;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //System.out.println(EtiquetaUsuario.obtenerRecursoSinEtiquetar());
        lblApodoUsuario.setText(superNombre);
        //recibeParametros(text);
        acordeonIzq.expandedPaneProperty().addListener(
                (ObservableValue<? extends TitledPane> ov, TitledPane old_val,
                        TitledPane new_val) -> {
                    if (new_val != null) {
                        cargarListasTags(new_val);
                    }
                }
        );
        acordeonDer.expandedPaneProperty().addListener(
                (ObservableValue<? extends TitledPane> ov, TitledPane old_val,
                        TitledPane new_val) -> {
                    if (new_val != null) {
                        cargarListasTags(new_val);
                    }
                }
        );
                  

    }

    //Métodos nuestros.
    public void cargarListasTags(TitledPane tp) {

        int i = 3;    //IDUSUARIO recuperarlo-----------------------------------------*********
        VBox contenido = null;
        //Switch para cargar el TitledPane apropiado.
        System.out.println(tp.getText());
        switch (tp.getText()) {
            case "POPULARES":   //TODO
                //contenido=configurarListaEtiquetas(EtiquetaUsuario.obtenerEtiquetasPopulares());
                break;
            case "MASVALORADAS":    //TODO
                //contenido=configurarListaEtiquetas(EtiquetaUsuario.obtenerEtiquetasValoradas());
                break;
            case "NOVEDADES":
                contenido = configurarListaEtiquetas(EtiquetaUsuario.obtenerEtiquetasNovedades());
                break;
            case "GENERAL":
                contenido = configurarListaEtiquetas(EtiquetaUsuario.obtenerEtiquetasGenerales());
                break;
            case "SIN ETIQUETAR":
                contenido = configurarListaEtiquetas(EtiquetaUsuario.obtenerRecursoSinEtiquetar());
                break;
            case "PUBLICAS":
                contenido = configurarListaEtiquetas(EtiquetaUsuario.obtenerEtiquetasUsuarioPublicas(i));
                break;
            case "PRIVADAS":
                contenido = configurarListaEtiquetas(EtiquetaUsuario.obtenerEtiquetasUsuarioPrivadas(i));
                break;

        }
        tp.setContent(contenido);
        //System.out.println(lista);

    }

    private VBox configurarListaEtiquetas(List<?> lista) {
        Label l = null;
        VBox contenido = new VBox();
        contenido.setStyle("-fx-background-color:#EAB0B2;");

        for (Object o : lista) {
            if (o instanceof Etiqueta) {
                Etiqueta e = (Etiqueta) o;
                l = (new Label("#" + e.getEtiquetaPK().getNombre()));
                l.setOnMouseClicked((new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {

                            System.out.println("Hola");
                        }
                    }
                }));
            }
            if (o instanceof String) { //PUBLICAS / PRIVADAS
                String s = (String) o;
                tagButton(contenido, ("#" + s));
                //l = new Label("#" + s);
                
//                l.setOnMouseClicked((new EventHandler<MouseEvent>() {
//                    
//                    @Override
//                    public void handle(MouseEvent event) {
//                        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
//
//                            System.out.println("Hola");
//                        }
//                    }
//                }));
            }             
            if (o instanceof RecursoCliente) {  //SIN ETIQUETAR
                RecursoCliente rc = (RecursoCliente) o;
                l = new Label("#" + rc.getNombre().substring(0, rc.getNombre().lastIndexOf(".")));
                l.setOnMouseClicked((new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                            //addGrid(rc.getIdRecurso());
                        }
                    }
                }));
            }
            if(! (o instanceof String)){//Solo añade cuando no es una instancia de String,el metodo tagButton añade los botones al box
                
            contenido.getChildren().add(l);}
        }
        return contenido;
    }

    public void recursosDinamicos() {

        HBox hboxMultiple;
        AnchorPane caja;

        List<Recurso> listaRecursos = RecursoClase.obtenerRecursos();
        System.out.println(listaRecursos);
        flow.getChildren().removeAll();
        flow.getChildren().clear();

        for (Recurso r : listaRecursos) {
            caja = new AnchorPane();
            hboxMultiple = new HBox();
            caja.setPrefSize(801, 128);
            caja.setStyle("-fx-background-color: #FF6B6B;");

            hboxMultiple.setPrefSize(801, 128);
            hboxMultiple.setSpacing(5);
            hboxMultiple.setStyle("-fx-background-color: #AA4747;");
            hboxMultiple.setPadding(new Insets(30, 0, 0, 0));
            AnchorPane.setTopAnchor(hboxMultiple, 10.0);
            AnchorPane.setLeftAnchor(hboxMultiple, 10.0);
            AnchorPane.setRightAnchor(hboxMultiple, 10.0);
            AnchorPane.setBottomAnchor(hboxMultiple, 10.0);

            FxIconicsLabel labIcon = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontGoogleMaterial.Icons.gmd_folder_special)
                    .size(30)
                    //.text("ARCHIVO")
                    .color(MaterialColor.WHITE)
                    .build();
            labIcon.setPadding(new Insets(-10, 0, 0, 10));//public Insets(double top,double right,double bottom,double left)
            hboxMultiple.getChildren().add(labIcon);

            JFXButton btVer = new JFXButton();
            btVer.setText("VER");
            btVer.setPrefSize(104, 30);
            //btVer.setPadding(new Insets(0, 0, 0, 0));
            btVer.setStyle("-fx-text-fill: #000000;"
                    + "-fx-background-color: #ffffff;"
                    + "-fx-font-size: 15px;\n"
                    + "-fx-font-family: \"System\"");
            btVer.setOnAction((ActionEvent e) -> {
                addGridRecurso(r.getIdRecurso());
            });

            hboxMultiple.getChildren().add(btVer);
            Label lbApodo = new Label(superNombre);
            lbApodo.setPrefSize(90, 35);
            lbApodo.setPadding(new Insets(0, 0, 0, 20));
            lbApodo.setStyle("-fx-text-fill: #ffffff;"
                    + "-fx-font-size: 15px;\n"
                    + "-fx-font-family: \"System\"");
            hboxMultiple.getChildren().add(lbApodo);

            Label lbRecurso = new Label(r.getNombre());
            lbRecurso.setPrefSize(140, 35);
            lbRecurso.setPadding(new Insets(0, 0, 0, 20));
            lbRecurso.setStyle("-fx-text-fill: #ffffff;"
                    + "-fx-font-size: 15px;\n"
                    + "-fx-font-family: \"System\"");
            hboxMultiple.getChildren().add(lbRecurso);

            Label lblDescripcion = new Label(r.getDescripcion());
            lblDescripcion.setPrefSize(340, 58);
            lblDescripcion.setPadding(new Insets(40, 10, 10, 20));
            lblDescripcion.setStyle("-fx-text-fill: #ffffff;"
                    + "-fx-font-size: 15px;\n"
                    + "-fx-font-family: \"System\"");
            lblDescripcion.setAlignment(Pos.CENTER);
            hboxMultiple.getChildren().add(lblDescripcion);
            caja.getChildren().add(hboxMultiple);

            flow.getChildren().add(caja);
        }
        //border.setCenter(flow);
    }

    public void addGridRecurso(int idRecurso) {

        GridPane gridRecurso = new GridPane();
        gridRecurso.setStyle("-fx-background-color: #AFB1CD;");
        Recurso recurso = obtenerRecursosPorId(idRecurso);

        flow.getChildren().removeAll();
        flow.getChildren().clear();

        gridRecurso.setPrefSize(818.0, 691.0);
        gridRecurso.setPadding(new Insets(20));
        gridRecurso.setHgap(25);
        gridRecurso.setVgap(25);

        Text textRecurso = new Text("Nombre del archivo:");
        textRecurso.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        gridRecurso.add(textRecurso, 1, 0);

        Text nombreRecurso = new Text(recurso.getNombre());
        nombreRecurso.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        gridRecurso.add(nombreRecurso, 2, 0);

        Text descripcion = new Text("Descripción:");
        descripcion.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        gridRecurso.add(descripcion, 1, 1, 2, 1);

        ImageView imageHouse = new ImageView(new Image(BaseController.class.getResourceAsStream("/imagenes/usuario-recurso.png")));
        gridRecurso.add(imageHouse, 0, 0, 1, 2);

        Label textDescripcion = new Label(recurso.getDescripcion());
        textDescripcion.setMaxSize(400.0, 300.0);
        textDescripcion.setWrapText(true);
        textDescripcion.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        gridRecurso.add(textDescripcion, 1, 2, 2, 1);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(3));
        vbox.setSpacing(2);

        Text title = new Text("TAGS");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);

        List<Etiqueta> listaEtiquetas = obtenerEtiquetasGenerales();
        for(Etiqueta e:listaEtiquetas){
            
            vbox.getChildren().add(new Hyperlink("#" + e.getEtiquetaPK().getNombre()));
        }
        gridRecurso.add(vbox, 4, 0, 2, 6);     

        
        Text tag = new Text("Añadir Tag");
        tag.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        gridRecurso.add(tag, 3,0);
        
        TextField textField = new TextField();
        textField.setPromptText("Tag nombre-ENTER ");
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                 vbox.getChildren().add(new Hyperlink("#" + textField.getText()));
                //tagButton(tagsPane, textField.getText());
                textField.clear();
            }
        });
        gridRecurso.add(textField,3, 1);

        Button descargar = new Button("DESCARGAR");
        descargar.setPrefSize(100, 20);
        descargar.setStyle("-fx-background-color: #FAE83C;");
        descargar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                FileChooser fileChooser = new FileChooser();
                //Show save file dialog
                File file = fileChooser.showSaveDialog(stage);
                FicherosBinarios.descargar(recurso.getIdRecurso(), file);
            }
        });
        gridRecurso.add(descargar, 0, 3);

        Button volver = new Button("VOLVER");
        volver.setPrefSize(100, 20);
        volver.setStyle("-fx-background-color: #FAE83C;");
        volver.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //recurso();
                gridNuevo();
            }
        });
        gridRecurso.add(volver, 1, 3);

        Button buttonSave = new Button("COMENTAR");
        buttonSave.setPrefSize(100, 20);
        buttonSave.setStyle("-fx-background-color: #FAE83C;");
        gridRecurso.add(buttonSave, 0, 4);

        textComentar.setPrefSize(200, 20);
        textComentar.setStyle("-fx-background-color: #FAE83C;");
        gridRecurso.add(textComentar, 1, 4, 2, 1);

        ObservableList<String> data = FXCollections.observableArrayList("chocolate", "salmon", "gold");

        lista.setItems(data);
        lista.setPrefSize(500, 200);

        buttonSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                comentario();
            }
        });

        HBox hb = new HBox();
        hb.setPadding(new Insets(10, 10, 10, 10));
        hb.setSpacing(10);
        hb.getChildren().addAll(lista);
        gridRecurso.add(hb, 0, 5, 3, 1);
        flow.getChildren().add(gridRecurso);

    }

    private void comentario() {

        Date objDate = new Date(); // Sistema actual La fecha y la hora se asignan a objDate 

        System.out.println(objDate);
        String strDateFormat = "hh:mm:ss a dd-MMM-yyyy"; // El formato de fecha está especificado  
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); // La cadena de formato de fecha se pasa como un argumento al objeto 
        String fecha = (objSDF.format(objDate)); // El formato de fecha se aplica a la fecha actual

        if (!"".equals(textComentar.getText())) {
            String texto;
            texto = (fecha + "  Usuario: " + superNombre + "\n" + textComentar.getText());
            System.out.println(texto);
            lista.getItems().add(texto + "\n");
            textComentar.clear();
        }
    }

    @FXML
    private void buscarRecursosHBox(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {

        } else {
        }
    }

    @FXML
    private void anadirRecurso(ActionEvent event) {

        //recurso();
        gridNuevo();
    }

    public void cargarListaRecursos() throws IOException {

        AnchorPane hboxMultiple;
        Label a = new Label();
        Label b = new Label();
        Label c = new Label();
        List<Recurso> listaRecursos = RecursoClase.obtenerRecursos();
        System.out.println(listaRecursos);
        flow.getChildren().removeAll();
        flow.getChildren().clear();

        for (Recurso r : listaRecursos) {

            a.setText(r.getNombre());
            b.setText(r.getDescripcion());
            c.setText(Integer.toString(r.getIdUsuario()));
//            btnVer.setText("VER");
//            btnVer.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent e) {
//                    addGridRecurso(r.getIdRecurso());
//                    System.out.println("entro");
//                }
//            });
            hboxMultiple = new AnchorPane();
            //hboxMultiple = FXMLLoader.load(getClass().getResource("/fxml/BoxRecurso.fxml")); 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PanelCentral.fxml"));
            hboxMultiple = loader.load();
            PanelCentralController controller = (PanelCentralController) loader.getController();
            controller.setLabelText(a.getText(), b.getText(), c.getText());
            flow.getChildren().add(hboxMultiple);
        }

    }

    public void ojo(Parent papa) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("/fxml/AnadirRecurso.fxml"));
        flow.getChildren().clear();
        flow.getChildren().add(papa);
        border.setCenter(papa);
    }

    @FXML
    private void AnadirRecEnBase(ActionEvent event) throws IOException {

        AnchorPane anchor = FXMLLoader.load(getClass().getResource("/fxml/AnadirRecurso.fxml"));
        flow.getChildren().clear();
        flow.getChildren().add(anchor);
        //border.setCenter(anchor);      

    }

    @FXML
    private void salir(ActionEvent event) {
        Platform.exit();
    }
    
    public void gridNuevo(){
        
        GridPane gridPane = new GridPane();
        List<Recurso> listaRecursos = RecursoClase.obtenerRecursos();
        System.out.println(listaRecursos);
        flow.getChildren().removeAll();
        flow.getChildren().clear();

        for (Recurso r : listaRecursos) {
            gridPane = new GridPane();
            gridPane.setPrefSize(801, 87);
            FxIconicsLabel labIcon = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontGoogleMaterial.Icons.gmd_folder_special)
                       .size(30)
                       //.text("ARCHIVO")
                       .color(MaterialColor.WHITE)
                       .build();
            
            JFXButton btVer = new JFXButton();
            btVer.setText("VER");
            btVer.setPrefSize(104, 30);
            //btVer.setPadding(new Insets(0, 0, 0, 0));
            btVer.setStyle("-fx-text-fill: #000000;"
                    + "-fx-background-color: #ffffff;"
                    + "-fx-font-size: 15px;\n"
                    + "-fx-font-family: \"System\"");
            btVer.setOnAction((ActionEvent e) -> {
                addGridRecurso(r.getIdRecurso());
            }); 

            Label lbRecurso = new Label(r.getNombre());
            lbRecurso.setPrefSize(140, 35);
            lbRecurso.setStyle("-fx-text-fill: #ffffff;"
                                + "-fx-font-size: 15px;\n"
                                + "-fx-font-family: \"System\"");
            
            Label lbApodo = new Label(superNombre);
            lbApodo.setPrefSize(90, 35);            
            lbApodo.setStyle("-fx-text-fill: #ffffff;"
                    + "-fx-font-size: 15px;\n"
                    + "-fx-font-family: \"System\"");
            
            Label lblDescripcion = new Label(r.getDescripcion());
            lblDescripcion.setPrefSize(340, 58);
            lblDescripcion.setStyle("-fx-text-fill: #ffffff;"
                    + "-fx-font-size: 15px;\n"
                    + "-fx-font-family: \"System\"");
            lblDescripcion.wrapTextProperty();
            //lblDescripcion.setAlignment(Pos.CENTER);
            
            Label lbUsuario = new Label("Usuario: ");
            lbUsuario.setPrefSize(90, 35);
            lbUsuario.setStyle("-fx-text-fill: #ffffff;"
                    + "-fx-font-size: 14px;\n"
                    + "-fx-font-family: \"System\"");
            
            Label lbNombreRecurso = new Label("Recurso ");
            lbNombreRecurso.setPrefSize(90, 35); 
            
            Label lbDescripcion = new Label("Descripcion: ");
            lbDescripcion.setPrefSize(90, 35); 

            gridPane.add(labIcon, 0, 0, 1, 1);
            gridPane.add(lbUsuario, 1, 0, 1, 1);
            gridPane.add(lbNombreRecurso, 2, 0, 1, 1);
            gridPane.add(lbDescripcion, 3, 0, 1, 1);
            gridPane.add(btVer, 0, 1, 1, 1);
            gridPane.add(lbApodo, 1, 1, 1, 1);
            gridPane.add(lbRecurso, 2, 1, 1, 1);
            gridPane.add(lblDescripcion, 3, 1, 1, 1);
            flow.getChildren().add(gridPane);
        }
    }
    
    public void tagButton(VBox box,String tag){
        ImageView iconCerrar = new ImageView(cerrarTag);
        Button btTag = new Button(tag,iconCerrar);
        btTag.setPrefHeight(20);
        btTag.setContentDisplay(ContentDisplay.RIGHT);

        btTag.setOnAction(event -> box.getChildren().remove(btTag));
        box.getChildren().add(btTag);
    }
}
