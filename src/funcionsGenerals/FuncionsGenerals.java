package funcionsGenerals;

import java.time.LocalDate;

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
	public static boolean validarInt(String integers) {
			int num = 0;
			while(num<integers.length()&&Character.isDigit(integers.charAt(num))) {
				num++;
			}
			if(num==integers.length()) return true;
			else return false;
	}
	public static boolean validarDouble(String doubles) {
		String[] strToDouble=doubles.split("\\.");
		if(strToDouble.length==2) {
			int str1 = 0;
			while(str1<strToDouble[0].length()&&Character.isDigit(strToDouble[0].charAt(str1))) {
				str1++;
			}
			int str2 = 0;
			while(str2<strToDouble[1].length()&&Character.isDigit(strToDouble[1].charAt(str2))) {
				str2++;
			}
			if(Character.isDigit(strToDouble[1].charAt(str2-1))&&Character.isDigit(strToDouble[0].charAt(str1-1))) {
				return true;
			}else return false;
		}else if(strToDouble.length==1) {
			int str1 = 0;
			while(str1<strToDouble[0].length()&&Character.isDigit(strToDouble[0].charAt(str1))) {
				str1++;
			}
			if(Character.isDigit(strToDouble[0].charAt(str1-1))) {
				return true;
			}else return false;
		}else return false;
	}
	public static LocalDate pasarAData(String data) {
		String[] dadesData = data.split("/");
		int dia = Integer.parseInt(dadesData[0]);
		int mes = Integer.parseInt(dadesData[1]);
		int any = Integer.parseInt(dadesData[2]);
		return LocalDate.of(any, mes, dia);
	}
	public static boolean validarData(String data) {
		String[] dadesData = data.split("/");
		if(dadesData.length==3) return true;
		else return false;
	}
}
