package annunci;
import java.util.*;
import java.text.*;

public class AstaRialzo extends Annuncio{
	
	// variabile statica richiesta da Serializable
	static final long serialVersionUID = 1;

	private double prezzoiniziale;
	private Date datascadenza;
	private double importoultimaofferta;
	private String ultimoofferente;
	
	public AstaRialzo(String descrizione, Date now, double prezzoiniziale, Date datascadenza, double importoultimaofferta, String ultimoofferente) {
		super (descrizione, now);
		this.datascadenza=datascadenza;
		this.importoultimaofferta=importoultimaofferta;
		this.ultimoofferente=ultimoofferente;
		this.prezzoiniziale=prezzoiniziale;
	}
	
	// impostiamo un altro costruttore perch� al momento della creazione dell'oggetto AstaRialzo
	// il valore di importoultimaofferta � necessariamente 0
	// per reimpostare il suo valore si utilizza l'apposito metodo setter
	public AstaRialzo(String descrizione, Date now, double prezzoiniziale, Date datascadenza, String ultimoofferente) {
		super (descrizione, now);
		this.datascadenza=datascadenza;
		this.importoultimaofferta=prezzoiniziale;
		this.ultimoofferente=ultimoofferente;
		this.prezzoiniziale=prezzoiniziale;
	}
	
	//imposto i debiti metodi getter
	public double getprezzoiniziale() {
		return prezzoiniziale;
	}
	public Date getdatascadenza() {
		return datascadenza;
	}
	public double getimportoultimaofferta() {
		return importoultimaofferta;
	}
	public String getultimoofferente() {
		return ultimoofferente;
	}
	//imposto il metodo setter per fare una nuova offerta
	public void nuovaofferta(double nuovaofferta) {
		importoultimaofferta=nuovaofferta;
	}
	//overriding del metodo toString()
	//cambio con SimpleDateFormat il formato dell'ora per renderlo pi� user friendly
	public String toString() {
		SimpleDateFormat outputdate= new SimpleDateFormat("EEEE dd MMMM yyyy");
		SimpleDateFormat outputhour= new SimpleDateFormat("HH:mm");
		String text = outputdate.format(datascadenza);
		String text2= " alle ore: " + outputhour.format(datascadenza);
		return "Asta al rialzo: " + descrizione + ". Data di scadenza: "+ text+text2+". Ultima offerta: "+importoultimaofferta ;
	}
}
