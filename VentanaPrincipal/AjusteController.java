
package VentanaPrincipal;

import ConexionBdb.usuarios;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class AjusteController implements Initializable {
    usuarios mod;
    @FXML
    private Label txtNombre;
    @FXML
    private Label txtrol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   

    @FXML
    private void verinfo(ActionEvent event) {
        txtNombre.setText(mod.getUsuario());
        txtrol.setText(mod.getNombre_tipo());
    }
    
    
}
