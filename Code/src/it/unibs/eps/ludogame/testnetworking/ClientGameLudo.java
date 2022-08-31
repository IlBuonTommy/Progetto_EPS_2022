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
	public GameModel model = null;
	private boolean isMyTurn = true;
	public void premutoTasto() {
		//chiuedere finestra
		//aprire finestra client
	}
	
	public void sendUpdatedModel(GameModel modelAggiornato) {
		if(isMyTurn) {
			try {
				System.out.println("Inserisci modifica giocatori:");
				Scanner console = new Scanner(System.in);
				int numAggiornato = console.nextInt();
				if(numAggiornato>10) {
					modelAggiornato.numGiocatori = numAggiornato;
					out.writeObject(modelAggiornato);
				}
				
				//System.out.println(model.toString());
				isMyTurn = false;
			} catch (IOException e) {
				System.out.println("eccezione dal client--> spedizione model, dettaglio: "+ e.getMessage());

		}
		}
	}
	
	public void connetti(){
		System.out.println("Client in esecuzione...");
		try {
			clientSocket = new Socket("localhost",port);
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
			//out.writeUTF("Alessio");
			//out.writeObject(nomeGiocatore);
			model = (GameModel)in.readObject();
			System.out.println("Model ricevuto:"+ model.toString());
			//out.writeObject(nomeGiocatore);
			while(true) {
				sendUpdatedModel(model);
				//System.out.println("sono dentro");
				out.writeObject(model);
				model = (GameModel)in.readObject();
				System.out.println(model.toString());
						
			}
			
		}catch(IOException ex) {
			System.out.println("exc dal client");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
