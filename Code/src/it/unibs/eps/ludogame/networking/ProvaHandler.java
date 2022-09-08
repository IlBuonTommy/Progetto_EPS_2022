package it.unibs.eps.ludogame.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

import it.unibs.eps.ludogame.client.Posizione;
import it.unibs.eps.ludogame.game.Casella;
import it.unibs.eps.ludogame.game.GameModel;

public class ProvaHandler implements Runnable {
	private String nome;
	private Socket client;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private PrintWriter stampa;
	private BufferedReader leggi;
	private GameModel threadModel;
	private boolean primoRunSuperato = false;
	private ProvaServer server;

	public ProvaHandler(Socket client, ProvaServer server) {
		this.client = client;
		this.server = server;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public GameModel getThreadModel() {
		return threadModel;
	}

	public void setThreadModel(GameModel threadModel) {
		this.threadModel = threadModel;
	}

	public boolean isPrimoRunSuperato() {
		return primoRunSuperato;
	}

	public void setPrimoRunSuperato(boolean primoRunSuperato) {
		this.primoRunSuperato = primoRunSuperato;
	}

	public void comunicazione() {
		try {
			System.out.println(threadModel.toString());
			Pacchetto p = (Pacchetto) in.readObject();
			String tipoOggetto = p.getType();
			if (tipoOggetto.equals("model")) {
				System.out.println("sono arrivato nellla rich modl");

				out.writeObject(threadModel);
				out.flush();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub

		try {
			out = new ObjectOutputStream(client.getOutputStream());
			in = new ObjectInputStream(client.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.flush();
			setNome((String) in.readObject());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("runnato");

	}

	public void disabilitaTasti() {
		// TODO Auto-generated method stub
		Pacchetto p = new Pacchetto("disable", null);
		try {
			out.writeObject(p);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void resettaFrame() {
		Pacchetto p = new Pacchetto("repaint", null);
		try {
			out.writeObject(p);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setVincitore(int vincitore) {
		Pacchetto p = new Pacchetto("setVincitore", vincitore);
		try {
			out.writeObject(p);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setBase(Casella[][] base) {
		
		try {
			for(int i=0;i<base.length;i++) {
				for(int j=0;j<base.length;j++) {
					Pacchetto p = new Pacchetto("setBase",base[i][j].getColore());
					out.writeObject(p);
					out.flush();
				}
				
			}
			//System.out.println("numero mandato!");
		} catch (IOException e) {
			e.printStackTrace();
	/*	Pacchetto p = new Pacchetto("setBase", base);
		for(int i=0;i<base.length;i++) {
			for(int j=0;j<base.length;j++) {
				System.out.println("BASE MANDATA:" + base[i][j].getColore());
			}
		}
		
		try {
			out.writeObject(p);
			out.flush();
			//System.out.println("numero mandato!");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	}
	public void setFinale(Casella[][] finale) {
		Pacchetto p = new Pacchetto("setFinale", finale);
		try {
			out.writeObject(p);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setPlanciaColore(Casella[] plancia) {

		try {
			for(int i=0;i<plancia.length;i++) {
				
					Pacchetto p = new Pacchetto("setPlanciaColore",plancia[i].getColore());
					out.writeObject(p);
					out.flush();

				
			}
			//System.out.println("numero mandato!");
		} catch (IOException e) {
			e.printStackTrace();	
		}
	}
		public void setPlanciaDoppio(Casella[] plancia) {

			try {
				for(int i=0;i<plancia.length;i++) {
					
						Pacchetto p = new Pacchetto("setPlanciaDoppio",plancia[i].getDoppio());
						out.writeObject(p);
						out.flush();

					
				}
				//System.out.println("numero mandato!");
			} catch (IOException e) {
				e.printStackTrace();	
		}
		/*
		Pacchetto p = new Pacchetto("setPlancia", plancia);
		try {
			out.writeObject(p);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	

	public void setTurno(int turno) {
		System.out.println("set turno))");
		Pacchetto p = new Pacchetto("setTurno", turno);
		try {
			System.out.println("pacchetto turno: " + p.toString());
			out.writeObject(p);
			System.out.println("mandato");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setDado(int dado) {
		Pacchetto p = new Pacchetto("setDado", dado);
		try {
			out.writeObject(p);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setUpdateModel(GameModel model) {
		System.out.println("UPDATE MODEL SERVER");
		Pacchetto p = new Pacchetto("setModel", model);
		try {
			out.writeObject(p);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendListaPos(Posizione[] listapos) {
		Pacchetto p = new Pacchetto("setPosizioni", listapos);
		try {
			out.writeObject(p);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public void receiveDadoFromClient() {
		try {
			System.out.println("inizio ad aspettare dado");
			int dado = (int) in.readObject();
			System.out.println("ricevuto dado");
			server.receiveDado(dado);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void receivePosizioneFromClient() {
		try {
			System.out.println("inizio ad aspettare posizione");
			Posizione pos = (Posizione) in.readObject();
			
			server.receivePosition(pos);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setCurrentPlayer(int currentPlayer) {
		Pacchetto p = new Pacchetto("currentPlayer", currentPlayer);
		System.out.println("CurrentPLayer: "+ currentPlayer);
		try {
			out.writeObject(p);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
