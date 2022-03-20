package com.starwars.socialnetwork.dto;

import lombok.Data;

@Data
public class TrocaItensDTO {

	private final static int ARMA = 4;
	private final static int MUNICAO = 3;
	private final static int AGUA = 2;
	private final static int COMIDA = 1;
	
	private Long rebeldeId;
	private InventarioDTO inventario;
	
	public int getTotalPontos() {
		return inventario.getArma() * ARMA + 
				inventario.getMunicao() * MUNICAO + 
				inventario.getComida() * COMIDA + 
				inventario.getAgua() * AGUA;
	}
	
	
}
