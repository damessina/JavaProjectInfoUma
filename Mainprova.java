import eccezioni.*;
import java.util.*;

public class MainProva {

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		System.out.println("Benvenuto/a in Hasta L'Asta!");
		
		//accedi (Output) o registrati(Input)
		
		//creo un vettore di utenti inizialmente vuoto che deve essere riempito
		//una volta lanciato
		Vector <Utente> utenti = new Vector <Utente>();
		
		//creo il menu delle aste
		Menu mymenu=new Menu(utenti);
		
		boolean bandiera=true;
		do {
		if(!utenti.isEmpty()) {
		System.out.println("Accedi [A] o Registrati [R]");
		char scelta=input.next().charAt(0);
		input.nextLine();
		try {
		if (scelta=='A') {
			System.out.println("Accedi alle aste online");
			bandiera=false;
			mymenu.login();
		}
		else if(scelta=='R') {
			
			utenti.add(mymenu.creautente());
			System.out.println("Registrazione eseguita con successo!");
		}
		else throw new EccezioneDigitazione ("Errore nella digitazione delle opzioni.");
		}catch(EccezioneDigitazione e) {
			System.out.println(e.getMessage());
			System.out.println("Inserisci un carattere valido");
		}
		}
		else {
			System.out.println("Non ci sono utenti ancora registrati.");
			System.out.println("Si proceda alla registrazione");
			mymenu.creautente();
			bandiera=false;
		}
		}while (bandiera);
	input.close();
	}
}
