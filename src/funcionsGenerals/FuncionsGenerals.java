package funcionsGenerals;

public class FuncionsGenerals {
	public static boolean validarOpcioMenu(String opcioMenu) {
		if(opcioMenu.length()==1) {
			int num = 0;
			while(num!=1&&Character.isDigit(opcioMenu.charAt(num))) {
				num++;
			}
			if(num==1) return true;
			else return false;
		}else return false;
	}
}
