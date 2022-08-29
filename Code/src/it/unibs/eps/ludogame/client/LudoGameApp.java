package it.unibs.eps.ludogame.client;

import it.unibs.eps.ludogame.game.GameModel;
import it.unibs.eps.ludogame.game.Giocatore;
import it.unibs.eps.ludogame.testnetworking.ClientController;
import it.unibs.eps.ludogame.testnetworking.ClientGameLudo;
import it.unibs.eps.ludogame.testnetworking.ServerGameLudo;

public class LudoGameApp {

	public static void main(String[] args) {
		//DEBUG ONLY
	/*	Giocatore gioc = new Giocatore(1,"alessio");
		Giocatore[] listaGiocatori = {gioc};

		GameModel gm = new GameModel(1, listaGiocatori);
		ClientController c = new ClientController(gm);
		c.connect("localhost", 50358);//6789
		//c.checkVincita();*/
		
		ServerGameLudo s = new ServerGameLudo();
		ClientGameLudo c = new ClientGameLudo();
		
		s.inizializzazione();
	//	c.connetti();
		
		
		
	}

}