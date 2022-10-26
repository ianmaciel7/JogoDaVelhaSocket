package br.com.ucsal.ian.app.jogo_da_velha;

import static br.com.ucsal.ian.app.jogo_da_velha.util.Properties.PLAYER_1;
import static br.com.ucsal.ian.app.jogo_da_velha.util.Properties.PLAYER_2;

import br.com.ucsal.ian.app.hosts.server.Server;
import br.com.ucsal.ian.app.hosts.server.ServerThread;
import br.com.ucsal.ian.app.jogo_da_velha.domain.TurnManager;

public class App implements Runnable  {
	@Override
	public void run() {
		boolean justOnePlayer = Server.threads.size() > 0;
		boolean hasTwoPlayers = Server.threads.size() > 1;

		if (hasTwoPlayers) {
			ServerThread player1 = Server.threads.get(PLAYER_1);
			ServerThread player2 = Server.threads.get(PLAYER_2);
			new TurnManager(player1, player2);
		} else {	
			while (!(Server.threads.size() > 1)) {
				System.out.println("esperando segundo jogador... \n total de jogadore: " + Server.threads.size());				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			} 
		}	
	}
}
