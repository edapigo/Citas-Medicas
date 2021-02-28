
package Controlador;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
////////////////////////////////
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
////////////////////////////////////////
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;


public class PaneleventosController implements Initializable {
    public static final String base = "datos";
    public static final String user = "root";
    public static final String password = "";
    public static final String url = "jdbc:mysql://localhost:3308/" + base;

    PreparedStatement ps;
    ResultSet rs;

    @FXML
    private DatePicker Dtfechas;
    @FXML
    private BorderPane panel;
    @FXML
    private TextField TextNombreEvento;
    @FXML
    private TextArea textDescripcionevento;
    @FXML
    private TextField txtcategoria;
    @FXML
    private TextField txtSubcategori;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Dtfechas.setDisable(true);
    }    
      @FXML
    private void exitventana(MouseEvent event) {
        CerrarVentana();
    }

    public void CerrarVentana() {
        Stage stage = (Stage) this.panel.getScene().getWindow();
        stage.close();
       
    }
    public void verfechas (LocalDate dia){
        Dtfechas.setValue(dia);
        Connection con = null;
        try {
            con = getConexion();
            ps = con.prepareStatement("SELECT * FROM eventos_registro WHERE fecha = ?");
            ps.setString(1, Dtfechas.getValue().toString());
            rs = ps.executeQuery();
            if (rs.next()) {
                TextNombreEvento.setText(rs.getString("tema_evento"));
                txtcategoria.setText(rs.getString("categoria"));
                txtSubcategori.setText(rs.getString("subcategoria"));
                textDescripcionevento.setText(rs.getString("descripcion"));
                Notification1("Ontime", "Evento encontrado");
            } else {
                Notification1("Ontime", "En este dia no hay evento");
            }
        } catch (Exception e) {
        }
       
    }
    //conexion
    public static Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }

        return con;
    }
    //notificaciones
    public void Notification1(String Nombre, String titulo) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Notifications notify = Notifications.create().title(Nombre)
                        .text(titulo)
                        .hideAfter(javafx.util.Duration.seconds(2))
                        .position(Pos.TOP_CENTER);
                notify.darkStyle();
                notify.showInformation();
            }
        });
    }
    

  
    
}
