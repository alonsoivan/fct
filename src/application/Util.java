package application;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Util {
	
	public static void modificarProveedor(Proveedor proveedor) {
		Connection conexion = null;
	    PreparedStatement ps;
		try {
			conexion = DriverManager.getConnection ("jdbc:mysql://localhost:3306/cesadb", "root", "");
			ps = conexion.prepareStatement("UPDATE prov_comp SET RAZ_PROVEEDOR = ?, REG_NOTARIAL = ?, SEG_RESPONSABILIDAD = ?, SEG_IMPORTE = ?, FEC_HOMOLOGACION = ? WHERE CIF_PROVEEDOR = ? ;");
			
			ps.setString(1, proveedor.getRazon());
			ps.setInt(2, proveedor.getRegNotarial());
			ps.setString(3,proveedor.getSegResponsabilidad());
			ps.setFloat(4,proveedor.getSegImporte());
			ps.setDate(5, proveedor.getFecHomologacion());
			ps.setString(6, proveedor.getCif());
			
			conexion.setAutoCommit(false);
			int regis = ps.executeUpdate();
			 
			System.out.println(regis+" registros insertados.");
			 
			conexion.commit();
			conexion.setAutoCommit(true);
			
			conexion.close();
			ps.close();
		} catch (SQLException e) {
			try {
				System.out.println("Error al hacer el update.");
				conexion.rollback();
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	

	public static void insertarProveedor(Proveedor proveedor) {
		Connection conexion = null;
	    PreparedStatement ps;
		try {
			conexion = DriverManager.getConnection ("jdbc:mysql://localhost:3306/cesadb", "root", "");
			ps = conexion.prepareStatement("INSERT INTO prov_comp VALUES(?,?,?,?,?,?)");
			
			ps.setString(1, proveedor.getCif());
			ps.setString(2, proveedor.getRazon());
			ps.setInt(3, proveedor.getRegNotarial());
			ps.setString(4,proveedor.getSegResponsabilidad());
			ps.setFloat(5,proveedor.getSegImporte());
			ps.setDate(6, proveedor.getFecHomologacion());
			 
			conexion.setAutoCommit(false);
			int regis = ps.executeUpdate();
			 
			System.out.println(regis+" registros insertados.");
			 
			conexion.commit();
			conexion.setAutoCommit(true);
			
			conexion.close();
			ps.close();
		} catch (SQLException e) {
			try {
				System.out.println("Error al hacer el insert.");
				conexion.rollback();
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public static void eliminarProveedor(Proveedor proveedor) {
		Connection conexion = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection ("jdbc:mysql://localhost:3306/cesadb", "root", "");
								
			conexion.setAutoCommit(false);
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM prov_comp WHERE CIF_PROVEEDOR = ? ;");

		    ps.setString(1, proveedor.getCif());
		    ps.executeUpdate();
		    
		    conexion.commit();
		    conexion.setAutoCommit(true);
			
			conexion.close();  // Cerrar conexión
			ps.close();
			
		} catch (SQLException e) {
			try {
				conexion.rollback();
				conexion.close();
			} catch (SQLException e1) {e1.printStackTrace();}
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	public static ArrayList<Proveedor> getProveedores() {
		
		ArrayList<Proveedor> proveedores = new ArrayList<>();
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost:3306/cesadb", "root", "");
								
			Statement sentencia = conexion.createStatement();
			ResultSet cursor = sentencia.executeQuery("SELECT * FROM prov_comp");
			
			while (cursor.next()) {

				Proveedor proveedor = new Proveedor(cursor.getString(1),
													cursor.getString(2),
													cursor.getInt(3), 
													cursor.getString(4), 
													cursor.getFloat(5),
													cursor.getDate(6));
			
			    proveedores.add(proveedor);
			}
			
			cursor.close();     // Cerrar ResultSet
			sentencia.close(); // Cerrar Statement
			conexion.close();  // Cerrar conexión
			
		}
		catch (ClassNotFoundException cn) {cn.printStackTrace();} 
		catch (SQLException e) {e.printStackTrace();}
		
		return proveedores;
	}
}
