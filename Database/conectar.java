package Database;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class conectar {
        
   Connection conectar=null;
public Connection conexion(){   
  try{
    Class.forName("org.gjt.mm.mysql.Driver");//.newInstance();
    conectar=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/conexion","root","");
    //conectar=DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/personas","luis","");
    
  }catch(SQLException | ClassNotFoundException ex) {
             
    }
  return conectar;
    }
}
