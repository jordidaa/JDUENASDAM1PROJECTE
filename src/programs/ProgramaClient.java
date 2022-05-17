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
				switch(opcioMenu) {
					case 1:
						Client client=new Client();
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
						System.out.println("Adreça");
						rClient.adreca=lector.nextLine();
						client.afegirClient(rClient, con);
						break;
					case 2:
						break;
					case 3:
						break;
				}
			}while(opcioMenu!=5);
			lector.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String menuPrincipal() {
		String menu="1.Alta client\n2.Login\n3.Sortir";
		return menu;
	}

}
