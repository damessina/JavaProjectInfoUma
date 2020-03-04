import java.util.*;

public class AstaRialzo extends Annuncio{
	
	private double prezzoiniziale;
	private String datascadenza;
	private double importoultimaofferta;
	private String ultimoofferente;
	
	public AstaRialzo(String descrizione, Date now, double prezzoiniziale, String datascadenza, double importoultimaofferta, String ultimoofferente) {
		super (descrizione, now);
		this.datascadenza=datascadenza;
		this.importoultimaofferta=importoultimaofferta;
		this.ultimoofferente=ultimoofferente;
		this.prezzoiniziale=prezzoiniziale;
	}
	
	// impostiamo un altro costruttore perché al momento della creazione dell'oggetto AstaRialzo
	// il valore di importoultimaofferta è necessariamente 0
	// per reimpostare il suo valore si utilizza l'apposito metodo setter
	public AstaRialzo(String descrizione, Date now, double prezzoiniziale, String datascadenza, String ultimoofferente) {
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
	public String getdatascadenza() {
		return datascadenza;
	}
	public double getimportoultimaofferta() {
		return importoultimaofferta;
	}
	public String getultimoorente() {
		return ultimoofferente;
	}
	public void setimportoultimaofferta(double nuovaofferta) {
		importoultimaofferta=nuovaofferta;
	}

}
