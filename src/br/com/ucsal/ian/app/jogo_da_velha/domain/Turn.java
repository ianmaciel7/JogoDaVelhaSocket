package br.com.ucsal.ian.app.jogo_da_velha.domain;

import br.com.ucsal.ian.app.hosts.server.Server;
import br.com.ucsal.ian.app.hosts.server.ServerThread;
import br.com.ucsal.ian.app.jogo_da_velha.presentation.BoardBuilder;
import br.com.ucsal.ian.app.jogo_da_velha.util.Action;
import br.com.ucsal.ian.app.jogo_da_velha.util.Message;
import br.com.ucsal.ian.app.jogo_da_velha.util.Properties;
import br.com.ucsal.ian.app.jogo_da_velha.util.Turns;

public class Turn {
	
	private static Player player1;
	private static Player player2;
	public static Turns whoseTurnIsIt;
	

	public Turn(Player player1, Player player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;	
	}
	
	public Turns getWhoseTurnIsIt() {
		return this.whoseTurnIsIt;
	}

	public void firstPlayersTurn() {
		
	
		player1.println(Message.YOUR_TURN); 		 	
		player2.println(Message.WAIT_THE_FIRST_PLAYER);
		
		player1.action(Action.ENABLE_KEYBOARD);
		player2.action(Action.DISABLE_KEYBOARD);
		
		
		String data=player1.read();
		if(data != null) {	

			Server.setData(data, Properties.PLAYER_1);
			this.whoseTurnIsIt = Turns.SECOND_PLAYERS_TURN;		
			broadcastMessage(new BoardBuilder(Server.getData()).toString());		
		}

	}
	
	public void secondPlayersTurn() {
		
		
		
		player2.println(Message.YOUR_TURN); 	
		player1.println(Message.WAIT_THE_SECOND_PLAYER);
		
		player2.action(Action.ENABLE_KEYBOARD);
		player1.action(Action.DISABLE_KEYBOARD);
		
		String data=player2.read();
		System.out.println("Turno finalizado"+data);
		
		if(data != null) {
			this.whoseTurnIsIt = Turns.ENDED_TURN;
			Server.setData(data, Properties.PLAYER_2);
			broadcastMessage(new BoardBuilder(Server.getData()).toString());
		}
	}

	public void endedTurn() {
		
		System.out.println("[Turno finalizado]");
		
		this.whoseTurnIsIt = Turns.FIRST_PLAYERS_TURN;
		
	}
	
	private void broadcastMessage(String msg) {	
		player1.println(msg);
		player2.println(msg);	
	}

	
	
}
