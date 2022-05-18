package programs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import client.*;
import funcionsGenerals.*;

public class ProgramaEmpresa {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/botiga","postgres","Jd230103");
			Scanner lector = new Scanner(System.in);
			String opcioMenu="";
			int opcioMenuInt=0;
			do {
				System.out.println(menuPrincipal());
				opcioMenu=lector.nextLine();
				while(!FuncionsGenerals.validarOpcioMenu(opcioMenu)) {
					System.out.println("L'opcio no pot contindre lletres i nomes pot tenir un caracter");
					System.out.println(menuPrincipal());
					opcioMenu=lector.nextLine();
				}
				opcioMenuInt=Integer.parseInt(opcioMenu);
				switch(opcioMenuInt) {
					case 1:
						String opcioMenuClient="";
						int opcioMenuClientInt=0;
						do {
							System.out.println(menuClients());
							opcioMenuClient=lector.nextLine();
							while(!FuncionsGenerals.validarOpcioMenu(opcioMenuClient)) {
								System.out.println("L'opcio no pot contindre lletres i nomes pot tenir un caracter");
								System.out.println(menuClients());
								opcioMenuClient=lector.nextLine();
							}
							opcioMenuClientInt=Integer.parseInt(opcioMenuClient);
							switch(opcioMenuClientInt) {
								case 1:
									System.out.println("DNI");
									String dni=lector.nextLine();
									if(Client.validarDNI(dni)) {
										System.out.println(Client.llistarDadesClient(dni, con));
									}else System.out.println("Dni no valid");
									break;
								case 2:
									System.out.println(Client.llistarDadesClient(con));
									break;
								case 3:
									break;
							}
						}while(opcioMenuClientInt!=3);
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						break;
				}
			}while(opcioMenuInt!=4);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static String menuPrincipal() {
		String menu="1.Clients\n2.Productes\n3.Facturacio\n4.Sortir";
		return menu;
	}
	public static String menuClients() {
		String menu="1.Visualitzar Dades 1 Client\n2.Llistar Clients\n3.Sortir";
		return menu;
	}
}
