package it.unibs.eps.ludogame.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import it.unibs.eps.ludogame.game.*;
//Serve da tramite tra server e client thread, per mandare e ricevere file JSON
import it.unibs.eps.ludogame.server.ClientThread;

public class ServerController {
	private String serverName = "ludo-game";
	private HashMap<ClientThread, PlayerColor> clients = new HashMap<ClientThread, PlayerColor>();
	private GameController game = new Game();
	
	//manda messaggio di benvenuto
	protected synchronized void sendWelcome(ClientThread client) {
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		json.put("type", MsgType.WELCOME.toString());
		data.put("serverName", serverName);
		json.put("data", data);
		client.out(json.toString());
	}
	
	//Assegna colore scelto
	private void sendassignColor(ClientThread client, PlayerColor color) {
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		json.put("type", MsgType.ASSIGNCOLOR.toString());
		data.put("color", color.toString());
		json.put("data", data);
		client.out(json.toString());
	}
	
	//manda turno con opzioni possibili
	private void sendTurn() {
		JSONObject json = new JSONObject();
		ClientThread client = null;
		for (Map.Entry<ClientThread, PlayerColor> entry : clients.entrySet()) {
			if (entry.getValue() == game.currentPlayer()) {
				client = entry.getKey();
				break;
			}
		}
		json.put("type", MsgType.TURN.toString());
		json.put("data", game.turn(null));
		client.out(json.toString());
	}
	
	//aggiorna in broadcast tutti i client lo stato del gioco
	private void broadcastUpdate() {
		JSONObject json = new JSONObject();
		json.put("type", MsgType.UPDATE.toString());
		json.put("data", game.toJSON());
		for (Entry<ClientThread, PlayerColor> client : clients.entrySet()) {
			client.getKey().out(json.toString());
		}
	}
	
	//Manda in broadcast a tutti i client un messaggio
	private void broadcastMessage(ClientThread client, String message) {
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		json.put("type", MsgType.MESSAGE.toString());
		json.put("message", message);
		if (client == null) {
			data.put("sender", "server");
		}
		else {
			data.put("sender", clients.get(client).toString());
		}
		json.put("data", data);
		for (Entry<ClientThread, PlayerColor> entry : clients.entrySet()) {
			if (client != entry.getKey()) {
				entry.getKey().out(json.toString());
			}
		}
	}
	
	
	//Informa tutti i client che un client si è disconnesso
	private void broadcastPlayerDisconnected(ClientThread client) {
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		json.put("type", MsgType.PLAYERDISCONNECTED.toString());
		data.put("player", clients.get(client).toString());
		json.put("data", data);
		for (Entry<ClientThread, PlayerColor> entry : clients.entrySet()) {
			if (client != entry.getKey()) {
				entry.getKey().out(json.toString());
			}
		}
	}
	
	//esegue le operazioni di disconnessione ed elimina il client dalla lista
	protected synchronized void disconnect(ClientThread client) {
		if (clients.containsKey(client)) {
			PlayerColor tempColor = clients.get(client);
			game.remove(tempColor);
			broadcastPlayerDisconnected(client);
			clients.remove(client, clients.get(client));
			if (clients.size() <= 0) {
				System.out.println("Tutti i giocatori hanno abbandonato; riavvio gioco...");
				game = new Game();
				PlayerColor.resetAvail();
			}
			else if (tempColor == game.currentPlayer()) {
				doRound();
			}
		}
	}
	
	//Metodo che effettua il decode dei messaggi ricevuti dal client
	protected synchronized void decoder(ClientThread client, String input) throws Exception {
		JSONObject json = new JSONObject(input);
		JSONObject data = new JSONObject(json.get("data").toString());

		// register
		if (json.getString("type").equals(MsgType.REGISTER.toString()) && client.getState() == MsgType.WELCOME) {
			if (data.getString("playerName").length() <= 40 && data.getString("clientName").length() <= 40) {
				PlayerColor tempColor;
				if (data.has("requestedColor")) {
					tempColor = game.register(decodeColor(data.getString("requestedColor")), data.getString("playerName"), data.getString("clientName"), data.getFloat("clientVersion"));
				}
				else {
					tempColor = game.register(null, data.getString("playerName"), data.getString("clientName"), data.getFloat("clientVersion"));
				}
				if (tempColor != null) {
					clients.put(client, tempColor);
					client.setState(MsgType.REGISTER);
					sendassignColor(client, tempColor);
					broadcastUpdate();
				}
				else {
					sendError(client, MsgError.SERVERFULL, null);
					throw new IllegalArgumentException("server full or game already running");
				}
			}
			else {
				sendError(client, MsgError.UNSUPPORTEDMESSAGETYPE, "Your name or client name exceeded the character limit of 40.");
				throw new IllegalArgumentException("name or client name to long");
			}
		}

		// ready
		else if (json.getString("type").equals(MsgType.READY.toString()) && (client.getState() == MsgType.REGISTER || client.getState() == MsgType.READY) && game.getState() == GameState.WAITINGFORPLAYERS) {
			client.setState(MsgType.READY);
			if (game.ready(clients.get(client), data.getBoolean("ready"))) {
				doRound();
			}
			else { broadcastUpdate(); }
		}

		// move
		else if (json.getString("type").equals(MsgType.MOVE.toString()) && client.getState() == MsgType.READY && game.getState() == GameState.RUNNING) {
			if (clients.get(client) == game.currentPlayer()) {
				if (!game.turn(data.getInt("selectedOption")).has("ok")) {
					sendError(client, MsgError.ILLEGALMOVE, null);
					doRound();
				}
				else {
					doRound();
				}
			}
			else {
				sendError(client, MsgError.NOTYOURTURN, null);
			}
		}

		// message (optional)
		else if (json.getString("type").equals(MsgType.MESSAGE.toString()) && (client.getState() == MsgType.READY || client.getState() == MsgType.REGISTER)) {
			if (data.getBoolean("broadcast")) {
				broadcastMessage(client, json.getString("message"));
			}
			else {
				LogController.log(Log.INFO, "Message from " + client + ": " + json.getString("message"));
			}
		}

		// error
		else if (json.get("type").equals(MsgType.ERROR.toString())) {
			LogController.log(Log.ERROR, "Error from " + client + ": " + json.get("type"));
			throw new IllegalArgumentException("Error message from client recieved");
		}
		
		// unsupported
		else {
			sendError(client, MsgError.UNSUPPORTEDMESSAGETYPE, null);
			throw new IllegalArgumentException("Message Type not supported or out of order");
		}
	}
	
	
	//Esegue il round del BOT
	private void doRound() {
		while (game.currentPlayerIsBot() && game.getState() == GameState.RUNNING) {
			broadcastUpdate();
			game.botTurn(1000);
		}
		broadcastUpdate();
		if (game.getState() == GameState.RUNNING) {
			sendTurn();
		}
	}
	
	//Decodifica i colori dei giocatori
	private PlayerColor decodeColor(String color) {
		switch(color) {
		case "red": return PlayerColor.RED;
		case "blue": return PlayerColor.BLUE;
		case "green": return PlayerColor.GREEN;
		case "yellow": return PlayerColor.YELLOW;
		default: return null;
		}
	}
	
	//Invia un errore al client
	private void sendError(ClientThread client, MsgError error, String message) {
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		json.put("type", MsgType.ERROR.toString());
		if (message != null) {
			json.put("message", message);
		}
		data.put("error", error.toString());
		json.put("data", data);
		client.out(json.toString());
	}
	
	
}
