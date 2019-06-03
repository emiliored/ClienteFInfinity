/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import com.pepperonas.fxiconics.FxIconicsLabel;
import com.pepperonas.fxiconics.MaterialColor;
import com.pepperonas.fxiconics.gmd.FxFontGoogleMaterial;
import conexion.AprecioConectar;
import conexion.ComentarioRecurso;
import conexion.EtiquetaUsuario;
import conexion.FicherosBinarios;
import conexion.Like;
import conexion.RecursoClase;
import static conexion.RecursoClase.obtenerRecursosPorId;
import conexion.UsuarioCliente;
import conexion.UsuarioLogin;
import conexion.objetos.Aprecio;
import conexion.objetos.AprecioPK;
import conexion.objetos.Etiqueta;
import conexion.objetos.Recurso;
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
import conexion.objetos.Comentario;
import conexion.objetos.ComentarioPK;
import conexion.objetos.EtiquetaPK;
import conexion.objetos.Visibilidad;
import conexion.objetos.VisibilidadPK;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.RED;
import javafx.scene.paint.Paint;
import javafx.stage.PopupWindow;
import javafx.stage.StageStyle;

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
    @FXML
    private JFXButton btnSalir;
    @FXML
    private TextField txtBuscador;
    @FXML
    private JFXButton btnPerfil;
    @FXML
    private Label lblNArchivos;
    @FXML
    private Label lblNComentarios;
    @FXML
    private Label lblNLikes;
    @FXML
    private ScrollPane scrollGeneral;
    @FXML
    private VBox boxGeneral;
    @FXML
    private ScrollPane scrollPublicas;
    @FXML
    private VBox boxPublicas;
    @FXML
    private ScrollPane scrollPrivadas;
    @FXML
    private VBox boxPrivadas;
    @FXML
    private ScrollPane scrollSinEti;
    @FXML
    private VBox boxSinEti;
    @FXML
    private Label lbCerrarSesion;
    @FXML
    private Label lbNullEti;
    //------------------------------
    private ListView<String> lista = new ListView<>();
    private TextField textComentar = new TextField();
    private Parent parent;
    private HBox vistaRecursos;
    private Label lblApodo;
    private Label lblRecurso;
    private Label lblDescripcion;
    private JFXButton btnVer;
    private final Image cerrarTag = new Image("/imagenes/delete3.png");
    public static UsuarioCliente usuarioInicio;
    private Like likeObjeto;
    private int idRecurso;  //MEJORAR  --- id del recurso que está visualizando el usuario actualmente.     
    private Stage stage;//nuevo para stage
    private static final String SQUARE_BUBBLE
            = "M24 1h-24v16.981h4v5.019l7-5.019h13z";
    private static final String ROUND_BUBBLE
            = "M12 1c-6.628 0-12 4.573-12 10.213 0 2.39.932 4.591 2.427 6.164l-2.427 5.623 7.563-2.26c9.495 2.598 16.437-3.251 16.437-9.527 0-5.64-5.372-10.213-12-10.213z";
