package br.com.ucsal.ian.app.jogo_da_velha.hosts;

import java.io.IOException;
import java.net.UnknownHostException;

import br.com.ucsal.ian.app.hosts.client.Client;
import br.com.ucsal.ian.app.jogo_da_velha.util.Action;
import br.com.ucsal.ian.app.jogo_da_velha.util.Properties;

public class JogoDaVelhaClient extends Client {

	public JogoDaVelhaClient() throws UnknownHostException, IOException {
		super(Properties.HOST,Properties.PORT);		
	}
	
	@Override
	public void run() {
		super.run();				
		while (true) {
			String data = this.read();
			if(data != null) this.send();
		}
	}
	@Override
	public void send() {
		if (Action.ENABLE_KEYBOARD.name().trim().equalsIgnoreCase(this.lastData.trim())) {
			super.send();
		} else {
			super.flushSystemIn();
		}
	}
	
}