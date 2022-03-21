package com.starwars.socialnetwork.dto;

import lombok.Data;

@Data
public class RebeldeListDTO {

	private Long id;
	private String nome;
    private Integer idade;
    private GeneroEnum genero;
    private LocalizacaoDTO localizacao;
    private InventarioDTO inventario;
    private Boolean traidor;
}
