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

public class ProvaHandler implements Runnable{
	private String nome;
	private Socket client;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private PrintWriter stampa;
	private BufferedReader leggi;
	private GameModel threadModel;
	private boolean primoRunSuperato=false; 
	private ProvaServer server;
	public ProvaHandler(Socket client,ProvaServer server)  {
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
				Pacchetto p = (Pacchetto)in.readObject();
				String tipoOggetto = p.getType();
				if(tipoOggetto.equals("model")) {
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
				setNome((String)in.readObject());
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("runnato");
			
	
	}

	public void disabilitaTasti() {
		// TODO Auto-generated method stub
		Pacchetto p = new Pacchetto("disable",null);
		try {
			out.writeObject(p);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void resettaFrame() {
		Pacchetto p = new Pacchetto("repaint",null);
		try {
			out.writeObject(p);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setTurno(int turno) {
		Pacchetto p = new Pacchetto("setTurno",turno);
		try {
			out.writeObject(p);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setDado(int dado) {
		Pacchetto p = new Pacchetto("setDado",dado);
		try {
			out.writeObject(p);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setUpdateModel(GameModel model) {
		Pacchetto p = new Pacchetto("setModel",model);
		try {
			out.writeObject(p);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendListaPos(Posizione[] listapos) {
		Pacchetto p = new Pacchetto("setPosizioni",listapos);
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
			int dado =(int)in.readObject();
			System.out.println("ricevuto dado");
			server.receiveDado(dado);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void receivePosizioneFromClient() {
		try {
			Posizione pos =(Posizione)in.readObject();
			server.receivePosition(pos);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
