package it.unibs.eps.ludogame.client;

import it.unibs.eps.ludogame.game.GameModel;
import it.unibs.eps.ludogame.game.Giocatore;
import it.unibs.eps.ludogame.testnetworking.ClientController;

public class LudoGameApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Giocatore gioc = new Giocatore(1,"alessio");
		Giocatore[] listaGiocatori = {gioc};
		/*  player[0] = new Giocatore(1,"alessio");
		  player[1] = new Giocatore(1,"alessio");
		  player[2] = new Giocatore(1,"alessio");
		  player[3] = new Giocatore(1,"alessio");*/
		GameModel gm = new GameModel(1, listaGiocatori);
		ClientController c = new ClientController(gm);
		c.connect("localhost", 6789);
		c.checkVincita();
		
		
	}

}