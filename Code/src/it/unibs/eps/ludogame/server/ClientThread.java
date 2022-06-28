package it.unibs.eps.ludogame.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import it.unibs.eps.ludogame.game.*;


public class ClientThread implements Runnable {
	private Socket socket;
	private ServerController controller;
	private Scanner in;
	private PrintWriter out;
	private String bufferIn;
	private MsgType state = MsgType.WELCOME;
	
	ClientThread(Socket socket, ServerController controller) {
		this.socket = socket;
		this.controller = controller;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			in = new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream(), true);
	        controller.sendWelcome(this);
			
			while (in.hasNextLine()) {
				bufferIn = in.nextLine();
				controller.decoder(this, bufferIn);
			}
		}
		catch (Exception e) { e.getStackTrace(); }
		finally {
			try {
				in.close();
				out.close();
				socket.close();
			}
			catch (IOException e) { e.getStackTrace();}
			System.out.println("CLIENT DISCONNECTED: " + socket);
			controller.disconnect(this);
		}
	}
	
	//manda stringa in output
	protected void out(String data) {
		out.println(data);
	}
	
	//Setta lo stato del client
	protected void setState(MsgType newState) {
		 state = newState;
	}
	
	//get dello stato del client
	protected MsgType getState() {
		return state;
	}
	
	

}
