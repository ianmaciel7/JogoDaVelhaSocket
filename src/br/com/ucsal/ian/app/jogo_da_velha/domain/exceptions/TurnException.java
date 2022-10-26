package br.com.ucsal.ian.app.jogo_da_velha.domain.exceptions;

import br.com.ucsal.ian.app.jogo_da_velha.domain.Turns;

public class TurnException extends Exception {

	public TurnException(Turns turn) {
		super("turno inválido"+ turn);
		// TODO Auto-generated constructor stub
	}
	
}
