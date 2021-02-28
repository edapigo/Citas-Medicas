package VentanaPrincipal;

import ConexionBdb.usuarios;
import java.io.IOException;
import java.net.URL;
///////////////////////
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
///////////////////////////
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
///////////////////////////
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
////////////////////////////
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projectopoo1.FXMLLoginController;

public class MenuPrincipal implements Initializable {
     //variables
    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss a");
    String horaActual = formateador.format(LocalDateTime.now());

    int Meses = localDate.getMonthValue();
    int anio = localDate.getYear();
     private float xOffset = 0f;
    private float yOffset = 0f;

    usuarios mod;
    @FXML
    public VBox Menu;
    @FXML
    private Button agregar;
    @FXML
    private HBox Menusuper;
    @FXML
    private AnchorPane Panelajuste;
    @FXML
    private ColorPicker pikercolor;
    @FXML
    private ColorPicker pikercolor2;
    @FXML
    private AnchorPane Bienvenido;
    @FXML
    private AnchorPane Panelcalendario;
    @FXML
    private Label meses;
    @FXML
    private Label Anios;
    @FXML
    private Label ContadorHoras;
    @FXML
    private AnchorPane panelamigos;
    @FXML
    private Pane color1;
    @FXML
    private Pane color2;
    
    @FXML
    private GridPane calendario;
    
    private YearMonth AñoMesActual;
    
     private ArrayList<AnchorPaneNode> diasCalendario = new ArrayList<>(35);


    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        AñoMesActual = YearMonth.now();
        
        //Llena el GridPane con archor pane
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                AnchorPaneNode ap = new AnchorPaneNode();
                ap.setPrefSize(200,200);
                calendario.add(ap,j,i);
                diasCalendario.add(ap);
            }
        }
        //Función que actualiza el calendario
        actualizarCalendario(YearMonth.now());
        
        
        //reloj 
        meses(Meses);
        Anios.setText(anio + "");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(300);
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
        //ventanas
        Bienvenido.setVisible(true);
        Panelajuste.setVisible(false);
        Panelcalendario.setVisible(false);
        agregar.setVisible(false);
        panelamigos.setVisible(false);
        

    }

    @FXML
    private void calendario(ActionEvent event) {
        Panelcalendario.setVisible(true);
        Bienvenido.setVisible(false);
        Panelajuste.setVisible(false);
        agregar.setVisible(true);
        panelamigos.setVisible(false);

    }

    @FXML
    public void CerrarVentana() {
        System.exit(0);
    }

    @FXML
    private void Inicio() {
        Panelcalendario.setVisible(false);
        Panelajuste.setVisible(false);
        Bienvenido.setVisible(true);
        panelamigos.setVisible(false);
        agregar.setVisible(false);

    }
//ventanas menu
    @FXML
    private void VentanaAjuste(ActionEvent event) {
        Panelcalendario.setVisible(false);
        Panelajuste.setVisible(true);
        Bienvenido.setVisible(false);
        panelamigos.setVisible(false);
        agregar.setVisible(false);
    }

    @FXML
    private void VentanaAmigos(ActionEvent event) {
        panelamigos.setVisible(true);
        Panelcalendario.setVisible(false);
        Panelajuste.setVisible(false);
        Bienvenido.setVisible(false);
        agregar.setVisible(false);

    }
//cambio de colores
    @FXML
    private void cambiocolor(ActionEvent event) {
        Color selectcolor = pikercolor.getValue();
        color1.setBackground(new Background(new BackgroundFill(Paint.valueOf(selectcolor.toString()), CornerRadii.EMPTY, Insets.EMPTY)));
        Menu.setBackground(new Background(new BackgroundFill(Paint.valueOf(selectcolor.toString()), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @FXML
    private void cambiarcolor2(ActionEvent event) {
        Color selectcolor = pikercolor2.getValue();
        color2.setBackground(new Background(new BackgroundFill(Paint.valueOf(selectcolor.toString()), CornerRadii.EMPTY, Insets.EMPTY)));
        Menusuper.setBackground(new Background(new BackgroundFill(Paint.valueOf(selectcolor.toString()), CornerRadii.EMPTY, Insets.EMPTY)));
    }
//Fechas
    @FXML
    private void atrasMeses(MouseEvent event) {
        if (Meses <= 12) {
            Meses--;
            meses(Meses);
        }
        AñoMesActual = AñoMesActual.minusMonths(1);
        actualizarCalendario(AñoMesActual);
    }

    @FXML
    private void adelenteMes(MouseEvent event) {
        if (Meses <= 11) {
            Meses++;
            meses(Meses);
        }
        AñoMesActual = AñoMesActual.plusMonths(1);
        actualizarCalendario(AñoMesActual);
    }

    @FXML
    private void adelentaAnio(MouseEvent event) {
        anio++;
        Anios.setText(anio + "");
        AñoMesActual = AñoMesActual.plusYears(1);
        actualizarCalendario(AñoMesActual);
    }

    @FXML
    private void atrasAnio(MouseEvent event) {
        anio--;
        Anios.setText(anio + "");
        AñoMesActual = AñoMesActual.minusYears(1);
        actualizarCalendario(AñoMesActual);
    }
//calendario
    private void meses(int Meses) {
        switch (Meses) {
            case 1:
                meses.setText("Enero");

                break;
            case 2:
                meses.setText("Febrero");

                break;
            case 3:
                meses.setText("Marzo");

                break;
            case 4:
                meses.setText("Abril");

                break;
            case 5:
                meses.setText("Mayo");

                break;
            case 6:
                meses.setText("Junio");

                break;
            case 7:
                meses.setText("Julio");

                break;
            case 8:
                meses.setText("Agosto");

                break;
            case 9:
                meses.setText("Septiembre");

                break;
            case 10:
                meses.setText("Octubre");

                break;
            case 11:
                meses.setText("Noviembre");

                break;
            case 12:
                meses.setText("Diciembre");

                break;
        }
    }

    @FXML
    private void ventanaevento(ActionEvent event) {
         try {
             ResourceBundle bundle = ResourceBundle.getBundle("tips/java/cfg/lableText");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/VentanaPrincipal/eventos.fxml"),bundle);

            Parent root = loader.load();

            EventosController controlador = loader.getController();

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
            stage.showAndWait();

            stage.setOnCloseRequest(e -> controlador.CerrarVentana());

        } catch (IOException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void actualizarCalendario(YearMonth yearMonth) {
        // Obtiene el mes que vamos a empezar.
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        // Regresar los dias hasta que sea lunes, excepto si empieza en lunes
        while (!calendarDate.getDayOfWeek().toString().equals("MONDAY")) {
            calendarDate = calendarDate.minusDays(1);
        }
        // Actualizar el calendario con los dias
        for (AnchorPaneNode ap : diasCalendario) {
            if (ap.getChildren().size() != 0) {
                ap.getChildren().remove(0);
            }
            Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
            ap.setDate(calendarDate);
            ap.setTopAnchor(txt, 5.0);
            ap.setLeftAnchor(txt, 5.0);
            ap.getChildren().add(txt);
            calendarDate = calendarDate.plusDays(1);
        }
    }
}
