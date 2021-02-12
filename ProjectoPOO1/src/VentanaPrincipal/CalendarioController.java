package VentanaPrincipal;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class CalendarioController implements Initializable {

    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss a");
    String horaActual = formateador.format(LocalDateTime.now());

    int Meses = localDate.getMonthValue();
    int anio = localDate.getYear();

    @FXML
    private Label meses;
    @FXML
    private Label Anios;
    @FXML
    private Pane panel29;
    @FXML
    private Pane panel30;
    @FXML
    private Pane panel31;
    @FXML
    private Label ContadorHoras;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        meses(Meses);
        Anios.setText(anio + "");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(200);
                        Platform.runLater(() -> {
                            ContadorHoras.setText(formateador.format(LocalDateTime.now()));
                        });

                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
    }

    private void meses(int Meses) {
        switch (Meses) {
            case 1:
                meses.setText("Enero");
                panel29.setDisable(false);
                panel30.setDisable(false);
                panel31.setDisable(false);
                break;
            case 2:
                meses.setText("Febrero");
                panel29.setDisable(true);
                panel30.setDisable(true);
                panel31.setDisable(true);
                break;
            case 3:
                meses.setText("Marzo");
                panel29.setDisable(false);
                panel30.setDisable(false);
                panel31.setDisable(false);
                break;
            case 4:
                meses.setText("Abril");
                panel29.setDisable(false);
                panel30.setDisable(false);
                panel31.setDisable(true);
                break;
            case 5:
                meses.setText("Mayo");
                panel29.setDisable(false);
                panel30.setDisable(false);
                panel31.setDisable(true);
                break;
            case 6:
                meses.setText("Junio");
                panel29.setDisable(false);
                panel30.setDisable(false);
                panel31.setDisable(true);
                break;
            case 7:
                meses.setText("Julio");
                panel29.setDisable(false);
                panel30.setDisable(false);
                panel31.setDisable(false);
                break;
            case 8:
                meses.setText("Agosto");
                panel29.setDisable(false);
                panel30.setDisable(false);
                panel31.setDisable(false);
                break;
            case 9:
                meses.setText("Septiembre");
                panel29.setDisable(false);
                panel30.setDisable(false);
                panel31.setDisable(true);
                break;
            case 10:
                meses.setText("Octubre");
                panel29.setDisable(false);
                panel30.setDisable(false);
                panel31.setDisable(false);
                break;
            case 11:
                meses.setText("Noviembre");
                panel29.setDisable(false);
                panel30.setDisable(false);
                panel31.setDisable(true);
                break;
            case 12:
                meses.setText("Diciembre");
                panel29.setDisable(false);
                panel30.setDisable(false);
                panel31.setDisable(false);
                break;
        }
    }

    @FXML
    private void adelenteMes(MouseEvent event) {
        if (Meses <= 11) {
            Meses++;
            meses(Meses);
        }

    }

    @FXML
    private void atrasMeses(MouseEvent event) {
        if (Meses <= 12) {
            Meses--;
            meses(Meses);
        }
    }

    @FXML
    private void adelentaAnio(MouseEvent event) {
        anio++;
        Anios.setText(anio + "");
    }

    @FXML
    private void atrasAnio(MouseEvent event) {
        anio--;
        Anios.setText(anio + "");
    }

}
