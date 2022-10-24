package br.com.ucsal.ian.app.jogo_da_velha;

import br.com.ucsal.ian.server.ServerMain;
import br.com.ucsal.ian.server.ServerThread;

public class JogoDaVelha implements Runnable {

	public final static int PLAYER_1 = 0;
	public final static int PLAYER_2 = 1;
	private Player player1;
	private Player player2;
	private Turn turn;

	

	public JogoDaVelha(ServerThread player1, ServerThread player2) {
		this.player1 = new Player(player1);
		this.player2 = new Player(player2);
		this.turn = new Turn(this.player1, this.player2);
	}

	@Override
	public void run() {
		System.out.println(turn.getWhoseTurnIsIt().name());		
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
