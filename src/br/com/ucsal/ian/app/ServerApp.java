package br.com.ucsal.ian.app;

import java.io.IOException;
import java.net.UnknownHostException;

import br.com.ucsal.ian.app.jogo_da_velha.domain.Turn;
import br.com.ucsal.ian.app.jogo_da_velha.domain.Turns;
import br.com.ucsal.ian.app.jogo_da_velha.infrastructure.hosts.JogoDaVelhaServer;

public class ServerApp {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Turn.whoseTurnIsIt = Turns.FIRST_PLAYER_TURN;
		
		new JogoDaVelhaServer().run();			 
	}

}
