package it.unibs.eps.ludogame.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

import it.unibs.eps.ludogame.client.ClientWaitingRoom;
import it.unibs.eps.ludogame.client.MainFrame;
import it.unibs.eps.ludogame.client.Posizione;
import it.unibs.eps.ludogame.client.Sconfitta;
import it.unibs.eps.ludogame.client.Vittoria;
import it.unibs.eps.ludogame.game.Casella;
import it.unibs.eps.ludogame.game.GameModel;

public class ProvaClient {
	private  String SERVER_IP;
	private static final int SERVER_PORT = 50358;
	private  Socket clientSocket;
	private  String playerName;
	private  ObjectOutputStream out;
	private  ObjectInputStream in;
	private PrintWriter stampa;
	private BufferedReader leggi;
	private  GameModel modelClient = null;
	private Casella[] plancia;
	private Casella[][] base;
	private Casella[][] finale;
	private int playerIndex;
	private Posizione posizioneUtente;
	private  int valoreDado;
	private ClientWaitingRoom clientFrame;
	private MainFrame framePrincipale;
	int i=0;
	int j=0;
	int iPlancia=0;
	public ProvaClient(String serverIp,String playerName, ClientWaitingRoom clientframe) {
		this.SERVER_IP = serverIp;
		this.playerName = playerName;
		this.clientFrame = clientframe;
		 // this.plancia = new Casella[40];
	        this.base = new Casella[4][4];
	        
	       // this.finale = new Casella[4][4];
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
				System.out.println(e.getMessage());
				e.printStackTrace();
				return false;
			}

		
	}
	
	public void richiestaModelIniziale() {
		Pacchetto p = new Pacchetto("model",null);
		try {
			out.writeObject(p);
			out.flush();
			modelClient = (GameModel)in.readObject();
			System.out.println("Model client ricevuto: " + modelClient.toString());
			base = modelClient.getBase();
			//finale = modelClient.getFinale();
			plancia = modelClient.getPlancia();
			//playerIndex = modelClient.getCurrentPlayerIndex();
			for(int i=0;i<modelClient.getPlayer().length;i++) {
				if(this.playerName.equals(modelClient.getPlayer()[i].getUsername())) {
					this.playerIndex=i;
					System.out.println("Player Index di "+this.playerName +" : "+this.playerIndex);
				}
			}
			//Avvia view
			this.avviaMainFrame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void comunica() {

				try {
					out = new ObjectOutputStream(clientSocket.getOutputStream());
					in = new ObjectInputStream(clientSocket.getInputStream());
					out.flush();
					out.writeObject(playerName);
					out.flush();
					System.out.println("mandato");
					//richiestaModelIniziale();
					//Avviare View
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public void comunicazioneInGameDaServer() {
		try {
			while(true) {
			Pacchetto p = (Pacchetto)in.readObject();
			String tipoRicevuto = p.getType();
				if(tipoRicevuto.equals("disable")) {
					System.out.println("sono disabile");
					framePrincipale.disableAllButtons();
					
				}
				
				if(tipoRicevuto.equals("repaint")) {
					
					framePrincipale.resetta(base, modelClient.getFinale(), plancia, playerIndex);
					//framePrincipale.resetta(base,finale,plancia,playerIndex);
					
					
					//framePrincipale.repaint();
				}
				
				if(tipoRicevuto.equals("setTurno")) {
					System.out.println("sono arrivato nel set turno");
					System.out.println("index: " + this.playerIndex);
					framePrincipale.setTurno((int)p.getMessage());
					if((int)p.getMessage()==this.playerIndex) {
						framePrincipale.enableRoll();
					}
					//framePrincipale.resetta(modelClient.getBase(), modelClient.getFinale(), modelClient.getPlancia(),this.playerIndex );
				}
				
				if(tipoRicevuto.equals("setDado")) {
					framePrincipale.setDado((int)p.getMessage());
				}
				
				if(tipoRicevuto.equals("setModel")) {
					modelClient = (GameModel)p.getMessage();
					//framePrincipale.resetta(modelClient.getBase(), modelClient.getFinale(), modelClient.getPlancia(),this.playerIndex );
					
					System.out.println("CLIENT MODEL: " + modelClient.toString());
				}
				
				if(tipoRicevuto.equals("setPosizioni")) {
					framePrincipale.enableBoardButtons((Posizione[])p.getMessage());
					
				}
				if(tipoRicevuto.equals("setVincitore")) {
					int vincitore=(int)p.getMessage();
					framePrincipale.dispose();
					if(vincitore==this.playerIndex) {
						Vittoria vittoria=new Vittoria();
						vittoria.setVisible(true);
						vittoria.setLocationRelativeTo(null);
					}else {
						String nome=modelClient.getPlayer()[vincitore].getUsername();
						Sconfitta sconfitta=new Sconfitta(nome);
						sconfitta.setVisible(true);
						sconfitta.setLocationRelativeTo(null);
					}
				}
				
				if(tipoRicevuto.equals("setBase")) {
					//System.out.println((int)p.getMessage());
					//for(int i=0;i<16;i++) {
					//base[i][j]=new Casella();
					base[i][j].setColore((int)p.getMessage());
					System.out.println("BASE COLORE RICEVUTO: " + base[i][j].getColore() );
					
					if(i==3 && j==3) {
						i=0;
						j=0;
					}else {
						i++;
						j++;
					}
						
						
					/*base = (Casella[][])p.getMessage();
					modelClient.setBase((Casella[][])p.getMessage());
					
					for(int i=0;i<modelClient.getBase().length;i++) {
						for(int j=0;j<modelClient.getBase().length;j++) {
							System.out.println("BASE RICEVUTA SENZA SET: " + base[i][j]);
							System.out.println("BASE RICEVUTA CON SET:" + modelClient.getBase()[i][j].getColore());
						}
					}*/
				//	base = (Casella[][])p.getMessage();
					//System.out.println(Arrays.toString(modelClient.getBase()));
					
				}
				if(tipoRicevuto.equals("setFinale")) {
					modelClient.setFinale((Casella[][])p.getMessage());
					//finale = (Casella[][])p.getMessage();
					System.out.println(Arrays.toString(modelClient.getFinale()));
					
				}
				if(tipoRicevuto.equals("setPlanciaColore")) {
					//modelClient.setPlancia((Casella[])p.getMessage());
					plancia[iPlancia].setColore((int)p.getMessage());
					//System.out.println(Arrays.toString(modelClient.getPlancia()));
					
				}
				if(tipoRicevuto.equals("setPlanciaDoppio")) {
					//modelClient.setPlancia((Casella[])p.getMessage());
					plancia[iPlancia].setDoppio((boolean)p.getMessage());
					if(iPlancia == 39) {
						iPlancia = 0;
					}else {
						iPlancia++;
					}
					//System.out.println(Arrays.toString(modelClient.getPlancia()));
					
				}
				if(tipoRicevuto.equals("setCurrentPlayer")) {
					
					//modelClient.setCurrentPlayerIndex((int)p.getMessage());
					playerIndex = (int)p.getMessage();
					//System.out.println("index: " + modelClient.getCurrentPlayerIndex());
					//framePrincipale.resetta(modelClient.getBase(), modelClient.getFinale(), modelClient.getPlancia(), modelClient.getCurrentPlayerIndex());
					
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public synchronized void sendDadoToServer(int n) {

		try {
			out.flush();
			out.writeObject(n);
			out.flush();
			System.out.println("mandato Dado");
			//richiestaModelIniziale();
			//Avviare View
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	


}

	
	public void avviaMainFrame() {
		String[] listaGiocatori = new String[modelClient.getPlayer().length];

		for(int i = 0;i<listaGiocatori.length;i++) {
			listaGiocatori[i] = modelClient.getPlayer()[i].getUsername();
		}
		
		framePrincipale = new MainFrame(listaGiocatori,this,null);
		framePrincipale.setVisible(true);
		framePrincipale.setLocationRelativeTo(null);
		comunicazioneInGameDaServer();
	/*	Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				comunicazioneInGameDaServer();
				
			}
			
		});
		t.run();*/
		
		clientFrame.closeFrame();
	
		
	}

	public void sendPositionToServer(Posizione p) {
		// TODO Auto-generated method stub

		try {
			out.flush();
			out.writeObject(p);
			out.flush();
			System.out.println("mandato posizione");
			//richiestaModelIniziale();
			//Avviare View
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}
	
}
