package VentanaPrincipal;

import ConexionBdb.usuarios;
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
import javafx.scene.control.Button;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MenuPrincipal implements Initializable {
    usuarios mod;
    
    @FXML
    private BorderPane border1;
    @FXML
    private VBox Menu;
    @FXML
    private Button agregar;
    @FXML
    private Button cal;
    @FXML
    private Button amigos;
    @FXML
    private Button ajuste;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicio();
        //agregar.setVisible(false);
    }
    public void initialize(usuarios mod) {
        
        this.mod = mod;
        agregar.setVisible(false);
        if (mod.getId_tipo() == 1) {
           agregar.setVisible(false);
           ajuste.setVisible(true);
        }else if (mod.getId_tipo() == 2){
            Inicio();
            agregar.setVisible(true);
            ajuste.setVisible(false);
        }
    }

    @FXML
    private void calendario(ActionEvent event) {
        loadFxmlCalendario("Calendario");
        
        Menu.setVisible(false);
        cal.setVisible(false);
        amigos.setVisible(false);
        
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
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        border1.setCenter(root);

    }

    @FXML
    private void Inicio() {
        Parent root1 = null;
        try {
            root1 = FXMLLoader.load(getClass().getResource("Bienvenidos.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        border1.setCenter(root1);
        cal.setVisible(true);
        amigos.setVisible(true);
        
    }

    @FXML
    private void VentanaAjuste(ActionEvent event) {
        loadFxmlCalendario("Ajuste");
    }

  

    

}
