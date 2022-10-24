package br.com.ucsal.ian.app.jogo_da_velha;

import static br.com.ucsal.ian.util.SocketConstants.ENABLE_KEYBOARD;

import br.com.ucsal.ian.server.ServerMain;
import br.com.ucsal.ian.server.ServerThread;

public class JogoDaVelhaServer extends Thread {

	public final static int PLAYER_1 = 0;
	public final static int PLAYER_2 = 1;

	private static Boolean firstPlayerAlredyPlay = false;

	private boolean finish1 = false;
	private boolean finish2 = false;
	

	public JogoDaVelhaServer() {
		this.finish1 = false;
		this.finish2 = false;

	}

	@Override
	public void run() {
		while (true) {
			boolean justOnePlayer = ServerMain.threads.size() > 0;
			boolean hasTwoPlayers = ServerMain.threads.size() > 1;

			if (!finish1) {

					if (hasTwoPlayers) {
						ServerThread player1 = ServerMain.threads.get(PLAYER_1);
						ServerThread player2 = ServerMain.threads.get(PLAYER_2);

						new JogoDaVelha(player1, player2).run();

					}else {
						System.out.println("esperando segundo jogador... \n total de jogadore: "+ServerMain.threads.size());
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

}
