import java.io.*;
import java.util.*;
import eccezioni.*;
import annunci.*;

public class Menu {
	//ponendo un vettore di Annunci come variabile di istanza, e non statica
	//si lascia la libertà di creare più elenchi di Annunci (cosa che in questo caso non si farà perché non richiesto)
	//ad esempio sarebbe possibile pensare un elenco di annunci per prodotti usati, e uno per prodotti nuovi
	//concettualmente  si voleva lasciare la possibilità di formare liste di annunci con caratteristiche diverse
	private Vector <Annuncio> elenco = new Vector <Annuncio>();
	//pongo input come variabile di istanza per non doverla dichiarare ogni volta
	Scanner input;

	public Menu() {
	input= new Scanner (System.in);
	}
	
	public void ingresso(Vector <Utente> v) {

		System.out.println("Accedi [A] o Registrati [R]");
		char scelta=input.next().charAt(0);
		input.nextLine();
		try {
		if (scelta=='A') {
			System.out.println("- Accedi alle aste online -");
			login(v);
		}
		else if(scelta=='R') {
			Utente nuovo=null;
			boolean uservalido=true;
			do {
			//blocco try-catch per convalidare l'utente
			try {
			//richiamo il metodo pubblico creutente() per l'instanziamento di un nuovo oggetto utente
			nuovo=creautente();
			
			//effettuo gli opportuni controlli per vedere se il nuovo utente creato abbia uno user uguale ad uno già creato
			
			for(int i=0; i<v.size();i++) {
				if(v.get(i).getusername()==nuovo.getusername())
					throw new EccezioneUtente("Username non disponibile!");
				else  uservalido = false;
			}
			}catch (EccezioneUtente e) {
				System.out.println(e.getMessage());
				System.out.println("Crea un utente con username valido!");
			}
			}while(uservalido);
			v.add(nuovo);
			//salva il vettore nel file
			try {
				ObjectOutputStream files_output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Utente.dat")));
				// salva l'ultimo oggetto creato nel vettore nel file
				files_output.writeObject(v);
				files_output.close();
			} catch (IOException e) {
				System.out.println("ERRORE di I/O");
				System.out.println(e);
			}		
			System.out.println("Registrazione eseguita con successo!");
		}
		else throw new EccezioneDigitazione ("Errore nella digitazione delle opzioni.");
		}catch(EccezioneDigitazione e) {
			System.out.println(e.getMessage());
			System.out.println("Inserisci un carattere valido");
		}
	}

	//metodo che consente di accedere e usare il menù
	private void login(Vector <Utente> v) {
		if (v.isEmpty()) System.out.println("Vuoto");
		else {
		for (Utente x: v) {
			System.out.println(x);
		}
		}
		boolean flag=true;
		do {
		// chiede credenziali di accesso
		System.out.print("username: ");
		String username = input.nextLine();
		System.out.print("password: ");
		String password = input.nextLine();
		try {
			
		// se le credenziali sono corrette, avvia il menù con le operazioni
		//la verifica avviene attraverso il metodo loginOK (che ritorna un valore booleano)
		if (loginOK(username, password, v)) {
			// saluta l'utente
			System.out.println("");
			System.out.println("Login effettuato con successo!");
			avviaMenu(elenco);
			flag=false;
		}
		else throw new EccezioneLogin("Errore nel login!");
		} catch (EccezioneLogin e) {
			System.out.println(e.getMessage());
			System.out.println("Inserire nuovamente le credenziali.");
		}
		}while(flag);
	}
					
