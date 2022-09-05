package it.unibs.eps.ludogame.networking;

import java.net.Socket;

public class ProvaHandler implements Runnable{
	private String nome;
	private Socket client;
	
	public ProvaHandler(Socket client) {
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
		System.out.println("runnato");
		setNome("paolo");
		
	}
	
	

}
