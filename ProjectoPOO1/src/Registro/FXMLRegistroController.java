package Registro;

import ConexionBdb.SqlUsuarios;
import ConexionBdb.hash;
import ConexionBdb.usuarios;
import java.io.IOException;
import java.net.URL;
//imports de escenas
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projectopoo1.FXMLLoginController;

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
    private Label texto2;
    @FXML
    public PasswordField passconfirme;

    //Metodos
    @FXML
    private void DatosRegistro(ActionEvent e) {//validacion de campos de registro
        if (email.getText().isEmpty() == false && username.getText().isEmpty() == false && pass.getText().isEmpty() == false && passconfirme.getText().isEmpty() == false) {
            validarRegistro();
        } else {
            texto2.setText("Complete los campos");
        }

    }

    @FXML
    private void SalirDeRegistro() {//cerrar ventanas
        System.exit(0);

    }

    @FXML
    public void CerrarVentana() {//volver al inicio de sesion
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projectopoo1/FXMLLogin.fxml"));

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
                        texto2.setText("Registro guardado");
                        Limpiar();
                    } else {
                        System.out.println("Error para guardar registro");
                    }
                } else {
                    texto2.setText("Correo no valido ");
                }
            } else {
                texto2.setText("El usuario ya existe");
            }
        } else {
            texto2.setText("Las contrseñas no son iguales");
        }

    }

    private void Limpiar() {
        email.setText("");
        username.setText("");
        pass.setText("");
        passconfirme.setText("");
    }
}
