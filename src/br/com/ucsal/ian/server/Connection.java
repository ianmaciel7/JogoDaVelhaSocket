package br.com.ucsal.ian.server;

import java.net.Socket;

import br.com.ucsal.ian.client.Client;

public class Connection {

	private Server server;
	private Client client;
	
	public Connection(Server server,Client client) {
		this.server = server;
		this.client = client;
	}

	public void sendDataToClient(String data) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				server.sendDataToClient(data);
				
			}
		}).start();
	}
	
}
