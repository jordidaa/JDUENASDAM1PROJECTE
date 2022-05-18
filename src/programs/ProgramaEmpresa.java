package programs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProgramaEmpresa {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/botiga","postgres","Jd230103");
			int opcioMenu=0;
			do {
				switch(opcioMenu) {
					case 1:
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						break;
				}
			}while(opcioMenu!=4);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
