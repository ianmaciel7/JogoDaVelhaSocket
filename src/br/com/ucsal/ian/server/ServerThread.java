package br.com.ucsal.ian.server;

import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {

	private Server server;
	private Socket socket;

	public ServerThread(Socket socket) throws IOException {
		super();
		this.socket = socket;
		this.server = new Server(socket);
		ServerMain.connections.add(this);
		
	}
	
	public void sendDataToClient(String data) {
		server.sendDataToClient(data);
	}
	
	public String readDataFromClient() {
		return server.readDataFromClient();
	}
	
	public Socket getSocket() {
		return server.getSocket();
	}
	
	public void refreshServer(Server server) {
		this.server = server;
	}

	@Override
	public void run() {
		try {
				
				new PrintReceivedDataFromClientThread(this).start();
				//new SendDataToClientThread(this).start();
				
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	
}
