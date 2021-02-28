package Controlador;

import Modelo.SqlUsuarios;
import Modelo.hash;
import Modelo.usuarios;
import java.io.IOException;
import java.net.URL;
//imports de escenas
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
/////////////////////////////////
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
/////////////////////////////
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
//////////////////////////////
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Notifications;

public class FXMLRegistroController implements Initializable {

    //variables de ventana
    private float xOffset = 0f;
    private float yOffset = 0f;
    @FXML
    private Button btn;
    @FXML
    public TextField email;
    @FXML
    public TextField username;
    @FXML
    public PasswordField pass;
    @FXML
    public PasswordField passconfirme;

    //Metodos
    @FXML
    private void DatosRegistro(ActionEvent e) {//validacion de campos de registro
        if (email.getText().isEmpty() == false && username.getText().isEmpty() == false && pass.getText().isEmpty() == false && passconfirme.getText().isEmpty() == false) {
            validarRegistro();
        } else {
            Notification("Ontime", "Complete los campos");
        }

    }

    @FXML
    private void SalirDeRegistro() {//cerrar ventanas
        System.exit(0);

    }

    @FXML
    public void CerrarVentana() {//volver al inicio de sesion
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("tips/java/cfg/lableText");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/FXMLLogin.fxml"),bundle);

            Parent root = loader.load();

            FXMLLoginController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.initStyle(StageStyle.UNDECORATED);

            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = (float) event.getSceneX();
                    yOffset = (float) event.getSceneY();
                }
            });
            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });

            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.btn.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void validarRegistro() {
        SqlUsuarios modSql = new SqlUsuarios();
        usuarios mod = new usuarios();
        //validar registro
        //validar caontra
        if (pass.getText().equals(passconfirme.getText())) {

            if (modSql.existeusuario(username.getText()) == 0) {

                if (modSql.esEmail(email.getText())) {

                    String nuevopass = hash.sha1(pass.getText());

                    mod.setUsuario(username.getText());
                    mod.setPassword(nuevopass);
                    mod.setCorreo(email.getText());
                    mod.setId_tipo(1);

                    if (modSql.registrar(mod)) {
                        Notification("Ontime", "Registro guardado");
                        Limpiar();
                    } else {
                        Notification("Ontime", "Error para guardar registro");
                    }
                } else {
                    Notification("Ontime", "Correo no valido");
                }
            } else {
                Notification("Ontime", "El usuario ya existe");
            }
        } else {
            Notification("Ontime", "Las contrseñas no son iguales");
        }

    }
//Limpiar cajas
    private void Limpiar() {
        email.setText("");
        username.setText("");
        pass.setText("");
        passconfirme.setText("");
    }
    //notificaciones
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
}
