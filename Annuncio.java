package annunci;
import java.io.Serializable;
import java.util.*;

public class Annuncio implements Serializable {
	
	// variabile statica richiesta da Serializable
	static final long serialVersionUID = 1;
	
	// usando la classe Date di java.util quando creiamo l'oggetto Annuncio
	// la data si imposta automaticamente sulla data di creazione dell'oggetto
	protected Date now =new Date();
	protected String descrizione;
	
	public Annuncio(String descrizione, Date now) {
		this.descrizione=descrizione;
		this.now=now;
		
	}
	public String getdescrizione() {
		return descrizione;
	}
	// metodo per visualizzare la data di creazione
	public Date getdata() {
		return now;
	}
}
