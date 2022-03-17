package com.starwars.socialnetwork.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RebeldeRequestDTO {

    private String nome;
    private Integer idade;
    private GeneroEnum genero;
    private LocalizacaoDTO localizacao;
    private Inventario inventario;

}
