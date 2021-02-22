package VentanaPrincipal;

import ConexionBdb.evento;
import ConexionBdb.sqlEvento;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EventosController implements Initializable {

    public static final String base = "datos";
    public static final String user = "root";
    public static final String password = "";
    public static final String url = "jdbc:mysql://localhost:3308/" + base;

    PreparedStatement ps;
    ResultSet rs;

    @FXML
    private DatePicker fechas;
    @FXML
    private ComboBox ListaEventos;
    @FXML
    private ImageView salir;
    @FXML
    private TextField nombreevento;
    @FXML
    private TextArea txtdescripcion;
    @FXML
    private ComboBox cbxSubtipo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> categoria = FXCollections.observableArrayList("Universidad", "Coloegio", "Trabajo");
        ListaEventos.setItems(categoria);
        ObservableList<String> subcategoria = FXCollections.observableArrayList("proyecto", "tareas", "reuniones");
        cbxSubtipo.setItems(subcategoria);

    }

    void CerrarVentana() {
        Stage stage = (Stage) this.salir.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cerrarventanas(MouseEvent event) {
        CerrarVentana();
    }

    @FXML
    private void guradarDatos(ActionEvent event) {
        validarEvento();
    }
    private void validarEvento() {
        sqlEvento modevento = new sqlEvento();
        evento mod = new evento();
        if (!nombreevento.getText().equals("")) {
            if (modevento.existeventohora(fechas.getValue().toString()) == 0) {
                mod.setTema_evento(nombreevento.getText());
                mod.setDescripcion(txtdescripcion.getText());
                mod.setFecha(fechas.getValue().toString());
                mod.setCategoria(ListaEventos.getSelectionModel().getSelectedItem().toString());
                mod.setSubcategoria(cbxSubtipo.getSelectionModel().getSelectedItem().toString());
                if (modevento.registrarEvento(mod)) {
                    System.out.println("Evento guardado");
                    CerrarVentana();
                } else {
                    System.out.println("No se pudo guardar");
                }
            } else {
                System.out.println("Fecha ya resgistrada");
            }

        } else {
            System.out.println("Completa el campo requerido");
        }
    }

}
