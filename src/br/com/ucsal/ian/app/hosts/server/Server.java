package br.com.ucsal.ian.app.hosts.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public abstract class Server {

	public static List<ServerThread> threads;	
	private static ServerSession session;
	private ServerSocket serverSocket;
	
	
	protected Server createServer(int port) throws IOException {
		this.serverSocket = new ServerSocket(port);
		session = new ServerSession().fill();
		threads = new ArrayList<ServerThread>();
		return this;
	}
	
	public void multiThread(Runnable app) throws IOException {
		while (true) {			
			Socket clientConnected= serverSocket.accept();
			ServerThread serverThread = new ServerThread(clientConnected,app);
			threads.add(serverThread);
			serverThread.start();
		}
	}
	
	public void multiThread() throws IOException {
		while (true) {			
			Socket clientConnected= serverSocket.accept();
			ServerThread serverThread = new ServerThread(clientConnected);
			threads.add(serverThread);
			serverThread.start();
		}
	}
	
	public static List<List<Integer>> getData() {
		return session.get();
	}
	
	public static void setData(String positions,int value) {
		session.set(positions,value);
	}
	
	

}
