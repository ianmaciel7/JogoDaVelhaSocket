package br.com.ucsal.ian.app.jogo_da_velha;

import static br.com.ucsal.ian.app.jogo_da_velha.util.Message.WAIT_THE_SECOND_PLAYER;
import static br.com.ucsal.ian.util.SocketConstants.DISABLE_KEYBOARD;
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
			boolean justOnePlayer = ServerMain.connections.size() > 0;
			boolean hasTwoPlayers = ServerMain.connections.size() > 1;

			if (!finish1) {

				if (justOnePlayer) {
					ServerThread player1 = ServerMain.connections.get(PLAYER_1);

					if (hasTwoPlayers) {
						ServerThread player2 = ServerMain.connections.get(PLAYER_2);

						player1.sendDataToClient("Suceesso");
						player1.sendDataToClient("zzzzz");
						player1.sendDataToClient("qqqqqqqq");
						player1.sendDataToClient(ENABLE_KEYBOARD);
						player2.sendDataToClient("oi");

						finish1 = true;

					}
				}
			}

		}

	}

	private boolean firstPlayersTurn() {

		/*
		 * secondPlayer.sendDataToClient(WAIT_THE_FIRST_PLAYER);
		 * secondPlayer.sendDataToClient(DISABLE_KEYBOARD);
		 * firtPlayer.sendDataToClient(ENABLE_KEYBOARD);
		 */

		// player.sendDataToClient("Suceesso");
		// player.sendDataToClient("zzzzz");
		// player.sendDataToClient("qqqqqqqq");

		return true;

	}

	private boolean secondPlayersTurn() {

		// player.sendDataToClient(WAIT_THE_SECOND_PLAYER);
		// player.sendDataToClient(DISABLE_KEYBOARD);

		// secondPlayer.sendDataToClient(ENABLE_KEYBOARD);
		;

		return false;
	}

}
