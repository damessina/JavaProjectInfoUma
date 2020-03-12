import annunci.*;
import Eccezioni.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class GestioneVendite {
	
	// nome del file in cui è salvato il registro
	private String AnnuncioFile;
	// flag per tenere traccia di modifiche non salvate su file
	private boolean modifica=false;
	
	Scanner input = new Scanner (System.in);
	private Vector <Annuncio> v  = new Vector <Annuncio>();
	private Date now = new Date();
	
	public GestioneVendite (String AnnuncioFile) {
		this.AnnuncioFile=AnnuncioFile;
	try {
		ObjectInputStream file_input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(AnnuncioFile)));
		// legge l'intero vettore da file
		v = (Vector<Annuncio>) file_input.readObject();
		file_input.close();
	} catch (FileNotFoundException e) {
		// gestisce il caso in cui il file non sia presente (sarà creato poi...)
		System.out.println("Il file " + AnnuncioFile + " non è mai stato salvato");
		System.out.println("Sara' creato al primo salvataggio");
		System.out.println();
	} catch (ClassNotFoundException e) {
		// gestisce il caso in cui il file non contenga un oggetto
		System.out.println("ERRORE di lettura");
		System.out.println(e);
	} catch (IOException e) {
		// gestisce altri errori di input/output
		System.out.println("ERRORE di I/O");
		System.out.println(e);
	}
}
	
	
	//metodo per la creazione di un nuovo annuncio
	public void aggiungiannuncio(String descrizione, Date now){
		Annuncio nuovoannuncio = new Annuncio (descrizione, now);
		boolean flag=true;
		do {
			try {
				System.out.println("Vuoi aggiungere un'asta al rialzo [R] o un acquisto diretto?[D]");
				char scelta=input.next().charAt(0);
		
				if (scelta=='R') {
			
					flag=false;
			
					boolean control=true;
					double prezzoiniziale=0.0;
			//gestisco l'eccezione dell'inserimento di un prezzo decimale
			
			do {
				try {
					System.out.println("Inserisci in prezzo iniziale:");
					prezzoiniziale=input.nextDouble();
					//input.nextLine();
					control=false;
			}catch (InputMismatchException e) {
				input.next();
				System.out.println("Formato non valido.");
				System.out.println("Inserisci un nuovo prezzo.");
				System.out.println("Usare la virgola per dividere le unità dai decimali");
			}
			}while (control);
			
			// mi serve per mangiarmi lo spazio successivo
			//altrimenti input.nextLine() successivo mi mangia lo spazio e viene skippato
			input.nextLine();
			
			//in questo punto sto gestendo l'inserimento della data
			//con SimpleDateFormat sto impostando il nuovo formato possibile di data da inserire
			//imposto una booleana per fare in modo che se ci sia l'eccezione
			//il programma continui a chiedere una data valida e non si interrompa
			
			boolean controllo=true;
			// imposto la variabile d'istanza a null, perché dopo verrà modificata dal blocco sottostante
			// devo dargli un valore, altrimenti il compilatore mi dà errore
			Date datagiusta=null;
			
			do {
				try {
					System.out.println("Inserisci una data di scadenza in formato dd-MM-yyyy: ");
					SimpleDateFormat newformat = new SimpleDateFormat ("dd-MM-yyyy");
					String datascadenza=input.next();
					datagiusta=newformat.parse(datascadenza);
					if  (now.after(datagiusta)) throw new EccezioneData("Data invalida: La data di scadenza deve essere posteriore a quella attuale");
					else controllo=false;
					} catch (EccezioneData e) {
						System.out.println(e.getMessage());
						System.out.println("Inserire nuovamente la data");
					} catch (ParseException e) {
						System.out.println("Impossibile ricavare una data valida dal formato inserito");
					}
				}while (controllo);
				
				System.out.println("Inserisci il nome dell'ultimo offerente:");
				String ultimoofferente= input.nextLine();
				input.nextLine();
				nuovoannuncio= new AstaRialzo(descrizione, now, prezzoiniziale, datagiusta, ultimoofferente);
				// bisogna aggiungere l'oggetto al vettore
				v.add(nuovoannuncio);
				modifica=true; // modifica non salvata
				System.out.println("Hai creato un nuovo annuncio!");
				System.out.println("");
				}
				//gestisco la scelta dell'utente di inserire un annuncio di AcquistoDiretto
		else if (scelta=='D') {
			
			flag=false;
			
			//gestisci l'eccezione del prezzo
			System.out.println("Inserisci un prezzo:");
			double prezzo=input.nextDouble();
			System.out.println("Inserisci il tempo di consegna (numero di giorni): ");
			int tempiconsegna=input.nextInt();
			nuovoannuncio= new AcquistoDiretto (descrizione, now, prezzo, tempiconsegna);
			v.add(nuovoannuncio);
			modifica=true; // modifica non salvata
			System.out.println("Hai creato un nuovo annuncio");
			System.out.println("");
		}
		else {
			//si è deciso in estendere la classe eccezione per migliorare la lettura del codice
			throw new EccezioneDigitazione("Errore nell'inserimento");
		}
		}catch (EccezioneDigitazione e) {
			System.out.println(e.getMessage());
			System.out.println("Riprova");
		}
		}while(flag);
	}
	public void visualizza() {
		
		if (v.isEmpty()) {
			System.out.println("Non è stato creato nessun annuncio. Fai tu la prima mossa!");
			System.out.println();
		}else {
			System.out.println("Elenco degli annunci:");
			for (Annuncio x : v) {
			System.out.println(x);
			System.out.println("");
			}
	}
}
		
	public void visualizzaAste() {
		boolean vuotoA=true;
		System.out.println("Elenco delle aste a rialzo:");
		for (Annuncio x:v) 
		if (x instanceof AstaRialzo){
			System.out.println(x);
			System.out.println("");
			vuotoA=false;
		}
		if (vuotoA) {
			System.out.println("Non è stato creato nessun annuncio di aste al rialzo. Fai tu la prima mossa!");
			System.out.println();
			}
	}
	
 
	public void visualizzaDiretti() {
		boolean vuotoD=true;
		System.out.println("Elenco degli acquisti diretti:");
		for (Annuncio x:v) 
			if (x instanceof AcquistoDiretto) {
				System.out.println(x);
				System.out.println("");
				vuotoD=false;
			}
		if (vuotoD) {
			System.out.println("Non è stato creato nessun annuncio di acquisti diretti. Fai tu la prima mossa!");
			System.out.println();
			}
	}
	

	
	public void visualizzaAfter() {
		boolean controllo=true;
		Date datagiusta = null;
		boolean vuoti=true;
		
		do {
			try {
			System.out.println("Inserisci una data di scadenza in formato dd-MM-yyyy: ");
			SimpleDateFormat newformat = new SimpleDateFormat ("dd-MM-yyyy");
			String datascadenza=input.next();
			datagiusta=newformat.parse(datascadenza);
			controllo=false;
			}
			catch (ParseException e) {
				System.out.println("Impossibile ricavare una data valida dal formato inserito");
			}
		}while (controllo);
	System.out.println("Ecco gi annunci dopo "+ datagiusta+":");
	for (Annuncio x:v) 
		if (x.getdata().after(datagiusta)) {
			System.out.println(x);
			System.out.println("");
			vuoti=false;
		}
		if(vuoti)
			System.out.println("Non ci sono aste al rialzo dopo "+ datagiusta);
			System.out.println();
	}

	
	public void visualizzascadute() {
		boolean vuote=true;
		for (Annuncio x:v) {
			if (x instanceof AstaRialzo) {
				if (((AstaRialzo) x).getdatascadenza().before(now)) {
					System.out.println(x);
					System.out.println("");
					vuote=false;
				}
			}
		}
			if(vuote) {
				System.out.println("Non ci sono aste scadute");
				System.out.println();
			
			}
		}
	
	public void rimuoviannuncio() {
		boolean empty=true;
		//creo un array della stessa dimensione del vettore
		//l'array mi serve per storare gli indici di posizione delle aste scadute
		//seguendo un indice sequenziale, utile alla loro eliminazione
		int[] posizioni = new int [v.size()];
		int contatore=0;
		System.out.println("Queste sono le aste scadute:");
		System.out.println("");
		for (int i=0; i<v.size();i++) {
			if (v.get(i) instanceof AstaRialzo) {
				if (((AstaRialzo) v.get(i)).getdatascadenza().before(now)) {
					System.out.print(contatore + "- ");
					System.out.println(v.get(i));
					System.out.println("");
					empty=false;
					// creo un array che come indice ha un contatore che avanza man mano che trova delle aste scadute
					// l'array memorizza, ogni volta che trova un'asta scaduta, la posizione che la relativa asta aveva sul vettore
					// in questo modo ho un array che ordina sequenzialmente le posizioni delle aste scadute sul vettore
					posizioni[contatore]=i;
					contatore++;
				}
			}
		}
		if(!empty) {
		System.out.println("Quale annuncio vuoi eliminare?");
		boolean bandierina=true;
		do {
			try {
		int elenco=input.nextInt();
		// se il numero inserito è maggiore del contatore (cioè dell'indice dell'ultimo elemento inserito nell'array), getto l'eccezione
		if(elenco>contatore) throw new ErroreIndice("Errore nella digitazione dell'indice");
		else {
			// rimuovo l'annuncio nella posizione "elenco" dell'array
			v.remove(posizioni[elenco]);
			bandierina=false;
			// stampo il nome dell'ultimo offerente
			System.out.print("Il nome dell'ultimo offerente è: ");
			System.out.println(((AstaRialzo) v.get(posizioni[elenco])).getultimoorente());
		}
			}catch (ErroreIndice e) {
				input.next();
				System.out.print(e.getMessage());
				System.out.println("Digita il numero dell'annuncio che vuoi eliminare: ");
			}
		}while(bandierina);
		}else {
			System.out.println("Non ci sono ascte scadute");
			System.out.println();
		}
			
		}
		
	// verifica se ci sono modifiche non salvate
		public boolean daSalvare() {
			return modifica;
		}
		
		// salva il registro nel file
		// restituisce true se il salvataggio è andato a buon fine
		public boolean salva() {
			if (daSalvare()) { // salva solo se necessario (se ci sono modifiche)
				try {
					ObjectOutputStream file_output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(AnnuncioFile)));
					// salva l'intero oggetto (vettore) nel file
					file_output.writeObject(v);
					file_output.close();
					modifica = false; // le modifiche sono state salvate
					return true;
				} catch (IOException e) {
					System.out.println("ERRORE di I/O");
					System.out.println(e);
					return false;
				}		
			} else return true;
		}
	}
