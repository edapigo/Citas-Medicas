package Controlador;

import Modelo.evento;
import Modelo.sqlEvento;
import java.net.URL;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
//////////////////////////////////
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
///////////////////////////
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
////////////////////////////////////////
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
////////////////////////////////////////
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;

public class EventosController implements Initializable {

    @FXML
    private DatePicker fechas;
    @FXML
    private ImageView salir;
    @FXML
    private TextField nombreevento;
    @FXML
    private TextArea txtdescripcion;
    //combobox
    @FXML
    private ComboBox ListaEventos;
    @FXML
    private ComboBox UComboBox;
    @FXML
    private ComboBox TComboBox;
    @FXML
    private ComboBox EComboBox;
    @FXML
    private ComboBox FComboBox;
    //botones
    @FXML
    private Button uni;
    @FXML
    private Button tr;
    @FXML
    private Button ent;
    @FXML
    private Button fes;
    //textfields
    @FXML
    private TextField txtdestino;
    @FXML
    private TextField txtasunto;
    @FXML
    private TextArea txtdesinvita;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Listascombobox();

    }

    @FXML
    private void cerrarventanas(MouseEvent event) {
        CerrarVentana();
    }

    @FXML
    private void guradarDatos(ActionEvent event) {
        validarEvento();
        enviarInvitacion();
    }

    @FXML
    private void even(ActionEvent event) {
        if (ListaEventos.getSelectionModel().getSelectedItem().toString() == ListaEventos.getSelectionModel().getSelectedItem().toString()) {
            switch (ListaEventos.getSelectionModel().getSelectedIndex()) {
                case 0:
                    uni.setDisable(false);
                    tr.setDisable(true);
                    ent.setDisable(true);
                    fes.setDisable(true);
                    UComboBox.setDisable(false);
                    TComboBox.setDisable(true);
                    EComboBox.setDisable(true);
                    FComboBox.setDisable(true);
                    break;
                case 1:
                    tr.setDisable(false);
                    uni.setDisable(true);
                    ent.setDisable(true);
                    fes.setDisable(true);
                    UComboBox.setDisable(true);
                    TComboBox.setDisable(false);
                    EComboBox.setDisable(true);
                    FComboBox.setDisable(true);
                    break;
                case 2:
                    ent.setDisable(false);
                    uni.setDisable(true);
                    tr.setDisable(true);
                    fes.setDisable(true);
                    UComboBox.setDisable(true);
                    TComboBox.setDisable(true);
                    EComboBox.setDisable(false);
                    FComboBox.setDisable(true);
                    break;
                case 3:
                    ent.setDisable(true);
                    uni.setDisable(true);
                    tr.setDisable(true);
                    fes.setDisable(false);
                    UComboBox.setDisable(true);
                    TComboBox.setDisable(true);
                    EComboBox.setDisable(true);
                    FComboBox.setDisable(false);
                    break;
            }
        } else {
            Notification("Ontime", "no se pudo realizar el cambio");
        }
    }
//Notificaciones

    public void Notification(String Nombre, String titulo) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Notifications notify = Notifications.create().title(Nombre)
                        .text(titulo)
                        .hideAfter(javafx.util.Duration.seconds(4))
                        .position(Pos.TOP_RIGHT);
                notify.darkStyle();
                notify.showInformation();
            }
        });

    }
//lista de los combobox

    private void Listascombobox() {
        ObservableList<String> categoria = FXCollections.observableArrayList("Universidad", "Trabajo", "Entretenimiento", "Festividades");
        ListaEventos.setItems(categoria);
        ObservableList<String> list = FXCollections.observableArrayList("Tareas", "Exposiciones", "Examenes", "Otros");
        UComboBox.setItems(list);
        ObservableList<String> list2 = FXCollections.observableArrayList("Reuniones", "Proyectos", "Otros");
        TComboBox.setItems(list2);
        ObservableList<String> list3 = FXCollections.observableArrayList("Cine ", "Fiestas ", "Otros  ");
        EComboBox.setItems(list3);
        ObservableList<String> list4 = FXCollections.observableArrayList(" Cumpleaños ", "Otros  ");
        FComboBox.setItems(list4);
    }
//Envia invitaciones////

    private void enviarInvitacion() {
        try {
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");

            Session sesion = Session.getDefaultInstance(props);
            String correoRemitente = "fastontimecalendar@gmail.com";
            String passwordremitente = "Ontime124578";
            String correoReceptor = txtdestino.getText();
            String asunto = txtasunto.getText();
            String mensaje = txtdesinvita.getText();

            MimeMessage message = new MimeMessage(sesion);
            message.setFrom(new InternetAddress(correoRemitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
            message.setSubject(asunto);
            message.setText(mensaje);

            Transport t = sesion.getTransport("smtp");
            t.connect(correoRemitente, passwordremitente);
            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            t.close();

            Notification("Ontime le informa que la ", "Invitacion envia correctamente");

        } catch (Exception e) {
        }

    }

    //Cerrar ventana
    protected void CerrarVentana() {
        Stage stage = (Stage) this.salir.getScene().getWindow();
        stage.close();
    }
    //Validar la creacion de eventos

    private void validarEvento() {
        sqlEvento modevento = new sqlEvento();
        evento mod = new evento();
        if (!nombreevento.getText().equals("") && !txtdescripcion.getText().equals("")) {
            mod.setTema_evento(nombreevento.getText());
            mod.setDescripcion(txtdescripcion.getText());
            mod.setFecha(fechas.getValue().toString());
            mod.setCategoria(ListaEventos.getSelectionModel().getSelectedItem().toString());
            if (ListaEventos.getSelectionModel().getSelectedItem().toString() == ListaEventos.getSelectionModel().getSelectedItem().toString()) {
                switch (ListaEventos.getSelectionModel().getSelectedIndex()) {
                    case 0:
                        mod.setSubcategoria(UComboBox.getSelectionModel().getSelectedItem().toString());
                        break;
                    case 1:
                        mod.setSubcategoria(TComboBox.getSelectionModel().getSelectedItem().toString());
                        break;
                    case 2:
                        mod.setSubcategoria(EComboBox.getSelectionModel().getSelectedItem().toString());
                        break;
                    case 3:
                        mod.setSubcategoria(FComboBox.getSelectionModel().getSelectedItem().toString());
                        break;
                }
            }
            if (modevento.registrarEvento(mod)) {
                Notification("Ontime", "Evento creado correctamente");
                CerrarVentana();
            } else {
                Notification("Ontime", "El evento no se pudo guardar");
            }

        } else {
            Notification("Ontime", "Completa el campo requerido");
        }
    }
}
