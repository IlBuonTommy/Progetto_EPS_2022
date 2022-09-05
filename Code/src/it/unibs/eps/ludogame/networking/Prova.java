package it.unibs.eps.ludogame.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import it.unibs.eps.ludogame.client.Posizione;
import it.unibs.eps.ludogame.game.GameModel;

public class Prova {
	private  String SERVER_IP;
	private static final int SERVER_PORT = 50358;
	private  Socket clientSocket;
	private  String playerName;
	private  ObjectOutputStream out;
	private  ObjectInputStream in;
	private PrintWriter stampa;
	private BufferedReader leggi;
	private  GameModel model = null;
	private boolean isMyTurn = true;
	private Posizione posizioneUtente;
	private  int valoreDado;
	
	public Prova(String serverIp,String playerName) {
		this.SERVER_IP = serverIp;
		this.playerName = playerName;

		
		
	}
	
	public boolean checkConnection() {
		System.out.println("[CLIENT]: in esecuzione.");
	
		//	clientSocket = new Socket(SERVER_IP,SERVER_PORT);
			System.out.println("primacom");
			try {
				clientSocket = new Socket(SERVER_IP,SERVER_PORT);
				
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}

		
	}
	
	public void inizializza() {
		try {
			stampa = new PrintWriter(clientSocket.getOutputStream(),true);
			leggi = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("arrivato qui");
		stampa.println(playerName);
	}
	
	public void comunica() {

			if(checkConnection()) {
				//inizializza();
			}

		
	}
	
}
