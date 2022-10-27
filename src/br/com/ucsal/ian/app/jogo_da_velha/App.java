package br.com.ucsal.ian.app.jogo_da_velha;

import static br.com.ucsal.ian.app.jogo_da_velha.domain.common.Properties.PLAYER_1;
import static br.com.ucsal.ian.app.jogo_da_velha.domain.common.Properties.PLAYER_2;

import br.com.ucsal.ian.app.hosts.server.Server;
import br.com.ucsal.ian.app.hosts.server.ServerThread;
import br.com.ucsal.ian.app.jogo_da_velha.domain.Match;

public class App implements Runnable {
	@Override
	public void run() {
		try {
			
			boolean justOnePlayer = Server.threads.size() > 0;
			boolean hasTwoPlayers = Server.threads.size() > 1;

			if (hasTwoPlayers) {
				startUp();
			} else {
				printWaitingForSecondPlayer();
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	private void startUp() throws Exception {
		ServerThread player1 = Server.threads.get(PLAYER_1);
		ServerThread player2 = Server.threads.get(PLAYER_2);

		new Match(player1, player2).start();
	}

	private void printWaitingForSecondPlayer() throws InterruptedException {
		while (!(Server.threads.size() > 1)) {
			System.out.println("esperando segundo jogador... \n total de jogadore: " + Server.threads.size());
			Thread.sleep(1000);
		}
	}
	
	
}
