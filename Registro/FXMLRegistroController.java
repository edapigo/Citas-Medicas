package Registro;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    //variables de base de datos
    public static final String URL = "jdbc:mysql://localhost:3308/datos";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    PreparedStatement ps;
    ResultSet rs;
    Connection con = null;
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

    //Metodos
    @FXML
    private void DatosRegistro(ActionEvent e) {//validacion de campos de registro
        if (email.getText().isEmpty() == false && username.getText().isEmpty() == false && pass.getText().isEmpty() == false) {
            
            try {

                con = getConecion();

                ps = con.prepareStatement("INSERT INTO usuario (email, useres, password) VALUES(?,?,?)");
                ps.setString(1, email.getText());
                ps.setString(2, username.getText());
                ps.setString(3, pass.getText());
                
                int res = ps.executeUpdate();
                
                if (res >0) {
                    texto2.setText("Registro completo");
                    Limpiar();
                } else {
                    System.out.println("vales verga puto");
                }
                con.close();

            } catch (Exception es) {
                System.out.println(es);
            }
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
    
    public static Connection getConecion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
    private void Limpiar(){
        email.setText("");
        username.setText("");
        pass.setText("");
    }
}
