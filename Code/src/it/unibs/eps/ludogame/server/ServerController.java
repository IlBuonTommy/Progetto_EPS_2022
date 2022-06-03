package it.unibs.eps.ludogame.server;

import java.util.HashMap;

import org.json.JSONObject;


//Serve da tramite tra server e client thread, per mandare e ricevere file JSON

public class ServerController {
	private String serverName = "ludo-game";
	private HashMap<ClientThread, PlayerColor> clients = new HashMap<ClientThread, PlayerColor>();
	private GameController game = new Game();
	
}
