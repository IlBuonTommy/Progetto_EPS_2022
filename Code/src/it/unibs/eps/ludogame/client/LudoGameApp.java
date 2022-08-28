package it.unibs.eps.ludogame.client;

import it.unibs.eps.ludogame.game.GameModel;
import it.unibs.eps.ludogame.game.Giocatore;
import it.unibs.eps.ludogame.testnetworking.ClientController;

public class LudoGameApp {

	public static void main(String[] args) {
		//DEBUG ONLY
		Giocatore gioc = new Giocatore(1,"alessio");
		Giocatore[] listaGiocatori = {gioc};

		GameModel gm = new GameModel(1, listaGiocatori);
		ClientController c = new ClientController(gm);
		c.connect("localhost", 6789);
		//c.checkVincita();
		
		
	}

}