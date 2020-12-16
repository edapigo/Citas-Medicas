
package Registro;

public class RegistrosUser {
    public String ususario ;
    public String correo;
    public String contra;

    public String getUsusario() {
        return ususario;
    }

    public void setUsusario(String ususario) {
        this.ususario = ususario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
    public RegistrosUser(String user , String contrase,String correo){
        this.ususario = user;
        this.contra = contrase;
        this.correo = correo;
    }
}
