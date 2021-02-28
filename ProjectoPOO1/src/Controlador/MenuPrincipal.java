package Controlador;

import Modelo.AnchorPaneNode;
import java.io.IOException;
import java.net.URL;
///////////////////////
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
///////////////////////////
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
///////////////////////////
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
////////////////////////////
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//////////////////////////
import Modelo.evento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/////////////////////////////
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MenuPrincipal implements Initializable {

    //variables  del reloj  
    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss a");
    String horaActual = formateador.format(LocalDateTime.now());
    int Meses = localDate.getMonthValue();
    int anio = localDate.getYear();
    //variables de pantalla
    private float xOffset = 0f;
    private float yOffset = 0f;
    //variables de listas
    ObservableList<evento> data_table = FXCollections.observableArrayList();
    //variables de conexion
    public static final String base = "datos";
    public static final String user = "root";
    public static final String password = "";
    public static final String url = "jdbc:mysql://localhost:3308/" + base;
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    ////////
    @FXML
    private Button btn;
    @FXML
    public VBox Menu;
    @FXML
    private Button agregar;
    @FXML
    private HBox Menusuper;
    @FXML
    private AnchorPane Panelajuste;
    @FXML
    private ColorPicker pikercolor;
    @FXML
    private ColorPicker pikercolor2;
    @FXML
    private AnchorPane Bienvenido;
    @FXML
    private AnchorPane Panelcalendario;
    @FXML
    private Label meses;
    @FXML
    private Label Anios;
    @FXML
    private Label ContadorHoras;
    @FXML
    private AnchorPane panelamigos;
    @FXML
    private Pane color1;
    @FXML
    private Pane color2;
    @FXML
    private GridPane calendario;
    
    private YearMonth AñoMesActual;
    private final ArrayList<AnchorPaneNode> diasCalendario = new ArrayList<>(35);
    //tablas
    @FXML
    private TableView<evento> table_eventos;
    @FXML
    private TableColumn<evento, String> col_evento;
    @FXML
    private TableColumn<evento, String> col_descripcion;
    @FXML
    private TableColumn<evento, String> col_fecha;
    @FXML
    private TableColumn<evento, String> col_tipo;
    @FXML
    private TableColumn<evento, String> col_tsevento;
    //tooltips y botones
    @FXML
    private Tooltip tthome;
    @FXML
    private Button home;
    @FXML
    private Tooltip ttagregar;
    @FXML
    private Button btncal;
    @FXML
    private Tooltip ttcall;
    @FXML
    private Button btnamigo;
    @FXML
    private Tooltip ttamigos;
    @FXML
    private Button btnajust;
    @FXML
    private Tooltip ttajuste;
    @FXML
    private Tooltip ttsalir;
    @FXML
    private Button btnsalir;
/////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tootipsOntime();
        inticol();
        colorinicio();
        Calendariopanel();
        //reloj 
        reloj();
        //calendario
        meses(Meses);
        Anios.setText(anio + "");
        //ventanas
        Bienvenido.setVisible(true);
        Panelajuste.setVisible(false);
        Panelcalendario.setVisible(false);
        agregar.setVisible(false);
        panelamigos.setVisible(false);

    }

    @FXML
    private void calendario(ActionEvent event) {
        Panelcalendario.setVisible(true);
        Bienvenido.setVisible(false);
        Panelajuste.setVisible(false);
        agregar.setVisible(true);
        panelamigos.setVisible(false);

    }

    @FXML
    public void CerrarVentana() {
        System.exit(0);
    }

    @FXML
    private void Inicio() {
        Panelcalendario.setVisible(false);
        Panelajuste.setVisible(false);
        Bienvenido.setVisible(true);
        panelamigos.setVisible(false);
        agregar.setVisible(false);

    }
//ventanas menu

    @FXML
    private void VentanaAjuste(ActionEvent event) {
        Panelcalendario.setVisible(false);
        Panelajuste.setVisible(true);
        Bienvenido.setVisible(false);
        panelamigos.setVisible(false);
        agregar.setVisible(false);
    }

    @FXML
    private void VentanaAmigos(ActionEvent event) {
        panelamigos.setVisible(true);
        Panelcalendario.setVisible(false);
        Panelajuste.setVisible(false);
        Bienvenido.setVisible(false);
        agregar.setVisible(false);

    }
//cambio de colores

    @FXML
    private void cambiocolor(ActionEvent event) {
        colorinicio();
    }

    @FXML
    private void cambiarcolor2(ActionEvent event) {
        colorinicio();
    }
    //guradar colores

//Fechas
    @FXML
    private void atrasMeses(MouseEvent event) {
        if (Meses <= 12) {
            Meses--;
            meses(Meses);
        }
        AñoMesActual = AñoMesActual.minusMonths(1);
        actualizarCalendario(AñoMesActual);
    }

    @FXML
    private void adelenteMes(MouseEvent event) {
        if (Meses <= 11) {
            Meses++;
            meses(Meses);
        }
        AñoMesActual = AñoMesActual.plusMonths(1);
        actualizarCalendario(AñoMesActual);
    }

    @FXML
    private void adelentaAnio(MouseEvent event) {
        anio++;
        Anios.setText(anio + "");
        AñoMesActual = AñoMesActual.plusYears(1);
        actualizarCalendario(AñoMesActual);
    }

    @FXML
    private void atrasAnio(MouseEvent event) {
        anio--;
        Anios.setText(anio + "");
        AñoMesActual = AñoMesActual.minusYears(1);
        actualizarCalendario(AñoMesActual);
    }
    @FXML
    private void refrescar(MouseEvent event) {
        Resfresh();
    }
