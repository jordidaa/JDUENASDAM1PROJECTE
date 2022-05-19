package empresa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Empresa {
	public static boolean validarCodiProducte(String codiProducte) {
		if(codiProducte.length()==4) {
			int numLletres=0;
			int numNumeros=0;
			int mirarNums=0;
			while(mirarNums<codiProducte.length()) {
				if(Character.isDigit(codiProducte.charAt(mirarNums))) {
					numNumeros++;
				}else numLletres++;
				mirarNums++;
			}
			if(numLletres==2&&numNumeros==2) {
				return true;
			}else return false;			
		}else return false;
	}
	public static void afegirProducte(RegisteProducte rProducte,Connection con) throws SQLException {
		Statement stmt=con.createStatement(0,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs=stmt.executeQuery("SELECT * FROM productes WHERE codi='"+rProducte.codi+"'");
        if(!rs.next()) {
        	rs.moveToInsertRow();
        	rs.updateString("codi", rProducte.codi);
        	rs.updateString("nom", rProducte.nom);
        	rs.updateInt("stock", rProducte.stock);
        	rs.updateDouble("preu", rProducte.preu);
        	rs.updateInt("iva", rProducte.iva);
        	rs.updateInt("unitats_venudes", 0);
        	rs.insertRow();
        	System.out.println("Producte insertat correctament");
        } else System.out.println("El codi del producte ja existeix");
        
	}
	public static boolean buscarProducte(String codiProducte,Connection con) throws SQLException {
		Statement stmt=con.createStatement(0,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs=stmt.executeQuery("SELECT * FROM productes WHERE codi='"+codiProducte+"'");
        if(rs.next()) {
        	return true;
        } else return false;
        
	}
	public static void afegirStock(String stock,String codiProducte,Connection con) throws SQLException {
		Statement stmt=con.createStatement(0,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs=stmt.executeQuery("SELECT * FROM productes WHERE codi='"+codiProducte+"'");
        rs.first();
        rs.updateInt("stock", rs.getInt("stock")+Integer.parseInt(stock));
        rs.updateRow();
	}
	public static void modificarPreu(String preu,String codiProducte,Connection con) throws SQLException {
		Statement stmt=con.createStatement(0,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs=stmt.executeQuery("SELECT * FROM productes WHERE codi='"+codiProducte+"'");
        rs.first();
        rs.updateDouble("preu", Double.parseDouble(preu));
        rs.updateRow();
	}
	public static void modificarIva(String iva,String codiProducte,Connection con) throws SQLException {
		Statement stmt=con.createStatement(0,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs=stmt.executeQuery("SELECT * FROM productes WHERE codi='"+codiProducte+"'");
        rs.first();
        rs.updateInt("iva", Integer.parseInt(iva));
        rs.updateRow();
	}
	public static boolean borrarProducte(String codiProducte,Connection con) throws SQLException{
		Statement stmt=con.createStatement(0,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs=stmt.executeQuery("SELECT * FROM productes WHERE codi='"+codiProducte+"'");
        if(!rs.next()) return false;
        else {
        	rs.deleteRow();
        	return true;
        }
	}
	public static String llistarProductes(Connection con) throws SQLException{
		Statement stmt=con.createStatement(0,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs=stmt.executeQuery("SELECT * FROM productes");
        String frase="\n";
        frase=frase+"CODI"+"|"+String.format("%-40s","NOM")+"|"
               		+"Preu Sense Iva"+"|"+"Iva"+"|"
               		+"Cost Iva"+"|"+"Preu amb Iva"+"\n";
        while(rs.next()) {        	
        	if(rs.getInt("stock")!=0) {
        		double preu=rs.getDouble("preu");
        		int iva=rs.getInt("iva");
        		double calculIva=(preu*iva)/100;
        		frase=frase+rs.getString("codi")+"|"+String.format("%-40s", rs.getString("nom"))+"|"
        			+String.format("%-14s", String.format("%.2f",preu)+"€")+"|"+rs.getInt("iva")+"%"+"|"
        			+String.format("%-8s",String.format("%.2f",calculIva)+"€")+"|"
        			+String.format("%-8s",String.format("%.2f",((calculIva+rs.getDouble("preu"))))+"€")+"\n";
        	}
    		
    	}
        return frase;
	}
}
