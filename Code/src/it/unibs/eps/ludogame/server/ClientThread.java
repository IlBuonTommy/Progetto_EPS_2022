package it.unibs.eps.ludogame.server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;



public class ClientThread implements Runnable {
	private Socket socket;
	private ServerController controller;
	private Scanner in;
	private PrintWriter out;
	private String bufferIn;
	
	ClientThread(Socket socket, ServerController controller) {
		this.socket = socket;
		this.controller = controller;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
