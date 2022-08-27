package it.unibs.eps.ludogame.testnetworking;
import java.io.PrintWriter;
import java.net.Socket;

import it.unibs.eps.ludogame.game.GameModel;
import it.unibs.eps.ludogame.game.Giocatore;

public class ClientController extends MultiClient{
	private Giocatore giocatoreLocale;
	private GameModel model;
	
	public ClientController(GameModel model){
		super();
		this.model = model;
	}
	
	public boolean connect(String serverAddress, int serverPort) {
		try {
			client = new Socket(serverAddress, serverPort);
			new Thread().start();
			outVersoServer = new PrintWriter(client.getOutputStream(), true);
			sendMsg(this, "Connesso");
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean isMyTurn(Giocatore p) {
		Giocatore currentPlayer = model.getCurrentPlayer();
		return currentPlayer != null && currentPlayer == p;
	}
	
	public void checkVincita() {
		if(model.ControlloVincitaTempDebug() != -1) {
			sendMsg(this,"Gioco terminato, ha vinto il giocatore:"+ model.ControlloVincitaTempDebug() + "Nome");
			isRunning = false;
		}else {
			isRunning = true;
		}
	}
	

}
