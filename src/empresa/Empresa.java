package empresa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import botiga.RegistreFactura;
import botiga.RegistreLiniaFactura;


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
	public static void treureStock(String stock,String codiProducte,Connection con) throws SQLException {
		Statement stmt=con.createStatement(0,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs=stmt.executeQuery("SELECT * FROM productes WHERE codi='"+codiProducte+"'");
        rs.first();
        rs.updateInt("stock", rs.getInt("stock")-Integer.parseInt(stock));
        rs.updateRow();
	}
	public static int buscarStock(String codiProducte,Connection con) throws SQLException {
		Statement stmt=con.createStatement(0,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs=stmt.executeQuery("SELECT * FROM productes WHERE codi='"+codiProducte+"'");
        rs.first();
        int stockActual= rs.getInt("stock");
        rs.updateRow();
        return stockActual; 
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
        			+String.format("%-14s", String.format("%.2f",preu)+"�")+"|"+String.format("%-3s",rs.getInt("iva")+"%")+"|"
        			+String.format("%-8s",String.format("%.2f",calculIva)+"�")+"|"
        			+String.format("%-8s",String.format("%.2f",((calculIva+rs.getDouble("preu"))))+"�")+"\n";
        	}    		
    	}
        return frase;
	}
	public static int numeroFactura(Connection con) throws SQLException{
		Statement stmt=con.createStatement(0,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs=stmt.executeQuery("SELECT num_factura FROM factura ORDER BY num_factura desc LIMIT 1");
        int numFacturaActual=0;
        if(rs.next()) {
        	numFacturaActual=rs.getInt("num_factura")+1;
        }else numFacturaActual=1;
        return numFacturaActual;
	}
	public static RegisteProducte dadesProducte(Connection con,String codiProducte) throws SQLException {
		RegisteProducte rProducte=new RegisteProducte();
		Statement stmt=con.createStatement(0,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs=stmt.executeQuery("SELECT * FROM productes WHERE codi='"+codiProducte+"'");
        if(rs.next()) {
        	rProducte.codi=rs.getString("codi");
        	rProducte.iva=rs.getInt("iva");
        	rProducte.preu=rs.getDouble("preu");
        	rProducte.nom=rs.getString("nom");
        }
        return rProducte;
	}
	public static boolean afegirProducteAFactura(Connection con,RegistreFactura rfactura,int quantitat,String codiProducte) throws SQLException {
		if((buscarStock(codiProducte, con)-quantitat)>0) {
			RegistreLiniaFactura rLiniaFactura=new RegistreLiniaFactura();
			RegisteProducte rProducte=dadesProducte(con,codiProducte);
			rLiniaFactura.codi=rProducte.codi;
			rLiniaFactura.nom=rProducte.nom;
			rLiniaFactura.iva=rProducte.iva;
			rLiniaFactura.preu=rProducte.preu;
			rLiniaFactura.quantitat=quantitat;
			rLiniaFactura.numFactura=rfactura.numFactura;
			rLiniaFactura.numLinia=rfactura.liniaFactura.size()+1;
			rfactura.liniaFactura.add(rLiniaFactura);
			return true;
		}else return false;
	}
	public static String veureFacturaPerPantalla(RegistreFactura rfactura) {
		String frase="";
		frase=frase+"-----DADES EMPRESA----- \n";
		frase=frase+"Numero de factura: "+rfactura.numFactura+"\n";
		frase=frase+"Data de factura: "+rfactura.dataFactura.getDayOfMonth()+"/"
		+rfactura.dataFactura.getMonthValue()+"/"+rfactura.dataFactura.getYear()+"\n";
		frase=frase+"-----DADES CLIENT----- \n";
		frase=frase+"Dni: "+rfactura.rClient.dni+"\n";
		frase=frase+"Nom: "+rfactura.rClient.nom+"\n";
		frase=frase+"Telefon: "+rfactura.rClient.telefon+"\n";
		frase=frase+"Correu Electronic: "+rfactura.rClient.correu+"\n";
		frase=frase+"Adreca: "+rfactura.rClient.adreca+"\n";
		frase=frase+"-----PRODUCTES----- \n";
		frase=frase+"CODI"+"|"+String.format("%-40s","NOM")+"|"+"Quantitat"+"|"
           		+"Preu Sense Iva"+"|"+"Iva"+"|"
           		+"Cost Iva"+"|"+"Preu amb Iva"+"\n";
		double totalPreu=0;
		double totalIva=0;
		for(int i=0;i<rfactura.liniaFactura.size();i++) {
			totalPreu=totalPreu+rfactura.liniaFactura.get(i).preu*rfactura.liniaFactura.get(i).quantitat;
			double calculIva=((rfactura.liniaFactura.get(i).preu*rfactura.liniaFactura.get(i).quantitat)*rfactura.liniaFactura.get(i).iva)/100;
			totalIva=totalIva+calculIva;
			frase=frase+rfactura.liniaFactura.get(i).codi+"|"+String.format("%-40s",rfactura.liniaFactura.get(i).nom)+"|"
					+String.format("%-9s",rfactura.liniaFactura.get(i).quantitat)+"|"
					+String.format("%-14s", String.format("%.2f",rfactura.liniaFactura.get(i).preu)+"�")+"|"
					+String.format("%-3s",rfactura.liniaFactura.get(i).iva+"%")+"|"
	           		+String.format("%-8s",String.format("%.2f",calculIva)+"�")+"|"
					+String.format("%-8s",String.format("%.2f",(calculIva+(rfactura.liniaFactura.get(i).preu*rfactura.liniaFactura.get(i).quantitat)))+"�")+"\n";
		}
		frase=frase+"-----TOTALS----- \n";
		frase=frase+"Total sense iva: "+String.format("%.2f",totalPreu)+"� \n";
		frase=frase+"Total iva: "+String.format("%.2f",totalIva)+"� \n";
		frase=frase+"Total amb iva: "+String.format("%.2f",(totalIva+totalPreu))+"� \n";
		return frase;
	}
	
}
