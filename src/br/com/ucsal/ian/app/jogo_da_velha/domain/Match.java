package br.com.ucsal.ian.app.jogo_da_velha.domain;

import static br.com.ucsal.ian.app.jogo_da_velha.domain.common.Properties.NOT_CHOSEN;
import static br.com.ucsal.ian.app.jogo_da_velha.domain.common.Properties.PLAYER_1;
import static br.com.ucsal.ian.app.jogo_da_velha.domain.common.Properties.PLAYER_2;

import java.util.Iterator;
import java.util.List;

import br.com.ucsal.ian.app.hosts.server.Server;
import br.com.ucsal.ian.app.hosts.server.ServerThread;
import br.com.ucsal.ian.app.jogo_da_velha.domain.exceptions.TurnException;

public class Match {

	private Player player1;
	private Player player2;
	private Player winner;
	private Turn turn;
	private Boolean finish;
	private Boolean stop;
	private static Boolean initializationFlag;
	private Integer iterator;

	public Match(ServerThread player1, ServerThread player2) {
		this.player1 = new Player(PLAYER_1, player1);
		this.player2 = new Player(PLAYER_2, player2);
		this.turn = new Turn(this.player1, this.player2);
		this.winner = null;
		this.finish = false;
		this.initializationFlag = false;
		this.stop = false;
		this.iterator = 0; 
	}

	public void start() throws Exception {			
			while (!this.stop) {
				runningGame();	
				iterator++;
			}
			endgame();
	}

	private void runningGame() throws Exception {
		if (!verifyIfSomeoneWon()) {
			System.out.println("verify: " + verifyIfSomeoneWon());
			boolean turnSuccess = turnSwitch(); 
		}

		
		this.stop = initializationFlag && isDraw() || hasWinner();
		this.initializationFlag = Boolean.TRUE;
	}

	private boolean turnSwitch() throws Exception {
		
		
		switch (turn.getWhoseTurnIsIt()) {
		case FIRST_PLAYER_TURN: {
			return turn.firstPlayersTurn();
		}
		case SECOND_PLAYER_TURN: {
			return turn.secondPlayersTurn();
		}
		default:
			throw new TurnException(turn.getWhoseTurnIsIt());
		}
	}

	private void endgame() throws Exception {
		if (hasWinner()) {
			turn.won(this.winner);
		} else if (isDraw()) {
			turn.draw();
		}
	}

	public boolean hasWinner() {
		return this.winner != null;
	}

	public boolean verifyIfSomeoneWon() {
		boolean v0 = hasWinnerHorizontally(0);
		boolean v1 = hasWinnerHorizontally(1);
		boolean v2 = hasWinnerHorizontally(2);

		boolean v3 = hasWinnerVertically(0);
		boolean v4 = hasWinnerVertically(1);
		boolean v5 = hasWinnerVertically(2);

		boolean v6 = hasWinnerRightDiagonal();
		boolean v7 = hasWinnerLeftDiagonal();

		return v0 | v1 | v2 | v3 | v4 | v5 | v6 | v7;
	}

	private boolean hasWinnerHorizontally(int row) {

		List<Integer> rowFiltered = Server.getData().get(row);

		if (allMatch(rowFiltered, PLAYER_1)) {
			this.winner = player1;
			return true;
		} else if (allMatch(rowFiltered, PLAYER_2)) {
			this.winner = player2;
			return true;
		}

		return false;
	}

	private boolean hasWinnerVertically(int column) {

		List<Integer> columnFilterd = Server.getData().stream().map(x -> x.get(0)).toList();

		if (allMatch(columnFilterd, PLAYER_1)) {
			this.winner = player1;
			return true;
		} else if (allMatch(columnFilterd, PLAYER_2)) {
			this.winner = player2;
			return true;
		}

		return false;
	}

	private boolean hasWinnerRightDiagonal() {

		if (analyzeRightDiagoalWinner(PLAYER_1)) {
			this.winner = player1;
			return true;
		} else if (analyzeRightDiagoalWinner(PLAYER_2)) {
			this.winner = player2;
			return true;
		}

		return false;
	}

	private boolean analyzeRightDiagoalWinner(int value) {

		List<Integer> row0 = Server.getData().get(0);
		List<Integer> row1 = Server.getData().get(1);
		List<Integer> row2 = Server.getData().get(2);

		if (row1.get(0).intValue() == value) {
			if (row1.get(1).intValue() == value) {
				if (row2.get(2).intValue() == value) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean hasWinnerLeftDiagonal() {

		if (analyzeLeftDiagoalWinner(PLAYER_1)) {
			this.winner = player1;
			return true;
		} else if (analyzeLeftDiagoalWinner(PLAYER_2)) {
			this.winner = player2;
			return true;
		}

		return false;
	}

	private boolean analyzeLeftDiagoalWinner(int value) {

		List<Integer> row0 = Server.getData().get(0);
		List<Integer> row1 = Server.getData().get(1);
		List<Integer> row2 = Server.getData().get(2);

		if (row2.get(0).intValue() == value) {
			if (row1.get(1).intValue() == value) {
				if (row0.get(2).intValue() == value) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean allMatch(List<Integer> colletion, Integer value) {
		return colletion.stream().allMatch((c) -> {
			return value == c.intValue();
		});
	}

	public boolean isDraw() {
		return Server.getData().stream().allMatch((r) -> r.stream().allMatch((c) -> c.equals(NOT_CHOSEN)));
	}

}
