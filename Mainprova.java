import annunci.*;
import java.util.*;

public class MainProva {

	public static void main(String[] args) {
		Scanner input= new Scanner (System.in);
		Vector <Annuncio> v = new Vector();
		GestioneVendite ciccio= new GestioneVendite(v);
		boolean controllo= true;
		do {
		System.out.println("Operazioni disponibili: ");
		System.out.println("");
		System.out.println("1- Crea un nuovo annuncio.");
		System.out.println("2- Visualizza tutti gli annunci.");
		System.out.println("3- Visualizza solo le aste al rialzo.");
		System.out.println("4- Visualizza solo gli acquisti diretti.");
		System.out.println("5- Visualizza gli annunci successivi ad una certa data.");
		System.out.println("6- Termina le operazioni.");
		System.out.println("");
		
		int scelta= input.nextInt();
		input.nextLine();
		switch (scelta) {
			case 1 :	Date now =new Date();
						System.out.println("Aggiungi una breve descrizione del prodotto: ");
						String desc=input.nextLine();
						ciccio.aggiungiannuncio(desc, now);
						break;
			case 2: ciccio.visualizza(); break;
			case 3: ciccio.visualizzaAste(); break;
			case 4: ciccio.visualizzaDiretti(); break;
			case 5: ciccio.visualizzaAfter(); break;
			case 6: controllo=false; break;
			default : System.out.println("Errore nell'inserimento");
		}
		}while (controllo);
		System.out.println("Grazie e Arrivederci.");
	}
}
