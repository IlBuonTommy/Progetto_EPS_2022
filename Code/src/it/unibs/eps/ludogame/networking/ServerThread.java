package it.unibs.eps.ludogame.networking;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import it.unibs.eps.ludogame.game.Casella;
import it.unibs.eps.ludogame.game.GameModel;
import it.unibs.eps.ludogame.game.Giocatore;

class ServerThread implements Runnable {
	ServerSocket server = null;
	Socket client = null;
	String stringaRicevuta = null;
	String stringaModificata = null;
	ObjectInputStream inDalClient;
	ObjectOutputStream outVersoClient;
	GameModel serverModel;
	private int numGiocatori;
	private Giocatore player[];
	private String nomeGiocatore = "";
	private String userInput;
	private boolean needUpdate;

	public ServerThread(Socket socket) {
		this.client = socket;
		// nomeGiocatore = "alessio";

	}

	public String getNomeGiocatore() {
		return nomeGiocatore;
	}

	public void setNomeGiocatore(String nomeGiocatore) {
		this.nomeGiocatore = nomeGiocatore;
	}

	public boolean isNeedUpdate() {
		return needUpdate;
	}

	public void setNeedUpdate(boolean needUpdate) {
		this.needUpdate = needUpdate;
	}

	public void updateModel(String userInput) {
		// aggiorno il server model usando il metodo movimento da...
		needUpdate = true;
	}


	public void run() {
		try {
			System.out.println("Client connesso");

			inDalClient = new ObjectInputStream(client.getInputStream());
			outVersoClient = new ObjectOutputStream(client.getOutputStream());
			outVersoClient.writeObject(serverModel);
			while (true) {
				Object clientInput = inDalClient.readObject();
				
				if (clientInput instanceof String) {
					userInput = (String) clientInput;
					updateModel(userInput);
					outVersoClient.writeObject(serverModel);
				}

			}

			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
}