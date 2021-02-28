package Controlador;

import Modelo.SqlUsuarios;
import Modelo.hash;
import Modelo.usuarios;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
//////////////////////////////////
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
///////////////////////////////////
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
///////////////////////////////////
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Notifications;

public class FXMLLoginController implements Initializable {
//variables de pantalla
    private float xOffset = 0f;
    private float yOffset = 0f;
    //////////////
    @FXML
    public TextField us;
    @FXML
    public PasswordField cot;
    @FXML
    private Button btn1Registro;
    @FXML
    private Button inicio;

    //metodos
    @FXML
    private void Datos(ActionEvent e) {
        validateLogin();
    }

    @FXML
    private void salir(ActionEvent e) {
        System.exit(0);
    }

    @FXML
    private void Registro(ActionEvent event) {

        seceneResgistro();

    }

    private void CalendarioDeEvento() {
        secenecalendario();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rb.getLocale();

    }
//validar login

    public void validateLogin() {
        SqlUsuarios modsql = new SqlUsuarios();
        usuarios mod = new usuarios();

        Date date = new Date();
        DateFormat fechahora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String pass = cot.getText();

        if (!us.getText().equals("") && !pass.equals("")) {
            String nuevopass = hash.sha1(pass);

            mod.setUsuario(us.getText());
            mod.setPassword(nuevopass);
            mod.setLast_session(fechahora.format(date));

            if (modsql.login(mod)) {
                Notification1("Ontime", "Bienvenido a Ontime");
                CalendarioDeEvento();
                
            } else {
                Notification("Ontime", "datos no validos");
                limpiar();
            }
        } else {
            Notification("Ontime", "Debe ingresar sus datos");
        }

    }
//notificaciones

    public void Notification(String Nombre, String titulo) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Notifications notify = Notifications.create().title(Nombre)
                        .text(titulo)
                        .hideAfter(javafx.util.Duration.seconds(2))
                        .position(Pos.CENTER);
                notify.darkStyle();
                notify.showInformation();
            }
        });

    }
    //notificaciones
     public void Notification1(String Nombre, String titulo) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Notifications notify = Notifications.create().title(Nombre)
                        .text(titulo)
                        .hideAfter(javafx.util.Duration.seconds(2))
                        .position(Pos.BOTTOM_RIGHT);
                notify.darkStyle();
                notify.showInformation();
            }
        });

    }
//llamar al menu principal

    private void secenecalendario() {
        try {

            ResourceBundle bundle = ResourceBundle.getBundle("tips/java/cfg/lableText");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/MenuPrincipal.fxml"), bundle);
            Parent root = loader.load();

            MenuPrincipal controlador = loader.getController();

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
            stage.show();

            stage.setOnCloseRequest(e -> controlador.CerrarVentana());

            Stage myStage = (Stage) this.inicio.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//llamar ventana registro

    private void seceneResgistro() {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("tips/java/cfg/lableText");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/FXMLRegistro.fxml"),bundle);

            Parent root = loader.load();

            FXMLRegistroController controlador = loader.getController();

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
            stage.show();

            stage.setOnCloseRequest(e -> controlador.CerrarVentana());

            Stage myStage = (Stage) this.btn1Registro.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void limpiar(){
        us.setText("");
        cot.setText("");
    }
}
