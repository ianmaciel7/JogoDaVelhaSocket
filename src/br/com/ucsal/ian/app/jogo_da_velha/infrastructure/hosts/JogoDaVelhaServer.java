package br.com.ucsal.ian.app.jogo_da_velha.infrastructure.hosts;

import br.com.ucsal.ian.app.hosts.server.Server;
import br.com.ucsal.ian.app.hosts.server.ServerThread;
import br.com.ucsal.ian.app.jogo_da_velha.App;
import br.com.ucsal.ian.app.jogo_da_velha.domain.Match;

import static br.com.ucsal.ian.app.jogo_da_velha.domain.common.Properties.*;

import java.io.IOException;

public class JogoDaVelhaServer extends Server implements Runnable {

	@Override
	public void run() {
		try {
			createServer(PORT).multiThread(new App());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
