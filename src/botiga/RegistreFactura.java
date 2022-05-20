package botiga;
import java.util.ArrayList;
import java.time.*;
import client.*;

public class RegistreFactura {
	public int numFactura;
	public LocalDate dataFactura;
	public RegisteClient rClient;
	public ArrayList<RegistreLiniaFactura> liniaFactura = new ArrayList<RegistreLiniaFactura>();
}
