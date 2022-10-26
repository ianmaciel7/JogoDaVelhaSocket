package br.com.ucsal.ian.app.jogo_da_velha.domain;

import br.com.ucsal.ian.app.hosts.server.Server;
import br.com.ucsal.ian.app.hosts.server.ServerThread;

public class TurnManager {
	
	private Player player1;
	private Player player2;
	private Turn turn;

	public TurnManager(ServerThread player1, ServerThread player2) {
		this.player1 = new Player(player1);
		this.player2 = new Player(player2);
		this.turn = new Turn(this.player1, this.player2);
		
		while (true) {
			switch (turn.getWhoseTurnIsIt()) {
			case FIRST_PLAYERS_TURN: {
				turn.firstPlayersTurn();			
			}
			case SECOND_PLAYERS_TURN: {
				turn.secondPlayersTurn();
			}
			case ENDED_TURN: {
				turn.endedTurn();
			}
			default:
				System.out.println("aki");
			}
			
		}
	

	}
	
	
}
