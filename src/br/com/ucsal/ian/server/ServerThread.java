package br.com.ucsal.ian.server;

import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {

	private ServerHandler server;
	private Socket socket;

	public ServerThread(Socket socket) throws IOException {
		super();
		this.socket = socket;
		this.server = new ServerHandler(socket);
	}
	
	public void send(String data) {
		server.sendDataToClient(data);
	}
	
	public String read() {
		return server.readDataFromClient();
	}
	
	public Socket getSocket() {
		return server.getSocket();
	}
	
	@Override
	public void run() {
		try {
				
				while (true) {
					//this.server = new ServerHandler(socket);
					//read();
				}
				
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	
}
