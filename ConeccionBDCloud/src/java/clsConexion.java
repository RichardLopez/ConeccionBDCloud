import java.sql.*;
import java.io.PrintWriter;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lopez
 */
public class clsConexion {
    Connection con = null;
    public Connection Conexion()
    {
 
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String db = "test";
            String usuario = "root";
            String Pass = "root";
            String server = "jdbc:mysql://localhost/" + db ;
            con = DriverManager.getConnection(server, usuario, Pass);
       
        }
        catch(Exception e)
        {
          System.out.println(e.getMessage());
        }
    return con;
    }
    
}