//    private Tooltip tool;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        flow.setStyle("-fx-background-image: url('/imagenes/fondolistar.png')");

        //Mostrar los datos del usuario.        
        lblApodoUsuario.setText(usuarioInicio.getApodo());
        lblNArchivos.setText(String.valueOf(usuarioInicio.getNumeroRecursos()));
        lblNLikes.setText(String.valueOf(usuarioInicio.getNumeroLikes()));
        lblNComentarios.setText(String.valueOf(usuarioInicio.getNumeroComentarios()));
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
        toolTip();
        cargarListaRecursos(RecursoClase.obtenerRecursos());
        System.out.println(usuarioInicio.toString());
    }

    //Métodos nuestros.
    public void cargarListasTags(TitledPane tp) {

//        tp.setStyle("-fx-background-color:#EAB0B2;");
        //Switch para cargar el TitledPane apropiado.
//        System.out.println(tp.getText());
        switch (tp.getText()) {
//            case "POPULARES":
//                contenido = configurarListaEtiquetas(EtiquetaUsuario.obtenerEtiquetasPopulares());
//                sp.setContent(contenido);
//                anchorGeneral.setStyle("-fx-background-color:#EAB0B2;");
//                tp.setContent(anchorPopulares);
//                break;
//            case "MAS VALORADAS":
//                contenido = configurarListaEtiquetas(EtiquetaUsuario.obtenerEtiquetasValoradas());
//                sp.setContent(contenido);
//                anchorGeneral.setStyle("-fx-background-color:#EAB0B2;");
//                tp.setContent(anchorValoradas);
//                break;
//            case "NOVEDADES":
//                contenido = configurarListaEtiquetas(EtiquetaUsuario.obtenerEtiquetasNovedades());
//                sp.setContent(contenido);
//                anchorGeneral.setStyle("-fx-background-color:#EAB0B2;");
//                tp.setContent(anchorNovedades);
//                break;
            case "GENERAL":
                boxGeneral = configurarListaEtiquetas(EtiquetaUsuario.obtenerEtiquetasGenerales());
                boxGeneral.setStyle("-fx-background-color:#EAB0B2;");
                scrollGeneral.setContent(boxGeneral);
                scrollGeneral.setStyle("-fx-background-color:#EAB0B2;");
                tp.setContent(scrollGeneral);
                break;
            case "SIN ETIQUETAR":
                boxSinEti = configurarListaEtiquetas(EtiquetaUsuario.obtenerRecursoSinEtiquetar());
                boxSinEti.setStyle("-fx-background-color:#EAB0B2;");
                scrollSinEti.setContent(boxSinEti);
                scrollSinEti.setStyle("-fx-background-color:#EAB0B2;");
                tp.setContent(scrollSinEti);
                break;
            case "PUBLICAS":
                boxPublicas = configurarListaEtiquetas(EtiquetaUsuario.obtenerEtiquetasUsuarioPublicas(usuarioInicio.getIdUsuario()));
//                boxPublicas.setStyle("-fx-background-color: #00B8D0;");
                scrollPublicas.setContent(boxPublicas);
//                scrollPublicas.setStyle("-fx-background-color: #00B8D0;");
                PUBLICAS.setContent(scrollPublicas);
                break;
            case "PRIVADAS":
                boxPrivadas = configurarListaEtiquetas(EtiquetaUsuario.obtenerEtiquetasUsuarioPrivadas(usuarioInicio.getIdUsuario()));
                scrollPrivadas.setContent(boxPrivadas);
                scrollPrivadas.setStyle("-fx-background-color:#EAB0B2;");
                tp.setContent(scrollPrivadas);
                break;
        }
    }

    private VBox configurarListaEtiquetas(List<?> lista) {

        Label l = null;
        VBox contenido = new VBox();
        contenido.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        contenido.setStyle("-fx-background-color:#EAB0B2;");

        for (Object o : lista) {
            if (o instanceof Etiqueta) {    //VALORADAS / POPULARES
                Etiqueta e = (Etiqueta) o;
                l = (new Label("#" + e.getEtiquetaPK().getNombre()));
                l.setOnMouseClicked((new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                            System.out.println("Has pinchado en una etiqueta." + e);
                            cargarListaRecursos(RecursoClase.obtenerRecursosPorEtiqueta(e.getEtiquetaPK().getNombre()));
                        }
                    }
                }));
            }
            if (o instanceof String) { //PUBLICAS / PRIVADAS
                String s = (String) o;
                tagButton(contenido, new Etiqueta(new EtiquetaPK(usuarioInicio.getIdUsuario(), s)));
            }
            if (o instanceof Recurso) {  //SIN ETIQUETAR
                Recurso r = (Recurso) o;
                l = new Label(r.getNombre().substring(0, r.getNombre().lastIndexOf(".")));
                l.setOnMouseClicked((new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                            cargarRecursoCompleto(r.getIdRecurso());
                        }
                    }
                }));
            }
            if (!(o instanceof String)) {//Solo añade cuando no es una instancia de String,el metodo tagButton añade los botones al box
                contenido.getChildren().add(l);
            }
        }
