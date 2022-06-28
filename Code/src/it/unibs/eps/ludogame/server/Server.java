package it.unibs.eps.ludogame.server;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
	private ServerSocket serverSocket;
	private static Integer port = 1234;
	
	public static void main(String[] args) throws Exception {
		Server server = new Server();
		server.start(port);
	}
	
	private void start(Integer port) {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println(">Server avviato..." + serverSocket);
		//	LogController.log(Log.INFO, "Running: " + serverSocket);
		//	ServerController controller = new ServerController();
			Controllore controller = new Controllore();
			ExecutorService  pool = Executors.newFixedThreadPool(8);
			while (true) {
				pool.execute(new ClientThread2(serverSocket.accept(), controller));
			}
		}
		catch (Exception e) { /*LogController.log(Log.ERROR, e.toString());*/ }
	}

}
