package it.unibs.eps.ludogame.testnetworking;

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
			// String rispostaOK = (String)in.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// }
	}

	public void sendUpdatedModel(GameModel modelAggiornato) {
		if (isMyTurn) {
			try {
				System.out.println("Inserisci modifica giocatori:");
				Scanner console = new Scanner(System.in);
				int numAggiornato = console.nextInt();
				if (numAggiornato > 10) {
					modelAggiornato.numGiocatori = numAggiornato;
					out.writeObject(modelAggiornato);
				}

				// System.out.println(model.toString());
				isMyTurn = false;
			} catch (IOException e) {
				System.out.println("eccezione dal client--> spedizione model, dettaglio: " + e.getMessage());

			}
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
				sendUserInput(posizioneUtente);

			}

		} catch (IOException ex) {
			System.out.println("exc dal client");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
