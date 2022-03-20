package com.starwars.socialnetwork.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LocalizacaoDTO {
	
	@NotNull(message = "O Campo latitude é obrigatório")
    private Double latitude;
	@NotNull(message = "O Campo longitude é obrigatório")
    private Double longitude;
	@NotNull(message = "O Campo nomeBase é obrigatório")
    private String nomeBase;

}