	private void avviaMenu(Vector <Annuncio> v) {	
		
	// istanzio un oggettto della classe GestioneVendite per richiamare metodi che lavorino sugli annunci
	GestioneVendite ciccio = new GestioneVendite(v);
	input= new Scanner (System.in);
	boolean controllo=true;
	do {
	System.out.println("Queste sono le operazioni disponibili: ");
	System.out.println("");
	System.out.println("1- Crea un nuovo annuncio.");
	System.out.println("2- Visualizza tutti gli annunci.");
	System.out.println("3- Visualizza solo le aste al rialzo.");
	System.out.println("4- Visualizza solo gli acquisti diretti.");
	System.out.println("5- Visualizza gli annunci successivi ad una certa data.");
	System.out.println("6- Visualizza le aste scadute. ");
	System.out.println("7- Rimuovi annuncio di asta al rialzo scaduta. ");
	System.out.println("8- Fai una nuova offerta per l'asta al rialzo. ");
	System.out.println("9- Esegui un acquisto diretto. ");
	System.out.println("10 - Esegui Logout. ");
	System.out.println("11- Termina le operazioni.");
	System.out.println("");
	try {
	int opzione= input.nextInt();
	input.nextLine();
	switch (opzione) {
		case 1 :	System.out.println("- Crea un nuovo annuncio -");
					System.out.println("");
					Date now =new Date();
					System.out.println("Aggiungi una breve descrizione del prodotto: ");
					String desc=input.nextLine();
					Annuncio nuovoannuncio = new Annuncio (desc, now);
					boolean flag=true;
					do {
						System.out.println("Vuoi aggiungere un'asta al rialzo [R] o un acquisto diretto[D]?");
						try {
							char scelta=input.next().charAt(0);
							if (scelta=='R'||scelta=='r') {
								//boolean per controllare che l'utente inserisca la lettera giusta
								flag=false;
								ciccio.aggiungiasta(nuovoannuncio, desc);
							}
							else if(scelta=='D'||scelta=='d') {
								//boolean per controllare che l'utente inserisca la lettera giusta
								flag=false;
								ciccio.aggiungidiretto(nuovoannuncio, desc);
							}
							else {
								//si è deciso in estendere la classe eccezione per migliorare la lettura del codice
								throw new EccezioneDigitazione("Errore nell'inserimento.");
							}
						}catch (EccezioneDigitazione e) {
								System.out.println(e.getMessage());
								System.out.println("Riprova!");
							}
						}while(flag);
					break;
		case 2: System.out.println("- Visualizza tutti gli annunci -");
				ciccio.visualizza(); break;
		case 3: System.out.println("- Visualizza solo le aste al rialzo -");
				ciccio.visualizzaAste(); break;
		case 4: System.out.println("- Visualizza solo gli acquisti diretti -");
				ciccio.visualizzaDiretti(); break;
		case 5: System.out.println("- Visualizza gli annunci successivi ad una certa data -");
				ciccio.visualizzaAfter(); break;
		case 6: System.out.println("- Visualizza le aste scadute -");
				ciccio.visualizzascadute(); break;
		case 7: System.out.println("- Rimuovi annuncio di asta al rialzo scaduta -");
				ciccio.rimuoviasta(); break;
		case 8: System.out.println("- Fai una nuova offerta per un'asta al rialzo -");
				ciccio.nuovaofferta(); break;
		case 9: System.out.println("- Esegui un acquisto diretto -");
				ciccio.rimuovidiretto(); break;
		case 11: System.out.println("- Termina le operazioni -"); //qua forse conviene fare un metodo in GestioneVendite
				controllo=false; 
				if (ciccio.daSalvare()) {
					//inizializzo la variabile risposta a 0 di default
					char risposta = 0;
					do {
						try {
						System.out.println("Vuoi salvare le modifiche effettuate (S/N)?");
						risposta = input.nextLine().charAt(0);
						if (risposta=='S') { 
							boolean tuttoOk = ciccio.salva();
							if (tuttoOk) 
								System.out.println("Dati Salvati.");
							else
								System.out.println("Problema durante il salvataggio.");
							}
						else if(risposta=='N') System.out.println("I dati non sono stati salvati.");
						else throw new EccezioneDigitazione ("Valore inserito non valido");
						
						}catch (EccezioneDigitazione e) {
							System.out.println(e.getMessage());
							System.out.println("Inserisci un carattere [S] o [N]");
						}
					} while (risposta!='S' && risposta!='N');
				}
				break;
		default : System.out.println("Errore nell'inserimento");
	}
	}catch(InputMismatchException e) {
		input.next();
		System.out.println("Valore inserito non valido.");
	}
	}while (controllo);
	System.out.println("Grazie e Arrivederci.");
	//input.close();
	}

	//metodo che permette la creazione di un nuovo utente non registrato
	public Utente creautente() {
		input= new Scanner (System.in);
		Utente nuovo;
		System.out.println("- Registrazione -");
		System.out.println("");
		System.out.println("Inserisci nome:");
		String nome = input.nextLine();
		System.out.println("Inserisci username: ");
		String user = input.nextLine();
		String pass1=null;
		String pass2=null;
		boolean controllo=true;
		do {
		try {
		System.out.println("Inserisci password:");
		pass1=input.nextLine();
		System.out.println("Reinserisci la password:");
		pass2=input.nextLine();
		boolean ok= pass1.equals(pass2);
		if(ok) {
			System.out.println("Ottimo! Le due password corrispondono.");
			controllo=false;
		}
		else throw new EccezioneDigitazione("Le due password non sono corrispondenti!");
		
		}catch(EccezioneDigitazione e) {
			System.out.println(e.getMessage());
		}
		}while(controllo);
		nuovo = new Utente (nome, user, pass1);
		
		//avvio il menu cos' che l'utente appena registrato non debba rifare il login dopo il primo accesso
		//in questo modo l'utente registrato verrà effettivamente creato solo al termine di una sessione
		avviaMenu(elenco);
		return nuovo;

	}

	//verifica le credenziali inserite (cerca tra gli utenti)
	// invocando il metodo opportuno della classe Utente
		private boolean loginOK(String usr, String pass, Vector <Utente> vec) {
		
		int i = 0; 
		boolean loginOK=false;
		while (i<vec.size() && !loginOK) {
			loginOK = vec.get(i).controlla(usr, pass);
			if (!loginOK) i++;
		}
		return loginOK;
	}
}
