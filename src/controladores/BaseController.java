/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import com.pepperonas.fxiconics.FxIconicsLabel;
import com.pepperonas.fxiconics.MaterialColor;
import com.pepperonas.fxiconics.gmd.FxFontGoogleMaterial;
import conexion.EtiquetaUsuario;
import static conexion.EtiquetaUsuario.obtenerEtiquetasUsuarios;
import conexion.FicherosBinarios;
import conexion.RecursoClase;
import static conexion.RecursoClase.obtenerRecursosPorId;
import conexion.objetos.Etiqueta;
import conexion.objetos.Recurso;
import static controladores.IdentificarController.superNombre;
import java.io.File;
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
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

/**
 * FXML Controller class
 *
 * @author Alfredo
 */
public class BaseController implements Initializable {

    
    @FXML 
    private BorderPane border;
    @FXML 
    private StackPane baseStack;    
    @FXML
    private Accordion acordeonIzq;
    @FXML
    private Accordion acordeonDer;
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
    private Label lblApodoUsuario;
    @FXML
    private Button vista;   
    @FXML
    private FlowPane flow;
    @FXML
    private Button boton;
    
    ListView <String>lista = new ListView<>();
    TextField textComentar = new TextField();
    private Stage stage;
    final FileChooser fileChooser = new FileChooser();
    
    
        
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        lblApodoUsuario.setText(superNombre);
        //recibeParametros(text);
        acordeonIzq.expandedPaneProperty().addListener(
            (ObservableValue<? extends TitledPane> ov, TitledPane old_val, 
                TitledPane new_val) -> {
                    if (new_val != null) {
                        prueba();   
                    }
                }        
        );
        recurso();
    }
    
    //Métodos nuestros.
    private void prueba() {
        VBox contenido = new VBox();
        contenido.setStyle("-fx-background-color:#EAB0B2;");
        Label l;

        List<Etiqueta> lista = EtiquetaUsuario.obtenerEtiquetasUsuarios();
        System.out.println(lista);

        for (Etiqueta e : lista) {
            l = (new Label("#" + e.getEtiquetaPK().getNombre()));
            l.setOnMouseClicked((new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                        //TODO 
                        System.out.println("Hola");
                    }
                }
            }));

            contenido.getChildren().add(l);
        }
        POPULARES.setContent(contenido);
    }

    @FXML
    private void vista(ActionEvent event) {
        //recurso();
    }
        
        private void recurso(){
            
        
        HBox hboxMultiple;
        List<Recurso> listaRecursos = RecursoClase.obtenerRecursos();
        System.out.println(listaRecursos);
        flow.getChildren().removeAll();
        flow.getChildren().clear();

        for (Recurso r : listaRecursos) {
            hboxMultiple = new HBox();
            hboxMultiple.setPrefSize(801, 128);
            hboxMultiple.setSpacing(15);
            hboxMultiple.setPadding(new Insets(15, 20, 10, 10));
            FxIconicsLabel labelTextDefault = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontGoogleMaterial.Icons.gmd_folder_special)
                    .size(24)
                    .text("ARCHIVO")
                    .color(MaterialColor.ORANGE_500)
                    .build();
            labelTextDefault.setPadding(new Insets(10, 0, 0, 10));//public Insets(double top,double right,double bottom,double left)

            hboxMultiple.getChildren().add(labelTextDefault);
            Button btnVer = new Button("VER");
            btnVer.setPrefSize(110, 42);

            btnVer.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    addGrid(r.getIdRecurso());
                }
            });

            hboxMultiple.getChildren().add(btnVer);
            Label lbApodo = new Label(superNombre);
            lbApodo.setPrefSize(136, 35);
            hboxMultiple.getChildren().add(lbApodo);

            Label lbRecurso = new Label(r.getNombre());
            lbRecurso.setPrefSize(136, 35);
            hboxMultiple.getChildren().add(lbRecurso);

            Label lblDescripcion = new Label(r.getDescripcion());
            lblDescripcion.setPrefSize(351, 58);
            lblDescripcion.setAlignment(Pos.CENTER);
            hboxMultiple.getChildren().add(lblDescripcion);

            flow.getChildren().add(hboxMultiple);
        }
    }

    private void addGrid(int idRecurso) {
        
        GridPane gridRecurso = new GridPane();
        Recurso recurso = obtenerRecursosPorId(idRecurso);
        
        flow.getChildren().removeAll();
        flow.getChildren().clear();
        
        gridRecurso.setPrefSize(818.0, 691.0);
        gridRecurso.setPadding(new Insets(20));
        gridRecurso.setHgap(25);
        gridRecurso.setVgap(25);

        Text textRecurso = new Text("Nombre del archivo:");
        textRecurso.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gridRecurso.add(textRecurso, 1, 0);

        Text nombreRecurso = new Text(recurso.getNombre());
        nombreRecurso.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gridRecurso.add(nombreRecurso, 2, 0);

        Text descripcion = new Text(recurso.getDescripcion());
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
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);              

        Text title = new Text("TAGS");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);
        
        List<Etiqueta> listaEtiquetas = obtenerEtiquetasUsuarios();
        Hyperlink options[] = new Hyperlink[]{
            new Hyperlink("#" + "playa"),
            new Hyperlink("montaña"),
            new Hyperlink("peliculas"),
            new Hyperlink("losnepesdeEmilio"),
            new Hyperlink("#" + "playa"),
            new Hyperlink("montaña"),
            new Hyperlink("montaña"),
            new Hyperlink("peliculas"),
            new Hyperlink("losnepesdeEmilio")};

        for (int i = 0; i < 9; i++) {
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(options[i]);
        }
        
        for(Etiqueta e : listaEtiquetas){
            vbox.getChildren().add(new Hyperlink("#" + e.getEtiquetaPK().getNombre()));
        }

        gridRecurso.add(vbox, 3, 0, 2, 4);

        Button descargar = new Button("DESCARGAR");
        descargar.setPrefSize(100, 20);
        descargar.setStyle("-fx-background-color: #FAE83C;");
        gridRecurso.add(descargar, 0, 3);

        Button volver = new Button("VOLVER");
        volver.setPrefSize(100, 20);
        volver.setStyle("-fx-background-color: #FAE83C;");
        volver.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                recurso();
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
        gridRecurso.add(hb, 0, 5, 3, 4);

        flow.getChildren().add(gridRecurso);
    }
    
    private void comentario(){
        
        Date objDate = new Date(); // Sistema actual La fecha y la hora se asignan a objDate 
 
        System.out.println(objDate); 
        String strDateFormat = "hh:mm:ss a dd-MMM-yyyy"; // El formato de fecha está especificado  
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); // La cadena de formato de fecha se pasa como un argumento al objeto 
        String fecha = (objSDF.format(objDate)); // El formato de fecha se aplica a la fecha actual
        
        if (!"".equals(textComentar.getText())){
            String texto ;
            texto = (fecha +"  Usuario: "+superNombre+"\n"+ textComentar.getText());
            System.out.println(texto);
            lista.getItems().add(texto+"\n");
            textComentar.clear();
        }   
    }

    @FXML
    private void buscarRecursosHBox(KeyEvent event) {
        
        if(event.getCode() == KeyCode.ENTER) { 
            
        }else{          
        }
    }

    @FXML
    private void seleccionarArchivos(ActionEvent event) {
        
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Recurso r=new Recurso();
            
            FicherosBinarios.subir(file, r);
        }
        
    }
}