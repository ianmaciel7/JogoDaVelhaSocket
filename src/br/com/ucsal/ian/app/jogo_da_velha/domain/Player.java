package br.com.ucsal.ian.app.jogo_da_velha.domain;

import br.com.ucsal.ian.app.hosts.server.ServerThread;

public class Player {
	
	private int id;
	private ServerThread serverThread;
	

	public Player(int id,ServerThread st) {
		this.serverThread = st;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void println(String msg) {
		this.serverThread.send(msg);	
	}
	
	public void action(Action action) {
		this.serverThread.send(action.name());	
	}

	public String read() {
		return this.serverThread.read();
	}
	
	


}
