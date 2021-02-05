package ConexionBdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class conexionBDB {

    public final String base = "datos";
    public final String user = "root";
    public final String password = "";
    public final String url = "jdbc:mysql://localhost:3308/" + base;
    public Connection con = null;

    public Connection getConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(conexionBDB.class.getName()).log(Level.SEVERE, null, ex);
        }
           

        return con;
    }

}
