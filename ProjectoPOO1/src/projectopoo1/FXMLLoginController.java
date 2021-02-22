package projectopoo1;

import ConexionBdb.SqlUsuarios;
import ConexionBdb.hash;
import ConexionBdb.usuarios;
import Registro.FXMLRegistroController;
import VentanaPrincipal.MenuPrincipal;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class FXMLLoginController implements Initializable {
    private float xOffset = 0f;
    private float yOffset = 0f;
    @FXML
    public TextField us;
    @FXML
    public PasswordField cot;
    @FXML
    private Button btn1Registro;
    @FXML
    private Button inicio;
    @FXML
    public Label prueba;
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

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Registro/FXMLRegistro.fxml"));

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

    private void CalendarioDeEvento() {

        try {
            ResourceBundle bundle = ResourceBundle.getBundle("tips/java/cfg/lableText");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/VentanaPrincipal/MenuPrincipal.fxml"),bundle);

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rb.getLocale();

    }

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
                CalendarioDeEvento();
            }else{
                prueba.setText("datos no validos");
            }
        }else{
            prueba.setText("Debe ingresar sus datos ");
        }
        
    }
    

}
