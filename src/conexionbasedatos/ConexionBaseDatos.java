package conexionbasedatos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBaseDatos {

    public static void main(String[] args){
        
        Connection conexion = null;
        Statement manipular_db = null;
        String user_db = "root";
        String password_db = "";
        String url = "jdbc:mysql://localhost:3306/actividad_bd";
        
        try {
            
            conexion = DriverManager.getConnection(url, user_db, password_db);
            manipular_db = conexion.createStatement();
            System.out.println("Conexion exitosa");
        } catch (SQLException ex) {
            System.out.println("Error en la conexion: "+ ex.getMessage());
        }
        
        if (manipular_db != null){
            
            //insert
            String cedula = "203040";
            String nombres = "Catalina";
            String apellidos = "Jaramillo";
            String telefono = "2940202";
            String email= "cata@hola.com";
            
            
            
            
            try {
                int proceso = manipular_db.executeUpdate("INSERT INTO personas(cedula, nombres, apellidos, telefono, email) VALUES ('"+cedula+"','"+nombres+"','"+apellidos+"','"+telefono+"','"+email+"')");
            
                if (proceso == 1){
                    System.out.println("Se inserto con exito");
                }else {
                    System.out.println("Error al insertar");
                }
            } catch (SQLException ex) {
                System.out.println("Error al insertar: "+ ex.getMessage());
            }
            
            //UPDATE
            
            String nuevo_nombre = "francisco javier";
            String cedula_buscar = "108804";
            
            try {
                int proceso_update = manipular_db.executeUpdate("UPDATE personas SET nombres ='"+nuevo_nombre+"' WHERE cedula = '"+cedula_buscar+"'");
                if (proceso_update == 1){
                    System.out.println("Editado con exito");
                }else{
                    System.out.println("Error al editar");
                }
            } catch (SQLException ex) {
                System.out.println("Error al editar: "+ex.getMessage());
            }
            
            //SELECT
            
            ResultSet rs;
            
            try {
                
                rs = manipular_db.executeQuery("SELECT * FROM personas");
                rs.next();
                
                do{
                    System.out.println(rs.getInt("cedula") + " : " + rs.getString("nombres"));
                }while(rs.next());
            
            } catch (SQLException ex) {
                System.out.println("Error en la seleccion " + ex.getMessage());
            }
            
            //Delete
            
            String cedula_delete = "108804";
            
            try {
                int proceso_delete = manipular_db.executeUpdate("DELETE FROM personas WHERE cedula = '"+cedula_delete+"'");
                if (proceso_delete == 1){
                    System.out.println("Un usuario fue eliminado");
                }else{
                    System.out.println("Error al eliminar");
                }
            } catch (SQLException ex) {
                System.out.println("Error al eliminar: "+ex.getMessage());
            }
           
        }
    }
    
}
