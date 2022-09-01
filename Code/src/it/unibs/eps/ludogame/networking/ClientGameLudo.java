package it.unibs.eps.ludogame.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import it.unibs.eps.ludogame.game.GameModel;

public class ClientGameLudo {
	private Socket clientSocket;
	private static Integer port = 50358;
	private String nomeGiocatore = "paolo";
	private String ipServer;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private GameModel model = null;
	private boolean isMyTurn = true;
	private String posizioneUtente = "ciao";

	public void premutoTasto() {
		// chiuedere finestra
		// aprire finestra client
	}

	public void sendUserInput(String posizione) {
		try {
			
			out.writeObject(posizione);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void connetti() {
		System.out.println("Client in esecuzione...");
		try {
			clientSocket = new Socket("localhost", port);
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());

			model = (GameModel) in.readObject();
			System.out.println("Model ricevuto:" + model.toString());

			while (model.ControlloVincitaTempDebug() == -1) {
				// System.out.println("imin");
				if(isMyTurn) {
					sendUserInput(posizioneUtente);
				}
				

			}

		} catch (IOException ex) {
			System.out.println("exc dal client");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
