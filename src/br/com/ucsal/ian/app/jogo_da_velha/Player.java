package br.com.ucsal.ian.app.jogo_da_velha;

import br.com.ucsal.ian.server.ServerThread;

public class Player {
	
	private ServerThread serverThread;
	

	public Player(ServerThread st) {
		this.serverThread = st;
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
