package it.unibs.eps.ludogame.networking;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import it.unibs.eps.ludogame.client.Posizione;
import it.unibs.eps.ludogame.client.Posizione.NomePosizione;
import it.unibs.eps.ludogame.game.Casella;
import it.unibs.eps.ludogame.game.GameModel;
import it.unibs.eps.ludogame.game.Giocatore;
import it.unibs.eps.ludogame.testnetworking.backup.v2.Pacchetto;

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
	private int valoreDado;
	private Posizione userInput;
	private boolean needUpdate;
	private boolean daEseguire;

	public ServerThread(Socket socket) {
		this.client = socket;

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
	
	
	

	public int getValoreDado() {
		return valoreDado;
	}

	public void setValoreDado(int valoreDado) {
		this.valoreDado = valoreDado;
	}
	public Posizione getUserInput() {
		return userInput;
	}

	public void setUserInput(Posizione userInput) {
		this.userInput = userInput;
	}

	public void updateModel(Posizione userInput) {
		// aggiorno il server model usando il metodo movimento da...
		serverModel.tastoPremuto(userInput.getNomeposizione(),userInput.getColor() ,userInput.getArrayposizione(),valoreDado);
		needUpdate = true;
	}


	public synchronized void run() {
		try {
			System.out.println("Client connesso");

			inDalClient = new ObjectInputStream(client.getInputStream());
			outVersoClient = new ObjectOutputStream(client.getOutputStream());
		//	setNomeGiocatore((String)inDalClient.readObject());
			outVersoClient.writeObject(serverModel);
			while (true) {
				
				Pacchetto pacchettoRicevuto = (Pacchetto)inDalClient.readObject();
				//Pacchetto pacchettoRicevuto = inDal;
				switch(pacchettoRicevuto.getType()) {
					case "posizione":	userInput = (Posizione)pacchettoRicevuto.getMessage();
										needUpdate=true;
										//updateModel(userInput);
										outVersoClient.writeObject(serverModel);break;
					case "dado":  		outVersoClient.writeObject(valoreDado);break;
					case "model":		outVersoClient.writeObject(serverModel);
					default: System.out.println("errore"); 
				}
			/*	if (pacchettoRicevuto.getType().equals("posizione")) {
					userInput = (Posizione)pacchettoRicevuto.getMessage();
					updateModel(userInput);
					outVersoClient.writeObject(serverModel);
				}*/

			}

			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
}