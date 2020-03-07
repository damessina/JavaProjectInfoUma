import annunci.*;
import java.util.*;
public class Mainprova {

	public static void main(String[] args) {
		Scanner input= new Scanner (System.in);
		Vector <Annuncio> v = new Vector();
		GestioneVendite ciccio= new GestioneVendite(v);
		boolean controllo= true;
		do {
		System.out.println("Operazioni disponibili: ");
		System.out.println("Crea un nuovo annuncio. [N]");
		System.out.println("Visualizza tutti gli annunci. [T]");
		System.out.println("Visualizza solo le aste al rialzo. [A]");
		System.out.println("Visualizza solo gli acquisti diretti. [D]");
		System.out.println("Termina le operazioni. [F]");
		
		char scelta= input.next().charAt(0);
		input.nextLine();
		switch (scelta) {
			case 'N' :	Date now =new Date();
						System.out.println("Aggiungi una breve descrizione del prodotto: ");
						String desc=input.nextLine();
						ciccio.aggiungiannuncio(desc, now);
						break;
			case 'T': ciccio.visualizza(); break;
			case 'A': ciccio.visualizzaAste(); break;
			case 'D': ciccio.visualizzaDiretti(); break;
			case 'F': controllo=false;
			default : System.out.println("Errore nell'inserimento");
		}
		}while (controllo);
		System.out.println("Grazie, arrivederci");
	}
}
