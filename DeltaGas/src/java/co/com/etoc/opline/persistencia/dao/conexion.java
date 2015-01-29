/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aimagenes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Trucos Jen <Jen at jenjen172009@gmail.com>
 */
public class conexion {
    private Connection conexion=null ;
    /**
    * Método utilizado para recuperar el valor del atributo conexion
    * @return conexion contiene el estado de la conexión
    *
    */
    public Connection getConexion()
    {
       return conexion;
    }
    
    /**
    * Método utilizado para establecer la conexión con la base de datos
    * @return estado regresa el estado de la conexión, true si se estableció la conexión,
    * falso en caso contrario
    */
    public boolean crearConexion()
    {
       try {
            Class.forName("com.mysql.jdbc.Driver");         
          conexion = DriverManager.getConnection("jdbc:mysql://localhost/estrella",
                    "java",
                    "java");
          if(conexion!=null){
              return true;
          }
       }
       catch (SQLException ex) {
          System.out.println("error en conexion: "+ex);
       }
       catch (ClassNotFoundException ex) {
          System.out.println(ex);
       }
       return false;
    }

    /**
    *
    *Método utilizado para realizar las instrucciones: INSERT, DELETE y UPDATE
    *@param sql Cadena que contiene la instrucción SQL a ejecutar
    *@return estado regresa el estado de la ejecución, true(éxito) o false(error)
    *
    */
    public boolean ejecutarSQL(String sql)
    {
       try {
           PreparedStatement sentencia = conexion.prepareStatement(sql);
          sentencia.execute(sql);
          return true;
       } catch (SQLException ex) {
            return false;
       }
    }

    /**
    *
    *Método utilizado para realizar la instrucción SELECT
    *@param sql Cadena que contiene la instrucción SQL a ejecutar
    *@return resultado regresa los registros generados por la consulta
    *
    */
    public ResultSet ejecutarSQLSelect(String sql)
    {
       ResultSet resultado;
       try {
          PreparedStatement sentencia = conexion.prepareStatement(sql);
          resultado = sentencia.executeQuery();
          return resultado;
       } catch (SQLException ex) {
          System.err.println("Error "+ex);
          return null;
       }
    }
}
