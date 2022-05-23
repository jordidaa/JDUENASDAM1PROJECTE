package programs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import botiga.RegistreFactura;
import client.*;
import empresa.*;
import funcionsGenerals.*;

public class ProgramaClient {

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
						RegisteClient rClient=new RegisteClient();
						System.out.println("DNI");
						String dni=lector.nextLine();
						while(!Client.validarDNI(dni)) {
							System.out.println("DNI incorecte torna l a posar");
							dni=lector.nextLine();
						}
						rClient.dni=dni;
						System.out.println("Contrasenya (Minim 8 caracters)");
						String contrasenya=lector.nextLine();
						System.out.println("Repeteix la Contrasenya");
						String contrasenyaRp=lector.nextLine();
						while(!Client.validarContrasenya(contrasenya, contrasenyaRp)) {
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
						while(!Client.validarCorreu(correu)) {
							System.out.println("Correu Electronic no valid torna l a posar");
							correu=lector.nextLine();
						}
						rClient.correu=correu;
						System.out.println("Numero de telefon");
						String telefon=lector.nextLine();
						while(!Client.validarTelefon(telefon)) {
							System.out.println("Numero de telefon no valid torna l a posar");
							telefon=lector.nextLine();
						}
						rClient.telefon=Integer.parseInt(telefon);
						System.out.println("Adreca");
						rClient.adreca=lector.nextLine();
						Client.afegirClient(rClient, con);
						System.out.println("Prem return per tornar al menu");
						lector.nextLine();
						break;
					case 2:
						System.out.println("DNI");
						dni=lector.nextLine();
						System.out.println("Contrasenya");
						contrasenya=lector.nextLine();
						String opcioMenuLogin="";
						int opcioMenuLoginInt=0;
						if(Client.loginClient(dni,contrasenya,con)) {
							do {
								System.out.println(menuLogin());
								opcioMenuLogin=lector.nextLine();
								while(!FuncionsGenerals.validarOpcioMenu(opcioMenuLogin)) {
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
											while(!FuncionsGenerals.validarOpcioMenu(opcioMenuModificar)) {
												System.out.println("L'opcio no pot contindre lletres i nomes pot tenir un caracter");
												System.out.println(menuModificar());
												opcioMenuModificar=lector.nextLine();
											}
											opcioMenuModificarInt=Integer.parseInt(opcioMenuModificar);
											switch(opcioMenuModificarInt) {
												case 1:
													System.out.println("Correu Electronic");
													correu=lector.nextLine();
													if(Client.validarCorreu(correu)) {
														Client.modificarCorreu(dni, correu, con);
														System.out.println("Correu Electronic modificat correctament");
													}else System.out.println("Correu Electronic no valid");
													System.out.println("Prem return per tornar al menu");
													lector.nextLine();
													break;
												case 2:
													System.out.println("Numero de telefon");
													telefon=lector.nextLine();
													if(Client.validarTelefon(telefon)) {
														System.out.println("Numero de telefon modificat correctament");
														Client.modificarTelefon(dni, Integer.parseInt(telefon), con);
													}else System.out.println("Numero de telefon no valid");
													System.out.println("Prem return per tornar al menu");
													lector.nextLine();
													break;
												case 3:
													System.out.println("Adreca");
													String adreca=lector.nextLine();
													Client.modificarAdreca(dni, adreca, con);
													System.out.println("Adreca modificada correctament");
													System.out.println("Prem return per tornar al menu");
													lector.nextLine();
													break;
												case 4:
													break;
											}
										}while(opcioMenuModificarInt!=4);
										break;
									case 2:
										System.out.println("Contrasenya");
										contrasenya=lector.nextLine();
										if(Client.BorrarClient(dni, contrasenya, con)) {
											System.out.println("Usuari borrat");
											opcioMenuLoginInt=4;
										}else System.out.println("Contrasenya incorrecte");
										System.out.println("Prem return per tornar al menu");
										lector.nextLine();
										break;
									case 3:
										RegistreFactura rfactura= new RegistreFactura();
										rfactura.rClient=Client.DniArClient(dni, con);
										rfactura.numFactura=Empresa.numeroFactura(con);
										rfactura.dataFactura=LocalDate.now();
										System.out.println(Empresa.llistarProductes(con));
										System.out.println("Codi producte(per finalitzar la compra posa ER00)");
										String codiProducte=lector.nextLine();
										while(!Empresa.validarCodiProducte(codiProducte)) {
											System.out.println("El codi de producte ha de tenir 2 numeros i 2 lletres");
											System.out.println("Codi producte(Ha de tenir 2 numeros i 2 lletres)");
											codiProducte=lector.nextLine();
										}
										while(!codiProducte.equals("ER00")) {
											if(Empresa.buscarProducte(codiProducte, con)) {
												System.out.println("Quantitat");
												int quantitat=lector.nextInt();
												lector.nextLine();
												if(Empresa.afegirProducteAFactura(con, rfactura, quantitat, codiProducte)) {
													System.out.println("Producte insertat correctament");
												}else System.out.println("Segons la quantitat que has posat no hi ha stock");
											}else System.out.println("El producte no existeix");
											System.out.println("Vols entrar al carret de la compra(Respon si/no)");
											String veureCarret=lector.nextLine();
											if(veureCarret.equals("si")) {
												System.out.println(Empresa.veureCarretDeLaCompra(rfactura));
												System.out.println("Quin producte vols modificar, posa el numero de linia(posa 0 per sortir)");
												String opcioProducte=lector.nextLine();
												int opcioProducteInt=0;
												while(!FuncionsGenerals.validarInt(opcioProducte)) {
													System.out.println("L'opcio no pot contindre lletres");
													System.out.println(menuPrincipal());
													opcioProducte=lector.nextLine();
												}
												opcioProducteInt=Integer.parseInt(opcioProducte);
												while(opcioProducteInt!=0) {
													System.out.println(menuCarret());
													String opcioMenuCarret="";
													int opcioMenuCarretInt=0;
													opcioMenuCarret=lector.nextLine();
													while(!FuncionsGenerals.validarOpcioMenu(opcioMenuCarret)) {
														System.out.println("L'opcio no pot contindre lletres i nomes pot tenir un caracter");
														System.out.println(menuPrincipal());
														opcioMenuCarret=lector.nextLine();
													}
													opcioMenuCarretInt=Integer.parseInt(opcioMenuCarret);
													switch(opcioMenuCarretInt) {
														case 1:
															System.out.println("Posa la nova quantitat");
															String novaQuantitat=lector.nextLine();
															while(!FuncionsGenerals.validarInt(opcioProducte)) {
																System.out.println("L'opcio no pot contindre lletres");
																System.out.println(menuPrincipal());
																opcioProducte=lector.nextLine();
															}
															Empresa.modificarQuantitatProducteCarret(rfactura, opcioProducteInt, novaQuantitat);
															break;
														case 2:
															Empresa.borrarProducteCarret(rfactura, opcioProducteInt);
															break;
														case 3:
															break;
													}
													System.out.println(Empresa.veureCarretDeLaCompra(rfactura));
													System.out.println("Quin producte vols modificar, posa el numero de linia(posa 0 per sortir)");
													opcioProducte=lector.nextLine();
													while(!FuncionsGenerals.validarInt(opcioProducte)) {
														System.out.println("L'opcio no pot contindre lletres");
														System.out.println(menuPrincipal());
														opcioProducte=lector.nextLine();
													}
													opcioProducteInt=Integer.parseInt(opcioProducte);
												}
												
											}
											System.out.println(Empresa.llistarProductes(con));
											System.out.println("Codi producte(per finalitzar la compra posa ER00)");
											codiProducte=lector.nextLine();
											while(!Empresa.validarCodiProducte(codiProducte)) {
												System.out.println("El codi de producte ha de tenir 2 numeros i 2 lletres");
												System.out.println("Codi producte(Ha de tenir 2 numeros i 2 lletres)");
												codiProducte=lector.nextLine();
											}
										}
										System.out.println("---------------CARRET DE LA COMPRA---------------");
										System.out.println(Empresa.veureCarretDeLaCompra(rfactura));
										System.out.println("Quin producte vols modificar, posa el numero de linia(posa 0 per sortir i tramitar la compra)");
										String opcioProducte=lector.nextLine();
										int opcioProducteInt=0;
										while(!FuncionsGenerals.validarInt(opcioProducte)) {
											System.out.println("L'opcio no pot contindre lletres");
											System.out.println(menuPrincipal());
											opcioProducte=lector.nextLine();
										}
										opcioProducteInt=Integer.parseInt(opcioProducte);
										while(opcioProducteInt!=0) {
											System.out.println(menuCarret());
											String opcioMenuCarret="";
											int opcioMenuCarretInt=0;
											opcioMenuCarret=lector.nextLine();
											while(!FuncionsGenerals.validarOpcioMenu(opcioMenuCarret)) {
												System.out.println("L'opcio no pot contindre lletres i nomes pot tenir un caracter");
												System.out.println(menuPrincipal());
												opcioMenuCarret=lector.nextLine();
											}
											opcioMenuCarretInt=Integer.parseInt(opcioMenuCarret);
											switch(opcioMenuCarretInt) {
												case 1:
													System.out.println("Posa la nova quantitat");
													String novaQuantitat=lector.nextLine();
													while(!FuncionsGenerals.validarInt(opcioProducte)) {
														System.out.println("L'opcio no pot contindre lletres");
														System.out.println(menuPrincipal());
														opcioProducte=lector.nextLine();
													}
													Empresa.modificarQuantitatProducteCarret(rfactura, opcioProducteInt, novaQuantitat);
													break;
												case 2:
													Empresa.borrarProducteCarret(rfactura, opcioProducteInt);
													break;
												case 3:
													break;
											}
											System.out.println(Empresa.veureCarretDeLaCompra(rfactura));
											System.out.println("Quin producte vols modificar, posa el numero de linia(posa 0 per sortir)");
											opcioProducte=lector.nextLine();
											while(!FuncionsGenerals.validarInt(opcioProducte)) {
												System.out.println("L'opcio no pot contindre lletres");
												System.out.println(menuPrincipal());
												opcioProducte=lector.nextLine();
											}
											opcioProducteInt=Integer.parseInt(opcioProducte);
										}
										System.out.println(Empresa.veureFacturaPerPantalla(rfactura));
										Empresa.guardarFacturaFitxer(rfactura);
										Empresa.afegirFactura(con,rfactura);
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
	public static String menuCarret() {
		String menu="1.Modificar quantitat\n2.Esborrar producte del carret\n3.Sortir";
		return menu;
	}


}
