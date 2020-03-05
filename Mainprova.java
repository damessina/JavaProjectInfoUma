import annunci.*;
import java.util.*;
public class Mainprova {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Scanner input= new Scanner (System.in);
		Vector <Annuncio> v = new Vector();
		GestioneVendite ciccio= new GestioneVendite(v);
		System.out.println("Crea un nuovo annuncio?");
		char scelta= input.next().charAt(0);
		if (scelta=='S') {
			Date now =new Date();
			ciccio.aggiungiannuncio("orologio", now);
			ciccio.visualizza();
			System.out.println("Hai creato un nuovo annuncio");
		}
		else System.out.println("Grazie, ciao");

	}

}
