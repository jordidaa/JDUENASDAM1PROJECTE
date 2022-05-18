package programs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import client.*;

public class ProgramaClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/botiga","postgres","Jd230103");
			Scanner lector = new Scanner(System.in);
			int opcioMenu=0;
			do {
				System.out.println(menuPrincipal());
				opcioMenu=lector.nextInt();
				lector.nextLine();
				Client client=new Client();
				switch(opcioMenu) {
					case 1:						
						RegisteClient rClient=new RegisteClient();
						System.out.println("DNI");
						String dni=lector.nextLine();
						while(!client.validarDNI(dni)) {
							System.out.println("DNI incorecte torna l a posar");
							dni=lector.nextLine();
						}
						rClient.dni=dni;
						System.out.println("Contrasenya (Minim 8 caracters)");
						String contrasenya=lector.nextLine();
						System.out.println("Repeteix la Contrasenya");
						String contrasenyaRp=lector.nextLine();
						while(!client.validarContrasenya(contrasenya, contrasenyaRp)) {
							System.out.println("Les contresenyes no coincideixen o es masa curta, torna la a posar");
							contrasenya=lector.nextLine();
							System.out.println("Repeteix la Contrasenya");
							contrasenyaRp=lector.nextLine();
						}
						rClient.contrasenya=contrasenya;
						System.out.println("Nom");
						rClient.nom=lector.nextLine();
						System.out.println("Correu Electronic");
						String correu=lector.nextLine();
						while(!client.validarCorreu(correu)) {
							System.out.println("Correu Electronic no valid torna l a posar");
							correu=lector.nextLine();
						}
						rClient.correu=correu;
						System.out.println("Numero de telefon");
						String telefon=lector.nextLine();
						while(!client.validarTelefon(telefon)) {
							System.out.println("Numero de telefon no valid torna l a posar");
							telefon=lector.nextLine();
						}
						rClient.telefon=Integer.parseInt(telefon);
						System.out.println("Adreca");
						rClient.adreca=lector.nextLine();
						client.afegirClient(rClient, con);
						break;
					case 2:
						System.out.println("DNI");
						dni=lector.nextLine();
						System.out.println("Contrasenya");
						contrasenya=lector.nextLine();
						int opcioMenuLogin=0;
						if(client.loginClient(dni,contrasenya,con)) {
							do {
								System.out.println(menuLogin());
								opcioMenuLogin=lector.nextInt();
								lector.nextLine();
								switch(opcioMenuLogin) {
									case 1:
										int opcioMenuModificar=0;
										do {
											System.out.println(menuModificar());
											opcioMenuModificar=lector.nextInt();
											lector.nextLine();
											switch(opcioMenuModificar) {
												case 1:
													System.out.println("Correu Electronic");
													correu=lector.nextLine();
													if(client.validarCorreu(correu)) {
														client.modificarCorreu(dni, correu, con);
														System.out.println("Correu Electronic modificat correctament");
													}else System.out.println("Correu Electronic no valid");
													break;
												case 2:
													System.out.println("Numero de telefon");
													telefon=lector.nextLine();
													if(client.validarTelefon(telefon)) {
														System.out.println("Numero de telefon modificat correctament");
														client.modificarTelefon(dni, Integer.parseInt(telefon), con);
													}else System.out.println("Numero de telefon no valid");
													break;
												case 3:
													System.out.println("Adreca");
													String adreca=lector.nextLine();
													client.modificarAdreca(dni, adreca, con);
													System.out.println("Adreca modificada correctament");
													break;
												case 4:
													break;
											}
										}while(opcioMenuModificar!=4);
										break;
									case 2:
										System.out.println("Contrasenya");
										contrasenya=lector.nextLine();
										if(client.BorrarClient(dni, contrasenya, con)) {
											System.out.println("Usuari borrat");
											opcioMenuLogin=4;
										}else System.out.println("Contrasenya incorrecte");
										break;
									case 3:
										break;
									case 4:
										break;
								}
							}while(opcioMenuLogin!=4);
						}else System.out.println("Usuari o contrasenya incorrecte");
						break;
					case 3:
						break;
				}
			}while(opcioMenu!=3);
			lector.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String menuPrincipal() {
		String menu="1.Alta client\n2.Login\n3.Sortir";
		return menu;
	}
	public static String menuLogin() {
		String menu="1.Modificar client\n2.Borrar Client\n3.Comprar\n4.Sortir";
		return menu;
	}
	public static String menuModificar() {
		String menu="1.Modificar correu\n2.Modificar telefon\n3.Modificar adreca\n4.Sortir";
		return menu;
	}

}
