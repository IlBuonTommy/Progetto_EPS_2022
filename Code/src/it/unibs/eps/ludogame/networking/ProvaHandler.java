package it.unibs.eps.ludogame.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ProvaHandler implements Runnable{
	private String nome;
	private Socket client;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private PrintWriter stampa;
	private BufferedReader leggi;
	public ProvaHandler(Socket client)  {
		this.client = client;
	}

	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub

			//stampa = new PrintWriter(client.getOutputStream(),true);
				//leggi = new BufferedReader(new InputStreamReader(client.getInputStream()));
			setNome("alessio");
			System.out.println("runnato");
			System.out.println(nome);
		
		
		
		
	}
	
	

}
