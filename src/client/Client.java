package client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Client {
	public static void afegirClient(RegisteClient client,Connection con) throws SQLException {
		Statement stmt=con.createStatement(0,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs=stmt.executeQuery("SELECT * FROM clients WHERE dni='"+client.dni+"'");
        if(!rs.next()) {
        	rs.moveToInsertRow();
        	rs.updateString("dni", client.dni);
        	rs.updateString("contrasenya", client.contrasenya);
        	rs.updateString("nom", client.nom);
        	rs.updateString("correu_electronic", client.correu);
        	rs.updateInt("telefon", client.telefon);
        	rs.updateString("adreca", client.adreca);
        	rs.insertRow();
        	System.out.println("Client insertat correctament");
        } else System.out.println("El dni del client ja existeix");
	}
	public static void modificarCorreu(String dni,String correu,Connection con) throws SQLException{
		Statement stmt=con.createStatement(0,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs=stmt.executeQuery("SELECT * FROM clients WHERE dni='"+dni+"'");
        rs.first();
        rs.updateString("correu_electronic", correu);
        rs.updateRow();
	}
	public static void modificarTelefon(String dni,int telefon,Connection con) throws SQLException{
		Statement stmt=con.createStatement(0,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs=stmt.executeQuery("SELECT * FROM clients WHERE dni='"+dni+"'");
        rs.first();
        rs.updateInt("telefon", telefon);
        rs.updateRow();
	}
	public static void modificarAdreca(String dni,String adreca,Connection con) throws SQLException{
		Statement stmt=con.createStatement(0,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs=stmt.executeQuery("SELECT * FROM clients WHERE dni='"+dni+"'");
        rs.first();
        rs.updateString("adreca", adreca);
        rs.updateRow();
	}
	public static String llistarDadesClient(String dni,Connection con) throws SQLException{
		Statement stmt=con.createStatement(0,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs=stmt.executeQuery("SELECT * FROM clients WHERE dni='"+dni+"'");
        String frase="";
       if(rs.next()){
    	   rs.previous();
        	while(rs.next()) {
        		frase=frase+rs.getString("dni")+" - "+rs.getString("nom")+" - "
        		+rs.getString("correu_electronic")+" - "+rs.getInt("telefon")+" - "
        		+ rs.getString("adreca");
        	}
        }else frase=frase+"El dni no existeix a la base de dades";
        return frase;
        
	}
	public static String llistarDadesClient(Connection con) throws SQLException{
		Statement stmt=con.createStatement(0,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs=stmt.executeQuery("SELECT * FROM clients");
        String frase="";
        	while(rs.next()) {
        		frase=frase+rs.getString("dni")+" - "+rs.getString("nom")+" - "
        		+rs.getString("correu_electronic")+" - "+rs.getInt("telefon")+" - "
        		+ rs.getString("adreca")+"\n";
        	}
        return frase;
        
	}
	public static boolean BorrarClient(String dni,String contrasenya,Connection con) throws SQLException{
		Statement stmt=con.createStatement(0,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs=stmt.executeQuery("SELECT * FROM clients WHERE dni='"+dni+"' and contrasenya='"+contrasenya+"'");
        if(!rs.next()) return false;
        else {
        	rs.deleteRow();
        	return true;
        }
	}
	public static boolean loginClient(String dni,String contrasenya,Connection con) throws SQLException{
		Statement stmt=con.createStatement(0,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs=stmt.executeQuery("SELECT * FROM clients WHERE dni='"+dni+"' and contrasenya='"+contrasenya+"'");
        if(!rs.next()) return false;
        else return true;
	}
	public static boolean validarDNI(String dni) {
		if(dni.length()==9) {
			int num = 0;
			while(Character.isDigit(dni.charAt(num))&&num<9) {
				num++;
			}
			if(num==8) {
				String codiValidacioDni="TRWAGMYFPDXBNJZSQVHLCKE";
				char lletraDniStr = dni.charAt(8);
				lletraDniStr=Character.toUpperCase(lletraDniStr);
				int buscarLletraDni=0;
				while(buscarLletraDni<codiValidacioDni.length()&&lletraDniStr!=codiValidacioDni.charAt(buscarLletraDni)) {
					buscarLletraDni++;
				}
				char lletraDniCorrecte=codiValidacioDni.charAt(buscarLletraDni);
				if(lletraDniCorrecte==lletraDniStr)return true;	
				else return false;
			}else return false;	
		}else return false;				
	}
	public static boolean validarTelefon(String telefon) {
		if(telefon.length()==9) {
			int num = 0;
			while(Character.isDigit(telefon.charAt(num))&&num<8) {
				num++;
			}
			if(num==8)return true;
			else return false;
		}
		else return false;
	}
	public static boolean validarCorreu(String correu) {
		String[] dadesCorreuArroba = correu.split("@");
		if(dadesCorreuArroba.length==2) {
			String[] dadesCorreuPunt = dadesCorreuArroba[1].split("\\.");
			if(dadesCorreuPunt.length==2) return true;
			else return false;	
		}else return false;
	}
	public static boolean validarContrasenya(String contrasenya1,String contrasenya2) {
		if(contrasenya1.equals(contrasenya2)&&contrasenya1.length()>8)return true;
		else return false;
	}
}
