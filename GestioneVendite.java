import java.util.*;
import java.text.SimpleDateFormat;
public class GestioneVendite {
	Scanner input = new Scanner (System.in);
	private Vector <Annuncio> v  = new Vector();
	
	public GestioneVendite (Vector <Annuncio> v) {
		this.v=v;
	}
	//metodo per la creazione di un nuovo annuncio
	public void aggiungiannuncio(String descrizione, Date now) throws EccezioneDigitazione {
		Annuncio nuovoannuncio = new Annuncio (descrizione, now);
		boolean flag=true;
		
		do {
		System.out.println("Vuoi aggiungere un'asta al rialzo [R] o un acquisto diretto?[D]");
		char scelta=input.next().charAt(0);
		
		if (scelta=='R') {
			
			flag=false;
			
			System.out.println("Inserisci un prezzo:");
			double prezzoiniziale=input.nextDouble();
			
			//inserire l'eccezione per la data
			
			System.out.println("Inserisci una data di scadenza");
			String datascadenza=input.nextLine();
			input.next();
			
			System.out.println("Inserisci il nome dell'ultimo offerente");
			String ultimoofferente= input.nextLine();
			input.next();
			
			nuovoannuncio= new AstaRialzo(descrizione, now, prezzoiniziale, datascadenza, ultimoofferente);
		}
		else if (scelta=='D') {
			
			flag=false;
			
			//gestisci l'eccezione del prezzo
			System.out.println("Inserisci un prezzo:");
			double prezzo=input.nextDouble();
			
			System.out.println("Inserisci il tempo di consegna (numero di giorni): ");
			int tempiconsegna=input.nextInt();
			nuovoannuncio= new AcquistoDiretto (descrizione, now, prezzo, tempiconsegna);
		}
		else throw new EccezioneDigitazione ("errore nella digitazione, riprova");
		}while(flag);
	}
	
public void visualizza() {
	for (Annuncio x : v) {
	System.out.println(x);
	}
}
}
	















