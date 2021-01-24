package VentanaPrincipal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class CalendarioInicioController implements Initializable {

    @FXML
    private BorderPane border1;
    @FXML
    private VBox Menu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Inicio();
        Menu.setVisible(false);
    }

    @FXML
    private void calendario(ActionEvent event) {
        loadFxmlCalendario("Calendario");
        Menu.setVisible(false);
    }
    @FXML
    private void MenuSlide(ActionEvent event) {
        Menu.setVisible(true);
    }


    @FXML
    public void CerrarVentana() {
        System.exit(0);
    }

    private void loadFxmlCalendario(String Calendario) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(Calendario + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(CalendarioInicioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        border1.setCenter(root);

    }

    @FXML
    private void Inicio() {
        Parent root1 = null;
        try {
            root1 = FXMLLoader.load(getClass().getResource("Bienvenidos.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(CalendarioInicioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        border1.setCenter(root1);
    }

}
