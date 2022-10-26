package br.com.ucsal.ian.app.hosts.server;

import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {

	private Runnable app;
	private ServerHandler server;
	private Socket socket;

	public ServerThread(Socket socket,Runnable app) throws IOException {
		super();
		this.app = app;
		this.socket = socket;
		this.server = new ServerHandler(socket);
	}
	
	public ServerThread(Socket socket) throws IOException {
		super();
		this.app = null;
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
			if(app != null) app.run();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	
}
