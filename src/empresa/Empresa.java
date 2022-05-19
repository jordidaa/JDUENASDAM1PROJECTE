package empresa;

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
}