//        contenido.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        contenido.setStyle("-fx-background-color:#EAB0B2;");
        return contenido;
    }

    public void cargarRecursoCompleto(int idRecurso) {//Carga un recurso con toda la informacion (se ejecuta cuando en los recursos dinamicos se pulsa "VER"

        //Borra lo que hay actualmente en la escena(en el flow).
        flow.getChildren().removeAll();
        flow.getChildren().clear();
        //Creas un nuevo Grid.
        GridPane gridRecurso = new GridPane();
        gridRecurso.setStyle("-fx-background-image: url('/imagenes/recursocompleto.png')");
        Recurso recurso = obtenerRecursosPorId(idRecurso);
        //Configura el Grid.
        gridRecurso.setPrefSize(846.0, 645.0);
        gridRecurso.setPadding(new Insets(20));
        gridRecurso.setHgap(25);
        gridRecurso.setVgap(25);
        gridRecurso.getColumnConstraints().add(new ColumnConstraints(130));//Columna 0
        gridRecurso.getColumnConstraints().add(new ColumnConstraints(150));//Columna 1
        gridRecurso.getColumnConstraints().add(new ColumnConstraints(120));//Columna 2
        gridRecurso.getColumnConstraints().add(new ColumnConstraints(140));//Columna 3
        gridRecurso.getColumnConstraints().add(new ColumnConstraints(170));//Columna 4
        //Icono del fichero.
        ImageView imageHouse = new ImageView(new Image(BaseController.class.getResourceAsStream("/imagenes/usuario-recurso.png")));
        gridRecurso.add(imageHouse, 0, 0, 1, 2);
        //Nombre del archivo.
        Text textRecurso = new Text("Nombre del archivo:");
        textRecurso.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        textRecurso.setFill(Color.web("#442850"));
        gridRecurso.add(textRecurso, 1, 0);

        Label nombreRecurso = new Label(recurso.getNombre());
        nombreRecurso.setStyle("-fx-text-fill: #221428");
        nombreRecurso.setWrapText(true);
        nombreRecurso.setFont(Font.font("Arial", 20));
        gridRecurso.add(nombreRecurso, 2, 0, 2, 1);
        //LIKES
        Text like = new Text("LIKES ");
        like.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        like.setFill(Color.web("#AA4747"));
        //Método para obtener los likes del servidor.
        likeObjeto = AprecioConectar.obtenerLikesRecurso(recurso.getIdUsuario(), recurso.getIdRecurso());

        Text cont = new Text(String.valueOf(likeObjeto.getContador()));
        cont.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text icon = GlyphsDude.createIcon(FontAwesomeIconName.HEART, "2em");
        //Colorea el like segun proceda.
        if (likeObjeto.isPropio()) {
            icon.setFill(Color.RED);
        } else {
            icon.setFill(Color.WHITE);
        }

        icon.setOnMousePressed((MouseEvent evento) -> {
            if (!likeObjeto.isPropio()) { //Añadir
                if (AprecioConectar.anadirAprecio(new Aprecio(new AprecioPK(recurso.getIdUsuario(), recurso.getIdRecurso()), new Date()))) {
                    cont.setText(String.valueOf(likeObjeto.incrementar()));
                    likeObjeto.invertir();
                    System.out.println("Aprecio añadido.");
                    icon.setFill(Color.RED);
                }
            } else { //Borrar
                if (AprecioConectar.borrarAprecio(recurso.getIdUsuario(), recurso.getIdRecurso())) {
                    cont.setText(String.valueOf(likeObjeto.decrementar()));
                    likeObjeto.invertir();
                    System.out.println("Aprecio borrado.");
                    icon.setFill(Color.WHITE);
                }
            }
        });

        HBox icli = new HBox();
        icli.setPadding(new Insets(20));
        icli.setSpacing(20);
        icli.getChildren().addAll(like, icon, cont);
        gridRecurso.add(icli, 1, 1, 2, 1);
        //FIN LIKES
        //Descripción
        Text descripcion = new Text("Descripción:");
        descripcion.setFill(Color.web("#442850"));
        descripcion.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        gridRecurso.add(descripcion, 0, 2);

        Label textDescripcion = new Label(recurso.getDescripcion());
        textDescripcion.setMaxSize(300.0, 200.0);
        textDescripcion.setWrapText(true);
        textDescripcion.setFont(Font.font("Arial", 16));
        gridRecurso.add(textDescripcion, 1, 2, 2, 1);
        //FIN Descripción
        //Etiquetas
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(3));
        vbox.setSpacing(2);

        Text title = new Text("TAGS");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        title.setFill(Color.web("#442850"));
        vbox.getChildren().add(title);

        System.out.println(usuarioInicio.getIdUsuario());
        List<Etiqueta> listaEtiquetas = EtiquetaUsuario.obtenerEtiquetasRecurso(recurso.getIdRecurso());
        for (Etiqueta e : listaEtiquetas) {

            Hyperlink h = new Hyperlink("#" + e.getEtiquetaPK().getNombre());
            h.setStyle("-fx-text-fill: #006697;");
            h.setFont(Font.font("Arial", 14));
            h.setOnAction(value -> {
                System.out.println("Has pinchado en una etiqueta.");
                this.cargarListaRecursos(RecursoClase.obtenerRecursosPorEtiqueta(e.getEtiquetaPK().getNombre()));
            });
            vbox.getChildren().add(h);
        }
        gridRecurso.add(vbox, 4, 0, 1, 3);
        //FIN Etiquetas
        //Añadir Etiquetas
        JFXToggleButton tbEtiqueta = new JFXToggleButton();
        //Seleccionado por defecto.
        tbEtiqueta.setSelected(true);
        tbEtiqueta.setFont(Font.font("Arial", 16));
        tbEtiqueta.setStyle("-fx-text-fill: #009688");
        tbEtiqueta.setText("Pública");
        tbEtiqueta.setUnToggleColor(Color.RED);
        tbEtiqueta.setUnToggleLineColor(Color.PINK);

        //Evento
        tbEtiqueta.setOnAction((ActionEvent e) -> {
            if (tbEtiqueta.isSelected()) {
                tbEtiqueta.setText("Pública");
                tbEtiqueta.setStyle("-fx-text-fill: #009688");
            } else {
                tbEtiqueta.setUnToggleColor(Color.RED);
                tbEtiqueta.setUnToggleLineColor(Color.PINK);
                tbEtiqueta.setText("Privada");
                tbEtiqueta.setStyle("-fx-text-fill: #8f000f");
            }
        });
        gridRecurso.add(tbEtiqueta, 3, 1);

        TextField textField = new TextField();
        textField.setStyle("-fx-text-fill: #006697;");
        textField.setFont(Font.font("Arial", 14));
        textField.setPrefSize(135, 35);
        textField.setPromptText("Crea Tag y ENTER ");
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                //Crear etiquetas.
                if (EtiquetaUsuario.crearEtiqueta(new Visibilidad(new VisibilidadPK(usuarioInicio.getIdUsuario(), textField.getText(), recurso.getIdRecurso()), tbEtiqueta.isSelected()))) {
                    this.cargarRecursoCompleto(recurso.getIdRecurso());
                    System.out.println("ETIQUETA creada correctamente.");
                }
                textField.clear();
            }
        });
        gridRecurso.add(textField, 2, 1);
        //FIN Añadir Etiquetas
        //Descargar
        JFXButton descargar = new JFXButton("DESCARGAR");
        descargar.setStyle("-fx-background-color: #333F3B;"
                + " -fx-text-fill: #FFFFFF");
        descargar.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        descargar.setPrefSize(135, 35);
        descargar.setOnAction((ActionEvent e) -> {
            FileChooser fileChooser = new FileChooser();
            //Show save file dialog
            fileChooser.setInitialFileName(recurso.getNombre().substring(0, recurso.getNombre().lastIndexOf(".")));
            File file = fileChooser.showSaveDialog(stage);
            FicherosBinarios.descargar(recurso.getIdRecurso(), file);
        });
        gridRecurso.add(descargar, 0, 6);
        //FIN Descargar
        //Volver
        JFXButton volver = new JFXButton("VOLVER");
        volver.setStyle("-fx-background-color: #333F3B;"
                + " -fx-text-fill: #FFFFFF");
        volver.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        volver.setPrefSize(120, 35);
        volver.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //recurso();
                cargarListaRecursos(RecursoClase.obtenerRecursos());
            }
        });
        gridRecurso.add(volver, 1, 6);
        //FIN Volver
        //Comentar
        JFXButton buttonGuardarComen = new JFXButton("COMENTAR");
        buttonGuardarComen.setStyle("-fx-background-color: #333F3B;"
                + " -fx-text-fill: #FFFFFF");
        buttonGuardarComen.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        buttonGuardarComen.setPrefSize(120, 35);
        buttonGuardarComen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                comentario(recurso);
            }
        });
        gridRecurso.add(buttonGuardarComen, 0, 3);

        textComentar.setPrefSize(200, 20);
        textComentar.setStyle("-fx-text-fill: #006697;");
        textComentar.setFont(Font.font("Arial", 14));
        textComentar.setStyle("-fx-background-color: #FFFFFF;");
        gridRecurso.add(textComentar, 1, 3, 2, 1);

        ObservableList<String> data = FXCollections.observableArrayList();
        for (Comentario c : ComentarioRecurso.obtenerComentariosPorRecurso(recurso.getIdRecurso())) {
            data.add(c.toString());
        }

        lista.setItems(data);
        lista.setPrefSize(150, 140);
        gridRecurso.add(lista, 0, 4, 4, 1);
        //FIN Comentar
        //Se añade el Grid al flow.
        flow.getChildren().add(gridRecurso);

    }

    private void comentario(Recurso r) {//Metodo que crea el comentario con fecha y usuario

        Date objDate = new Date(); // Sistema actual La fecha y la hora se asignan a objDate 

        String fecha = (new SimpleDateFormat("hh:mm:ss a dd-MMM-yyyy").format(objDate)); // El formato de fecha se aplica a la fecha actual

        if (!"".equals(textComentar.getText())) {
            String texto = (fecha + " El usuario " + usuarioInicio.getApodo() + " ,dice: \"" + textComentar.getText() + "\"");
            System.out.println(texto);
            if (ComentarioRecurso.subirComentario(new Comentario(new ComentarioPK(r.getIdUsuario(), r.getIdRecurso(), objDate), texto))) //Añado el comentario en la base de datos.
            {
                System.out.println("Comentario subido correctamente.");
            }
            lista.getItems().add(texto + "\n");
            textComentar.clear();
        }
    }

    @FXML
    private void buscarRecursosHBox(KeyEvent event) {

        lbNullEti.setVisible(false);
        if (event.getCode() == KeyCode.ENTER && !"".equals(txtBuscador.getText())) {
            List<Recurso> buscaLista = RecursoClase.obtenerRecursosBuscarPorEtiqueta(txtBuscador.getText());
            if (!buscaLista.isEmpty()) {
                this.cargarListaRecursos(buscaLista);
            } else {
                lbNullEti.setVisible(true);
            }
            txtBuscador.setText("");
        }
    }

    @FXML
    private void botonListarRecursos(ActionEvent event) {//Boton de la interfaz para listar los recursos

        cargarListaRecursos(RecursoClase.obtenerRecursos());
    }

    @FXML
    private void vistaSubirRecurso(ActionEvent event) throws IOException {//Carga la vista para subir un recurso.

        AnchorPane anchor = FXMLLoader.load(getClass().getResource("/fxml/AnadirRecurso.fxml"));
        flow.getChildren().clear();
        flow.getChildren().add(anchor);
        //border.setCenter(anchor);      

    }

    public void cargarListaRecursos(List<Recurso> listaRecursos) {//Carga dinamicamente una vista con la lista de recursos

        AnchorPane caja;
        GridPane gridPane = new GridPane();

        flow.getChildren().removeAll();
        flow.getChildren().clear();

        for (Recurso r : listaRecursos) {

            caja = new AnchorPane();
            caja.setPrefSize(846, 87);
            caja.setStyle("-fx-background-color: #FF6B6B;");
            gridPane = new GridPane();
            gridPane.setPrefSize(808, 87);
            gridPane.setStyle("-fx-background-image: url('/imagenes/recursodinamico.png')");
            gridPane.setPadding(new Insets(15));
            gridPane.setHgap(30);
            gridPane.setVgap(15);
            gridPane.getRowConstraints().add(new RowConstraints(20));//Fila 0
            gridPane.getRowConstraints().add(new RowConstraints(67));//Fila 1
            AnchorPane.setTopAnchor(gridPane, 10.0);
            AnchorPane.setLeftAnchor(gridPane, 10.0);
            AnchorPane.setRightAnchor(gridPane, 10.0);
            AnchorPane.setBottomAnchor(gridPane, 10.0);

            FxIconicsLabel labIcon = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontGoogleMaterial.Icons.gmd_folder_special)
                    .size(30)
                    //.text("ARCHIVO")
                    .color(MaterialColor.WHITE)
                    .build();

            JFXButton btVer = new JFXButton();
            btVer.setText("VER");
            btVer.setPrefSize(104, 30);
            btVer.setStyle("-fx-text-fill: #000000;"
                    + "-fx-background-color: #ffffff;"
                    + "-fx-font-size: 15px;\n"
                    + "-fx-font-family: \"System\"");
            btVer.setOnAction((ActionEvent e) -> {//Carga la informacion de un recurso completo cuando se pulsa "VER".
                cargarRecursoCompleto(r.getIdRecurso());
                this.idRecurso = r.getIdRecurso();
            });

            Label lbRecurso = new Label(r.getNombre());
            lbRecurso.setPrefSize(140, 58);
            lbRecurso.setWrapText(true);
            lbRecurso.setStyle("-fx-text-fill: #ffffff;"
                    + "-fx-font-size: 15px;\n"
                    + "-fx-font-family: \"System\"");

            Label lbApodo = new Label(UsuarioLogin.obtenerApodoUsuario(r.getIdUsuario()));
            lbApodo.setPrefSize(90, 35);
            lbApodo.setStyle("-fx-text-fill: #ffffff;"
                    + "-fx-font-size: 15px;\n"
                    + "-fx-font-family: \"System\"");

            Label lblDescripcion = new Label(r.getDescripcion());
            lblDescripcion.setPrefSize(340, 58);
            lblDescripcion.setWrapText(true);
            lblDescripcion.setStyle("-fx-text-fill: #ffffff;"
                    + "-fx-font-size: 15px;\n"
                    + "-fx-font-family: \"System\"");
            lblDescripcion.setWrapText(true);
            //lblDescripcion.setAlignment(Pos.CENTER);

            Label lbUsuario = new Label("Usuario:");
            lbUsuario.setPrefSize(90, 35);
            lbUsuario.setFont(Font.font("System", FontWeight.BOLD, 16));
            lbUsuario.setStyle("-fx-text-fill: #ffffff;");

            Label lbNombreRecurso = new Label("Recurso:");
            lbNombreRecurso.setPrefSize(90, 35);
            lbNombreRecurso.setFont(Font.font("System", FontWeight.BOLD, 16));
            lbNombreRecurso.setStyle("-fx-text-fill: #ffffff;");

            Label lbDescripcion = new Label("Descripcion:");
            lbDescripcion.setPrefSize(100, 35);
            lbDescripcion.setFont(Font.font("System", FontWeight.BOLD, 16));
            lbDescripcion.setStyle("-fx-text-fill: #ffffff;");

            gridPane.add(labIcon, 0, 0, 1, 1);
            gridPane.add(lbUsuario, 1, 0, 1, 1);
            gridPane.add(lbNombreRecurso, 2, 0, 1, 1);
            gridPane.add(lbDescripcion, 3, 0, 1, 1);
            gridPane.add(btVer, 0, 1, 1, 1);
            gridPane.add(lbApodo, 1, 1, 1, 1);
            gridPane.add(lbRecurso, 2, 1, 1, 1);
            gridPane.add(lblDescripcion, 3, 1, 1, 1);
            caja.getChildren().add(gridPane);
            flow.getChildren().add(caja);
        }
    }

    public void tagButton(VBox box, Etiqueta tag) {
        ImageView iconCerrar = new ImageView(cerrarTag);
        Label btTag = new Label("#" + tag.getEtiquetaPK().getNombre(), iconCerrar);
        btTag.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getTarget().getClass() == javafx.scene.control.Label.class) //IMAGEN
            {
                iconCerrar.fireEvent(event);
            } else //TEXTO
            {
                this.cargarListaRecursos(RecursoClase.obtenerRecursosPorEtiqueta(tag.getEtiquetaPK().getNombre()));
            }
        });

        iconCerrar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (EtiquetaUsuario.borrarEtiqueta(tag.getEtiquetaPK())) {
                    System.out.println("ETIQUETA borrada.");
                    box.getChildren().removeAll(btTag);
                    cargarRecursoCompleto(idRecurso);
                }
                event.consume();
            }
        });
        box.getChildren().add(btTag);
    }

    public void cerrar() {

        stage = (Stage) lbCerrarSesion.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cerrarSesion(MouseEvent event) {

        try {
            AnchorPane anchor = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
            Stage stage1 = new Stage();
            Scene scene = new Scene(anchor);
            scene.getStylesheets().add("/estilos/estilos.css");
            stage1.setTitle("FInfinity");
            stage1.initStyle(StageStyle.UNDECORATED);
            stage1.getIcons().add(new Image(getClass().getResourceAsStream("/imagenes/icons8_infinity_large_40px.png")));
            stage1.setScene(scene);
            stage1.show();
            cerrar();
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Tooltip formatoToolTip(Tooltip tooltip) {
        tooltip.setStyle("-fx-font: normal bold 16 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;"
                + " -fx-shape: \"" + ROUND_BUBBLE + "\";");
        tooltip.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
        //SQUARE_BUBBLE->distinto formato de la caja del tooltip.
        return tooltip;
        //        tool.setText("PULSE PARA\nVER PERFIL");
//        tool.setStyle("-fx-font: normal bold 16 Langdon; "
//                + "-fx-base: #AE3522; "
//                + "-fx-text-fill: orange;");  
    }
    
    private void toolTip(){//Metodo para cargar los Tooltip.
        
         //Tooltips 
        btnSalir.setTooltip(formatoToolTip(new Tooltip("PULSE PARA SALIR")));
        btnAnadirRecurso.setTooltip(formatoToolTip(new Tooltip("PULSE PARA LISTAR ARCHIVOS PROPIOS")));
        btnAnadirRecEnBase.setTooltip(formatoToolTip(new Tooltip("PULSE PARA AÑADIR UN ARCHIVO")));
        txtBuscador.setTooltip(formatoToolTip(new Tooltip("PULSE PARA BUSCAR ARCHIVOS\nPOR ETIQUETA")));
        POPULARES.setTooltip(formatoToolTip(new Tooltip("PULSE PARA LISTAR \n LAS ETIQUETAS MAS POPULARES")));
        MASVALORADAS.setTooltip(formatoToolTip(new Tooltip("PULSE PARA LISTAR \nLAS ETIQUETAS MAS VALORADAS")));
        NOVEDADES.setTooltip(formatoToolTip(new Tooltip("PULSE PARA LISTAR\nLAS ULTIMAS ETIQUETAS")));
        GENERAL.setTooltip(formatoToolTip(new Tooltip("PULSE PARA LISTAR \nTODAS LAS ETIQUETAS")));
        SINETIQUETAR.setTooltip(formatoToolTip(new Tooltip("PULSE PARA LISTAR \nARCHIVOS SIN ETIQUETAS")));
        PUBLICAS.setTooltip(formatoToolTip(new Tooltip("PULSE PARA LISTAR \nETIQUETAS PÚBLICAS PROPIAS")));
        PRIVADAS.setTooltip(formatoToolTip(new Tooltip("PULSE PARA LISTAR \nETIQUETAS PRIVADAS PROPIAS")));
        lbCerrarSesion.setTooltip(formatoToolTip(new Tooltip("PULSE PARA \nCERRAR LA SESION")));
        //Fin Tooltips
    }

    @FXML
    private void salir(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void perfil(ActionEvent event) {
    }
}
