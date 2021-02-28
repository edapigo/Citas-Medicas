
package Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class sqlEvento extends conexionBDB{
    public boolean registrarEvento(evento eventos) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO eventos_registro (tema_evento, descripcion, fecha ,categoria, subcategoria ) VALUES(?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,eventos.getTema_evento());
            ps.setString(2,eventos.getDescripcion());
            ps.setDate(3,Date.valueOf(eventos.getFecha()));
            ps.setString(4, eventos.getCategoria());
            ps.setString(5, eventos.getSubcategoria());
            ps.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    public int existeventohora(String fecha) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT count(ideventos) FROM eventos_registro WHERE fecha = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setDate(1,Date.valueOf(fecha));
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }

    }
}
