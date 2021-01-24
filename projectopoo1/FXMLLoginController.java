package projectopoo1;

import Registro.FXMLRegistroController;
import static Registro.FXMLRegistroController.PASSWORD;
import static Registro.FXMLRegistroController.URL;
import static Registro.FXMLRegistroController.USERNAME;
import VentanaPrincipal.CalendarioInicioController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    /////////////////////

    PreparedStatement ps;
    ResultSet rs;
    Connection con = null;
    /////////////////
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

        if (us.getText().isEmpty() == false && cot.getText().isEmpty() == false) {
            validateLogin();
        } else {
            prueba.setText("Ingresa tu usuario y contraseña");
        }
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

    @FXML
    private void CalendarioDeEvento() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/VentanaPrincipal/CalendarioInicio.fxml"));

            Parent root = loader.load();

            CalendarioInicioController controlador = loader.getController();

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

    }

    //funcion de base de datos

    public static Connection getConecion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Conexion exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return con;
    }

    public void validateLogin() {
        try {
            con = getConecion();
            String vlogin ="SELECT count(1) FROM usuario WHERE useres = '"+us.getText()+"' AND password = '"+cot.getText()+"'";
            
            Statement stat = con.createStatement();
            rs =stat.executeQuery(vlogin);
            
            while (rs.next()) {                
                if (rs.getInt(1)== 1) {
                    System.out.println("usuario correcto");
                    CalendarioDeEvento();
                }
                else{
                    prueba.setText("usuario incorrecto");
                }
            }
        } catch (Exception e) {
            
        }

    }

}
