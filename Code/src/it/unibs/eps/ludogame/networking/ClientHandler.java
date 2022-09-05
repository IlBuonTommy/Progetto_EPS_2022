package it.unibs.eps.ludogame.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import it.unibs.eps.ludogame.client.Posizione;
import it.unibs.eps.ludogame.game.GameModel;
import it.unibs.eps.ludogame.game.Giocatore;
import networking.ultimaprova.Server;
public class ClientHandler implements Runnable{
	private Socket client;
	private int valoreDadoS;
	private GameModel model;
	private int numeroDado;
	private int numMaxGiocatori;
	private Giocatore[] listaGiocatori;
	private String nomeGiocatore = "alessio";
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private ArrayList<ClientHandler> clients;
	
	public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException {
		this.client = clientSocket;
		this.clients = clients;
		//in = new ObjectInputStream(client.getInputStream());
		//out = new ObjectOutputStream(client.getOutputStream());
		
	}
	
	
	public String getNomeGiocatore() {
		return nomeGiocatore;
	}


	public void setNomeGiocatore(String nomeGiocatore) {
		this.nomeGiocatore = nomeGiocatore;
	}
	
	

	public int getValoreDadoS() {
		return valoreDadoS;
	}


	public void setValoreDadoS(int valoreDadoS) {
		this.valoreDadoS = valoreDadoS;
	}


	public void allClientUpdate() {
		for (ClientHandler c : clients) {
			model = c.model;
		}
		for (ClientHandler c : clients) {
			c.model = model;
		}
	}
	public void diceToAll(int valoreDado) {
		for (ClientHandler c : clients) {
			c.setValoreDadoS(valoreDado);
		}
	}
	
	public void gestioneTurnoIniziale(){
		//creo il model con i dati dei player che mi hanno passato le view
		//il model viene inviato a tutti i client
		allClientUpdate();
		//System.out.println(model.toString());
		//gestioneTurnoUno();
	}
	public void gestioneTurnoUno(){
		if(model.currentIsBot()){
			//genero numero random da 1 a 6 e lo salvo su valoreDadoS
			//faccio fare il movimento al bot
			model.movimentoBOT(model.getCurrentPlayerIndex(), valoreDadoS);
			//aggiorno il model dei vari client e visualizzo quello nuovo
			allClientUpdate();
			gestioneTurnoQuattro();
		}else{
			//abilito il bottone di lancio dado del giocatore corrente
		}
	}
	//questa funzione è richiamata dal tasto lancia dado 
	public void gestioneTurnoDue(int valoreDado){
		valoreDadoS = valoreDado;
		//mando a tutti i client il valore del dado
		diceToAll(valoreDado);
		//mando alla view i bottoni che può abilitare QUESTO PASSAGGIO PUò ESSERE FATTO IN AUTONOMIA DAL PROGRAMMA DEL GIOCATORE
	}
	//questa funzione viene richiamata dalla pressione del bottone da parte di un giocatore
	public void gestioneTurnoTre(Posizione tastoPremuto){
		//modifico il model in base al tasto premuto del giocatore
		model.tastoPremuto(tastoPremuto.getNomeposizione(), tastoPremuto.getColor(), tastoPremuto.getArrayposizione(), valoreDadoS);

		if(!model.tastoPremuto(tastoPremuto.getNomeposizione(),tastoPremuto.getArrayposizione(), tastoPremuto.getColor(), valoreDadoS)){
			gestioneTurnoDue(valoreDadoS);
			return;
		}
		//disabilito tutti i tasti al giocatore corrente
		//invio a tutti i giocatori il nuovo model e lo visualizzo
		allClientUpdate();
		gestioneTurnoQuattro();
	}
	public void gestioneTurnoQuattro(){
		if(model.checkWin()!=-1){
			//il gioco finisce chiudiamo le connessioni e tutti a baita
			System.out.println("Partita terminata, Complimenti ha vinto il giocatore: "+ model.getPlayer()[model.checkWin()].getUsername());
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		if(valoreDadoS==6){
			gestioneTurnoUno();
			return;
		}
		model.nextTurn();
		gestioneTurnoUno();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	
			nomeGiocatore = "alessio";
			System.out.println("run partito");
			//nomeGiocatore = (String)in.readObject();
			
			//out.writeObject(model);
			/*while (true) {
				Pacchetto pacchettoRicevuto = (Pacchetto)in.readObject();
				switch(pacchettoRicevuto.getType()) {
				case "nome": out.writeObject(nomeGiocatore);
								//System.out.println("[client handler]: nomeGiocatore "+ nomeGiocatore);
							   gestioneTurnoIniziale();break;
				case "dado":    out.writeObject(5);
								diceToAll(5);break;			   
				default: System.out.println("errore"); 
			}
				
				//out.writeObject(model);
			}*/
			
			

		
	}

}
