import java.util.*;

public class Annuncio {
	
	// usando la classe Date di java.util quando creiamo l'oggetto Annuncio
	// la data si imposta automaticamente sulla data di creazione dell'oggetto
	private Date now =new Date();
	private String descrizione;
	
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
	//overriding del metodo toString per facilitare la visualizzazione degli oggetti
	public String toString() {
		return descrizione + " "+ now;
	}
	

}
