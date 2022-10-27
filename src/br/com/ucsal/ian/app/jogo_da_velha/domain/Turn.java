package br.com.ucsal.ian.app.jogo_da_velha.domain;

import br.com.ucsal.ian.app.hosts.server.Server;
import br.com.ucsal.ian.app.hosts.server.ServerThread;
import br.com.ucsal.ian.app.jogo_da_velha.domain.common.Message;
import br.com.ucsal.ian.app.jogo_da_velha.domain.common.Properties;
import br.com.ucsal.ian.app.jogo_da_velha.presentation.BoardBuilder;

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
	
	public boolean isInvalidChoice(String data) {
		if(data.length() == 2) return true;
		var a =data.split(",");
		if(data.split(",").length == 1) return true;
		
		try {
			Integer row = Integer.parseInt(String.valueOf(data.charAt(0)));
			Integer column = Integer.parseInt(String.valueOf(data.charAt(2)));
			
			if(row > 2) return true;
			if(column > 2) return true;
			return false;
			
		} catch (StringIndexOutOfBoundsException e) {
			return true;
		}
		catch (NumberFormatException e) {
			return true;
		}
	}

	public boolean firstPlayersTurn() {
		
		player1.println(Message.YOUR_TURN); 		 	
		player2.println(Message.WAIT_THE_FIRST_PLAYER);
		
		player1.action(Action.ENABLE_KEYBOARD);
		player2.action(Action.DISABLE_KEYBOARD);
		broadcastMessage(new BoardBuilder(Server.getData()).toString());
		
		
		String data=player1.read();
		
		
		if(data != null) {	
			if(isInvalidChoice(data)) {
				this.whoseTurnIsIt = Turns.FIRST_PLAYER_TURN;		
				broadcastMessage("[Escolha inválida feita pelo primeiro jogador, tente novamente]");
				return false;
			}else {
				Server.setData(data, Properties.PLAYER_1);
				this.whoseTurnIsIt = Turns.SECOND_PLAYER_TURN;		
				broadcastMessage(new BoardBuilder(Server.getData()).toString());	
				return true;
			}		
		}
		return false;

	}
	
	public boolean secondPlayersTurn() {

		player2.println(Message.YOUR_TURN); 	
		player1.println(Message.WAIT_THE_SECOND_PLAYER);
		
		player2.action(Action.ENABLE_KEYBOARD);
		player1.action(Action.DISABLE_KEYBOARD);
		
		String data=player2.read();		
		
		if(data != null) {
			if(isInvalidChoice(data)) {
				this.whoseTurnIsIt = Turns.SECOND_PLAYER_TURN;		
				broadcastMessage("[Escolha inválida feita pelo segundo jogador, tente novamente]");
				return false;
			}else {
				this.whoseTurnIsIt = Turns.FIRST_PLAYER_TURN;
				Server.setData(data, Properties.PLAYER_2);
				broadcastMessage(new BoardBuilder(Server.getData()).toString());
				return true;
			}
		}
		return false;
	}

	public void endedTurn() {
		this.whoseTurnIsIt = Turns.FIRST_PLAYER_TURN;
		System.out.println("[Turno finalizado]");	
		player2.action(Action.DISABLE_KEYBOARD);
		player1.action(Action.DISABLE_KEYBOARD);
	
	}
	
	public void draw() {
		broadcastMessage(Message.DRAW);
		broadcastMessage(Message.ENDGAME);
	}
	
	public void won(Player winner) throws Exception {	
		if(winner.getId() == Properties.PLAYER_1) {
			broadcastMessage(Message.FIRST_PLAYER_WON);
			broadcastMessage(Message.ENDGAME);
		}else if(winner.getId() == Properties.PLAYER_2) {
			broadcastMessage(Message.SECOND_PLAYER_WON);
			broadcastMessage(Message.ENDGAME);
		}else {
			throw new Exception("player inválido ao ganhar");
		}
	}
	
	private void broadcastMessage(String msg) {	
		player1.println(msg);
		player2.println(msg);	
		System.out.println(msg);
	}

}