//calendario
    private void meses(int Meses) {
        switch (Meses) {
            case 1:
                meses.setText("Enero");
                break;
            case 2:
                meses.setText("Febrero");
                
                break;
            case 3:
                meses.setText("Marzo");
                
                break;
            case 4:
                meses.setText("Abril");
               
                break;
            case 5:
                meses.setText("Mayo");
                break;
            case 6:
                meses.setText("Junio");
                break;
            case 7:
                meses.setText("Julio");
                break;
            case 8:
                meses.setText("Agosto");
                break;
            case 9:
                meses.setText("Septiembre");
                break;
            case 10:
                meses.setText("Octubre");
                break;
            case 11:
                meses.setText("Noviembre");
                break;
            case 12:
                meses.setText("Diciembre");
                break;
        }
    }

    @FXML
    private void ventanaevento(ActionEvent event) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("tips/java/cfg/lableText");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/eventos.fxml"), bundle);

            Parent root = loader.load();

            EventosController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);

            scene.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = (float) event.getSceneX();
                    yOffset = (float) event.getSceneY();
                }
            });
            scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });
            stage.setScene(scene);
            stage.showAndWait();

            stage.setOnCloseRequest(e -> controlador.CerrarVentana());

        } catch (IOException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//metodo de colores 
    private void colorinicio() {

        Color selectcolor1 = pikercolor2.getValue();
        BackgroundFill e = new BackgroundFill(Paint.valueOf(selectcolor1.toString()), CornerRadii.EMPTY, Insets.EMPTY);
        color2.setBackground(new Background(e));
        Menusuper.setBackground(new Background(e));

        Color selectcolor = pikercolor.getValue();
        pikercolor.setValue(selectcolor);
        BackgroundFill c = new BackgroundFill(Paint.valueOf(selectcolor.toString()), CornerRadii.EMPTY, Insets.EMPTY);
        Menu.setBackground(new Background(c));
        color1.setBackground(new Background(c));
    }
//tabla de eventos 
    private void inticol() {
        try {
            con = getConexion();
            rs = con.createStatement().executeQuery("SELECT * FROM eventos_registro where ideventos");
            while (rs.next()) {
                data_table.add(new evento(rs.getString("tema_evento"), rs.getString("descripcion"), rs.getString("fecha"),
                        rs.getString("categoria"), rs.getString("subcategoria")));
            }
        } catch (Exception e) {

        }

        col_evento.setCellValueFactory(new PropertyValueFactory<>("tema_evento"));
        col_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        col_fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        col_tipo.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        col_tsevento.setCellValueFactory(new PropertyValueFactory<>("subcategoria"));

        table_eventos.setItems(data_table);

    }
//update de la tabla de eventos
    private void Resfresh() {
        try {
            data_table.clear();
            con = getConexion();
            rs = con.createStatement().executeQuery("SELECT * FROM eventos_registro");
            while (rs.next()) {
                data_table.add(new evento(rs.getString("tema_evento"), rs.getString("descripcion"), rs.getString("fecha"),
                        rs.getString("categoria"), rs.getString("subcategoria")));
            }
        } catch (Exception e) {
        }
    }
//tooltip para el menu
    private void tootipsOntime() {
        home.setTooltip(tthome);
        agregar.setTooltip(ttagregar);
        btncal.setTooltip(ttcall);
        btnamigo.setTooltip(ttamigos);
        btnajust.setTooltip(ttajuste);
        btnsalir.setTooltip(ttsalir);
    }
//reloj
    public void reloj() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(300);
                        Platform.runLater(() -> {
                            ContadorHoras.setText(formateador.format(LocalDateTime.now()));
                        });

                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
    }
    //getconexion
    public static Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            //JOptionPane.showMessageDialog(null, "conexion exitosa");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }

        return con;
    }
    private void Calendariopanel(){
         AñoMesActual = YearMonth.now();
        
        //Llena el GridPane con archor pane
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                AnchorPaneNode ap = new AnchorPaneNode();
                ap.setPrefSize(200,200);
                calendario.add(ap,j,i);
                diasCalendario.add(ap);
            }
        }
        //Función que actualiza el calendario
        actualizarCalendario(YearMonth.now());
    }
    private void actualizarCalendario(YearMonth yearMonth) {
        // Obtiene el mes que vamos a empezar.
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        // Regresar los dias hasta que sea lunes, excepto si empieza en lunes
        while (!calendarDate.getDayOfWeek().toString().equals("MONDAY")) {
            calendarDate = calendarDate.minusDays(1);
        }
        // Actualizar el calendario con los dias
        for (AnchorPaneNode ap : diasCalendario) {
            if (ap.getChildren().size() != 0) {
                ap.getChildren().remove(0);
            }
            Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
            txt.setFont(Font.font(18.0));
            ap.setDate(calendarDate);
            AnchorPaneNode.setTopAnchor(txt, 5.0);
            AnchorPaneNode.setLeftAnchor(txt, 5.0);
            ap.getChildren().add(txt);
            calendarDate = calendarDate.plusDays(1);
        }
    }
    
}
