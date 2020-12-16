package Registro;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projectopoo1.FXMLLoginController;


public class FXMLRegistroController implements Initializable {
    @FXML
    private float xOffset = 0f;
    private float yOffset = 0f;
    @FXML
    private Button btn;
   
   
    //Metodos
    
    /*public void ingresar(ActionEvent e){
        conectar cc=new conectar();
        Connection cn=cc.conexion();
        try{
            PreparedStatement pst=cn.prepareStatement("INSERT INTO idconexion(email,nombre,contra) VALUES(?,?,?)");
            pst.setString(1,email.getText());
            pst.setString(2,user.getText());
            pst.setString(3,pass.getText());
            

        
        int a=pst.executeUpdate();
        if(a>0){
            System.out.println("Registro exitoso");
             mostrardatos("");
        }
        else{
             System.out.println("Error al agregar");
        }
        }catch(Exception ee){
        }        
    }*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    public void CerrarVentana() {
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
    
  

}
