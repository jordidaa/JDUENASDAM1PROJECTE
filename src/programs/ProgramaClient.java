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
			String opcioMenu="";
			int opcioMenuInt=0;
			do {
				Client client=new Client();
				System.out.println(menuPrincipal());
				opcioMenu=lector.nextLine();
				while(!client.validarOpcioMenu(opcioMenu)) {
					System.out.println("L'opcio no pot contindre lletres i nomes pot tenir un caracter");
					System.out.println(menuPrincipal());
					opcioMenu=lector.nextLine();
				}
				opcioMenuInt=Integer.parseInt(opcioMenu);
				
				switch(opcioMenuInt) {
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
						String opcioMenuLogin="";
						int opcioMenuLoginInt=0;
						if(client.loginClient(dni,contrasenya,con)) {
							do {
								System.out.println(menuLogin());
								opcioMenuLogin=lector.nextLine();
								lector.nextLine();
								while(!client.validarOpcioMenu(opcioMenuLogin)) {
									System.out.println("L'opcio no pot contindre lletres i nomes pot tenir un caracter");
									System.out.println(menuLogin());
									opcioMenuLogin=lector.nextLine();
								}
								opcioMenuLoginInt=Integer.parseInt(opcioMenuLogin);
								switch(opcioMenuLoginInt) {
									case 1:
										String opcioMenuModificar="";
										int opcioMenuModificarInt=0;
										do {
											System.out.println(menuModificar());
											opcioMenuModificar=lector.nextLine();
											lector.nextLine();
											while(!client.validarOpcioMenu(opcioMenuModificar)) {
												System.out.println("L'opcio no pot contindre lletres i nomes pot tenir un caracter");
												System.out.println(menuModificar());
												opcioMenuModificar=lector.nextLine();
											}
											opcioMenuModificarInt=Integer.parseInt(opcioMenuModificar);
											switch(opcioMenuModificarInt) {
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
										}while(opcioMenuModificarInt!=4);
										break;
									case 2:
										System.out.println("Contrasenya");
										contrasenya=lector.nextLine();
										if(client.BorrarClient(dni, contrasenya, con)) {
											System.out.println("Usuari borrat");
											opcioMenuLoginInt=4;
										}else System.out.println("Contrasenya incorrecte");
										break;
									case 3:
										break;
									case 4:
										break;
								}
							}while(opcioMenuLoginInt!=4);
						}else System.out.println("Usuari o contrasenya incorrecte");
						break;
					case 3:
						break;
				}
			}while(opcioMenuInt!=3);
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
