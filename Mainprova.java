public class MainProva {

	public static void main(String[] args) {
		System.out.println("Benvenuto/a in Hasta L'Asta!");
		//Scanner input = new Scanner(System.in);
		
		// crea tre utenti impostando username e password
		Utente[] utenti = new Utente[3];
		utenti[0] = new Utente("Tizio", "tizio", "123");
		utenti[1] = new Utente("Caio", "caio", "456");
		utenti[2] = new Utente("Sempronio", "sempronio", "789");
		
		Menu menu1=new Menu(utenti);

		System.out.println("Accedi alle aste online");
				menu1.login();
	}
}

