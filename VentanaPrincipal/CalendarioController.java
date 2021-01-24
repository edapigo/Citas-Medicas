package VentanaPrincipal;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CalendarioController implements Initializable {

    
    
    @FXML
    private Label meses;
    @FXML
    private Label Anios;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        meses.setText(NombreMess());
        Anios.setText(toString(anios()));
    }

    @FXML
    private void eventos(MouseEvent event) {
        System.out.println("dale hp");
    }

    private String NombreMess() {
        Month mes = LocalDate.now().getMonth();
        String nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        String primeraLetra = nombre.substring(0, 1);
        String mayuscula = primeraLetra.toUpperCase();
        String demasLetras = nombre.substring(1, nombre.length());
        nombre = mayuscula + demasLetras;

        return nombre;
    }

    private int anios() {
        Date d = new Date();
        int year =1900+d.getYear();
        return year;
    }

    private String toString(int anios) {
      return String.valueOf(anios);
    }

}
