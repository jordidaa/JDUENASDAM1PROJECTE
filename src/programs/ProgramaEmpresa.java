package programs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import client.*;
import funcionsGenerals.*;
import empresa.*;

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
										System.out.println("Prem return per tornar al menu");
										lector.nextLine();
									}else System.out.println("Dni no valid");
									System.out.println("Prem return per tornar al menu");
									lector.nextLine();
									break;
								case 2:
									System.out.println(Client.llistarDadesClient(con));
									System.out.println("Prem return per tornar al menu");
									lector.nextLine();
									break;
								case 3:
									break;
							}
						}while(opcioMenuClientInt!=3);
						break;
					case 2:
						String opcioMenuProducte="";
						int opcioMenuProducteInt=0;
						do {
							System.out.println(menuProducte());
							opcioMenuProducte=lector.nextLine();
							while(!FuncionsGenerals.validarOpcioMenu(opcioMenuProducte)) {
								System.out.println("L'opcio no pot contindre lletres i nomes pot tenir un caracter");
								System.out.println(menuProducte());
								opcioMenuProducte=lector.nextLine();
							}
							opcioMenuProducteInt=Integer.parseInt(opcioMenuProducte);
							switch(opcioMenuProducteInt) {
								case 1:
									RegisteProducte rProducte=new RegisteProducte();
									System.out.println("Codi producte(Ha de tenir 2 numeros i 2 lletres)");
									String codiProducte=lector.nextLine();
									while(!Empresa.validarCodiProducte(codiProducte)) {
										System.out.println("El codi de producte ha de tenir 2 numeros i 2 lletres");
										System.out.println("Codi producte(Ha de tenir 2 numeros i 2 lletres)");
										codiProducte=lector.nextLine();
									}
									rProducte.codi=codiProducte;
									System.out.println("Nom producte");
									rProducte.nom=lector.nextLine();
									System.out.println("Stock inicial");
									String stock=lector.nextLine();
									while(!FuncionsGenerals.validarInt(stock)) {
										System.out.println("L'stock conte lletres");
										System.out.println("Stock inicial");
										stock=lector.nextLine();
									}
									rProducte.stock=Integer.parseInt(stock);
									System.out.println("Preu");
									String preu=lector.nextLine();
									while(!FuncionsGenerals.validarDouble(preu)) {
										System.out.println("El preu conte lletres o mes de un punt");
										System.out.println("Stock inicial");
										preu=lector.nextLine();
									}
									rProducte.preu=Double.parseDouble(preu);
									System.out.println("Iva");
									String iva=lector.nextLine();
									while(!FuncionsGenerals.validarInt(iva)) {
										System.out.println("L'iva conte lletres");
										System.out.println("Iva");
										iva=lector.nextLine();
									}
									rProducte.iva=Integer.parseInt(iva);
									Empresa.afegirProducte(rProducte, con);
									System.out.println("Prem return per tornar al menu");
									lector.nextLine();
									break;
								case 2:
									String opcioModificarProducte="";
									int opcioModificarProducteInt=0;
									do {
										System.out.println(menuModificarProducte());
										opcioModificarProducte=lector.nextLine();
										while(!FuncionsGenerals.validarOpcioMenu(opcioMenuProducte)) {
											System.out.println("L'opcio no pot contindre lletres i nomes pot tenir un caracter");
											System.out.println(menuModificarProducte());
											opcioModificarProducte=lector.nextLine();
										}
										opcioModificarProducteInt=Integer.parseInt(opcioModificarProducte);
										switch(opcioModificarProducteInt) {
											case 1:
												System.out.println("Codi producte(Ha de tenir 2 numeros i 2 lletres)");
												codiProducte=lector.nextLine();
												while(!Empresa.validarCodiProducte(codiProducte)) {
													System.out.println("El codi de producte ha de tenir 2 numeros i 2 lletres");
													System.out.println("Codi producte(Ha de tenir 2 numeros i 2 lletres)");
													codiProducte=lector.nextLine();
												}
												if(Empresa.buscarProducte(codiProducte, con)) {
													System.out.println("Cuant stock vols afegir?");
													stock=lector.nextLine();
													while(!FuncionsGenerals.validarInt(stock)) {
														System.out.println("L'stock conte lletres");
														System.out.println("Cuant stock vols afegir?");
														stock=lector.nextLine();
													}
													Empresa.afegirStock(stock, codiProducte, con);
													System.out.println("Stock afegit correctament");
													System.out.println("Prem return per tornar al menu");
													lector.nextLine();
												}else {
													System.out.println("El producte no existeix");
													System.out.println("Prem return per tornar al menu");
													lector.nextLine();		
												}
												break;
											case 2:
												System.out.println("Codi producte(Ha de tenir 2 numeros i 2 lletres)");
												codiProducte=lector.nextLine();
												while(!Empresa.validarCodiProducte(codiProducte)) {
													System.out.println("El codi de producte ha de tenir 2 numeros i 2 lletres");
													System.out.println("Codi producte(Ha de tenir 2 numeros i 2 lletres)");
													codiProducte=lector.nextLine();
												}
												if(Empresa.buscarProducte(codiProducte, con)) {
													System.out.println("Quin preu vols posar?");
													preu=lector.nextLine();
													while(!FuncionsGenerals.validarDouble(preu)) {
														System.out.println("El preu conte lletres o mes de un punt");
														System.out.println("Quin preu vols posar?");
														preu=lector.nextLine();
													}
													Empresa.modificarPreu(preu, codiProducte, con);
													System.out.println("Preu modificat correctament");
													System.out.println("Prem return per tornar al menu");
													lector.nextLine();
												}else {
													System.out.println("El producte no existeix");
													System.out.println("Prem return per tornar al menu");
													lector.nextLine();
												}												
												break;
											case 3:
												System.out.println("Codi producte(Ha de tenir 2 numeros i 2 lletres)");
												codiProducte=lector.nextLine();
												while(!Empresa.validarCodiProducte(codiProducte)) {
													System.out.println("El codi de producte ha de tenir 2 numeros i 2 lletres");
													System.out.println("Codi producte(Ha de tenir 2 numeros i 2 lletres)");
													codiProducte=lector.nextLine();
												}
												if(Empresa.buscarProducte(codiProducte, con)) {
													System.out.println("Quin iva vols posar?");
													iva=lector.nextLine();
													while(!FuncionsGenerals.validarInt(iva)) {
														System.out.println("L'iva conte lletres");
														System.out.println("Quin iva vols posar?");
														iva=lector.nextLine();
													}
													Empresa.modificarIva(iva, codiProducte, con);
													System.out.println("Iva modificat correctament");
													System.out.println("Prem return per tornar al menu");
													lector.nextLine();
												}else {
													System.out.println("El producte no existeix");
													System.out.println("Prem return per tornar al menu");
													lector.nextLine();
												}
												break;
											case 4:
												break;
										}
									}while(opcioModificarProducteInt!=4);
									break;
								case 3:
									System.out.println("Codi producte(Ha de tenir 2 numeros i 2 lletres)");
									codiProducte=lector.nextLine();
									while(!Empresa.validarCodiProducte(codiProducte)) {
										System.out.println("El codi de producte ha de tenir 2 numeros i 2 lletres");
										System.out.println("Codi producte(Ha de tenir 2 numeros i 2 lletres)");
										codiProducte=lector.nextLine();
									}
									if(Empresa.borrarProducte(codiProducte, con)) {
										System.out.println("Producte borrat correctament");
										System.out.println("Prem return per tornar al menu");
										lector.nextLine();
									}else {
										System.out.println("Producte no trobat");
										System.out.println("Prem return per tornar al menu");
										lector.nextLine();
									}
									break;
								case 4:
									break;
							}
						}while(opcioMenuProducteInt!=4);
						break;
					case 3:
						break;
					case 4:
						break;
				}
			}while(opcioMenuInt!=4);
			lector.close();
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
	public static String menuProducte() {
		String menu="1.Alta Producte\n2.Modificar Producte\n3.Baixa Producte\n4.Sortir";
		return menu;
	}
	public static String menuModificarProducte() {
		String menu="1.Modificar Stock\n2.Modificar preu\n3.Modificar iva\n4.Sortir";
		return menu;
	}
}
