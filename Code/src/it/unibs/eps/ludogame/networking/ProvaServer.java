package it.unibs.eps.ludogame.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.unibs.eps.ludogame.client.HostWaitingRoom;
import it.unibs.eps.ludogame.client.MainFrame;
import it.unibs.eps.ludogame.client.Posizione;
import it.unibs.eps.ludogame.client.Sconfitta;
import it.unibs.eps.ludogame.client.Vittoria;
import it.unibs.eps.ludogame.game.GameModel;
import it.unibs.eps.ludogame.game.GestionePartita;
import it.unibs.eps.ludogame.game.Giocatore;

public class ProvaServer {

	
		private Socket client;
		private static final int PORT = 50358;
		private ServerSocket listener;
		private  ExecutorService pool;
		private int numMaxGiocatori;
		private HostWaitingRoom frameWaiting;
		private  Giocatore[] listaGiocatori;
		private  ArrayList<ProvaHandler> clients = new ArrayList<>();
		private String[] listaBot = {"giovanninoBot","antoniettaBot","rinaldoBot"};
		private ProvaHandler clientHandler;
		private boolean partitaAvviata = false;
		private String nomeServer;
		private GameModel serverModel;
		private MainFrame framePrincipale;
		private int nGiocatoriConnessi=0;
		private GestionePartita gestione;
		public ProvaServer(int numMaxGiocatori, HostWaitingRoom frameWaiting, String nomeServer) {
			this.numMaxGiocatori = numMaxGiocatori;
			this.frameWaiting = frameWaiting;
			this.nomeServer = nomeServer;
			this.listaGiocatori = new Giocatore[numMaxGiocatori];
			this.listaGiocatori[0] = new Giocatore(0,nomeServer,false);
			try {
				listener = new ServerSocket(PORT);
				pool = Executors.newFixedThreadPool(numMaxGiocatori);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frameWaiting.setS(this);
		}
		
		
		public boolean isPartitaAvviata() {
			return partitaAvviata;
		}


		public void setPartitaAvviata(boolean partitaAvviata) {
			this.partitaAvviata = partitaAvviata;
		}


		public synchronized boolean accettaConnessioni() {
			if (nGiocatoriConnessi < numMaxGiocatori && partitaAvviata == false) {
				System.out.println("[SERVER]: waiting for client connection");
				try {
					client = listener.accept();
					System.out.println("accettato");
					clientHandler = new ProvaHandler(client,this);
					System.out.println("handler");
					clients.add(clientHandler);
					System.out.println("add");
					pool.execute(clientHandler);
					System.out.println("arrivato qui");
					//connectedClients.add(client);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				nGiocatoriConnessi++;
				return true;
			}else {
				System.out.println("Numero massimo di giocatori raggiunto.Inizia la partita");
				return false;
				
			}
		}
			/*	if(nGiocatoriConnessi<numMaxGiocatori-1) {
					try {
						Socket client = listener.accept();
						
						System.out.println("connesso");
						ProvaHandler p = new ProvaHandler(client);
						pool.execute(p);
						wait(1000);
						//Thread t = new Thread(p);
						//t.run();
						System.out.println("NOME:"+ p.getNome());
						frameWaiting.addPlayer(p.getNome());
						return true;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					System.out.println("Numero massimo di giocatori raggiunto.Inizia la partita");
					return false;
					
				}
			*/
		public void allClientSendModel() {
			for (ProvaHandler c : clients) {
				c.setThreadModel(serverModel);
				c.comunicazione();

			}
		}
		
		public void setWinner(int vincitore) {
			
			for (ProvaHandler c : clients) {
				c.setVincitore(vincitore);
			}
			
			framePrincipale.dispose();
			if(vincitore==0) {
				Vittoria vittoria=new Vittoria();
				vittoria.setVisible(true);
				vittoria.setLocationRelativeTo(null);
			}else {
				String nome=serverModel.getPlayer()[vincitore].getUsername();
				Sconfitta sconfitta=new Sconfitta(nome);
				sconfitta.setVisible(true);
				sconfitta.setLocationRelativeTo(null);
			}
			
			
			
		}
		
		public void sendModelInGame() {
			for (ProvaHandler c : clients) {
				c.setUpdateModel(serverModel);
				c.resettaFrame();
			}
			framePrincipale.resetta(serverModel.getBase(), serverModel.getFinale(), serverModel.getPlancia(), serverModel.getCurrentPlayerIndex());
			if(serverModel.getCurrentPlayerIndex()==0){
				framePrincipale.enableRoll();
			}
		}
		
		public void sendDado(int dado) {
			for (ProvaHandler c : clients) {
				c.setDado(dado);

			}
		}
		
		
		public void starTurnoDue(int dado) {
			System.out.println("ricevuto dado sever");
			gestione.gestioneTurnoDue(dado);
		}
		
		public void sendMovePosition(int dado) {
			Posizione[] listapos=serverModel.getTastiAbilitati(dado);
			
			if(listapos[0] != null) {
				int currentPlayer=serverModel.getCurrentPlayerIndex();
				if(currentPlayer==0) {
					framePrincipale.enableBoardButtons(listapos);
				}else {
					clients.get(currentPlayer-1).sendListaPos(listapos);
				}
			}else {
				gestione.gestioneTurnoQuattro();
			}
			
		}
		
		public void startTurnoTre(Posizione p) {
			gestione.gestioneTurnoTre(p);
		}
		
		public void disableAllButton() {
			int currentPlayer=serverModel.getCurrentPlayerIndex();
			if(currentPlayer==0) {
				framePrincipale.disableAllButtons();
			}else {
				clients.get(currentPlayer-1).disabilitaTasti();
			}
		}
		
		public void gestioneTurnoIniziale(){
			//creo il model con i dati dei player che mi hanno passato le view
			//il model viene inviato a tutti i client
			serverModel = new GameModel(numMaxGiocatori,listaGiocatori);	
			allClientSendModel();
			for (ProvaHandler c : clients) {
				c.disabilitaTasti();
				c.resettaFrame();
			}
			gestione = new GestionePartita(this);
			gestione.gestioneTurnoUno();
			
		}
		
		//abbozziamo
		public void receiveDado(int dado) {
			gestione.gestioneTurnoDue(dado);
		}
		
		public void startWaitingDado() {
			int currentPlayer=serverModel.getCurrentPlayerIndex();
			if(currentPlayer!=0) {
				clients.get(currentPlayer-1).receiveDadoFromClient();
			}
		}
		
		public void settaTurnoClient() {
			int currentPlayer=serverModel.getCurrentPlayerIndex();
			System.out.println("INDEX SERVER:" + currentPlayer);
			if(currentPlayer!=0) {
				clients.get(currentPlayer-1).setTurno(currentPlayer);
			}
		}
		
		public void inizioGame() {
			framePrincipale.disableAllButtons();
			framePrincipale.resetta(serverModel.getBase(), serverModel.getFinale(), serverModel.getPlancia(),0);
			framePrincipale.enableRoll();
		}
		
		
		public GameModel getServerModel() {
			return serverModel;
		}


		public synchronized void avvia() {
				System.out.println("[SERVER]: avviato correttamente.");
				//createModel();
				int n=1;
				while(true) {
					System.out.println("ci sono");
					if(accettaConnessioni()) {
						System.out.println("[SERVER]: new client connected");
						try {
							wait(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						listaGiocatori[n] = new Giocatore(n,clients.get(clients.size()-1).getNome(),false);
						System.out.println("NOMELISTA:"+ listaGiocatori[n].getUsername());
						frameWaiting.addPlayer(listaGiocatori[n].getUsername());
						
						n++;
					}else {
						if(n == numMaxGiocatori) {
							//gestioneTurnoIniziale();
							break;
						}
					}
				}	//fine ciclo
				System.out.println("finito ciclo");
				
		}
		
		public void creaMainFrame() {
			// TODO Auto-generated method stub
			String[] listaGiocatori = new String[serverModel.getPlayer().length];

			for(int i = 0;i<listaGiocatori.length;i++) {
				listaGiocatori[i] = serverModel.getPlayer()[i].getUsername();
			}
			 framePrincipale = new MainFrame(listaGiocatori,null,this);
			framePrincipale.setVisible(true);
			framePrincipale.setLocationRelativeTo(null);
			
		}

		public void creaBot() {
			// TODO Auto-generated method stub
			if(nGiocatoriConnessi < numMaxGiocatori) {
				for(int i=nGiocatoriConnessi+1;i<(numMaxGiocatori);i++) {
					listaGiocatori[i] = new Giocatore(i,listaBot[numMaxGiocatori-i-1],true);
					
				}
			}
		}


		public void receivePosition(Posizione pos) {
			// TODO Auto-generated method stub
			gestione.gestioneTurnoTre(pos);
		}
		
		public void startWaitingPosition() {
			int currentPlayer=serverModel.getCurrentPlayerIndex();
			if(currentPlayer!=0) {
				clients.get(currentPlayer-1).receivePosizioneFromClient();
			}
		}
		
		


}
