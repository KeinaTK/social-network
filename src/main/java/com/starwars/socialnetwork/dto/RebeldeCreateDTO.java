package com.starwars.socialnetwork.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RebeldeCreateDTO {

	@NotNull(message = "O Campo nome é obrigatório")
    private String nome;
	@NotNull(message = "O Campo idade é obrigatório")
    private Integer idade;
	@NotNull(message = "O Campo genero é obrigatório")
    private GeneroEnum genero;
	@NotNull(message = "O Campo localizacao é obrigatório")
    private LocalizacaoDTO localizacao;
	@NotNull(message = "O Campo inventario é obrigatório")
    private InventarioDTO inventario;

}
