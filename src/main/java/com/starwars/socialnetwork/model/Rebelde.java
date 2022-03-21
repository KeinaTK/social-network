package com.starwars.socialnetwork.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.starwars.socialnetwork.dto.GeneroEnum;

import lombok.Data;

@Entity
@Data
public class Rebelde {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer idade;
    private GeneroEnum genero;
    @OneToOne(cascade = CascadeType.ALL)
    private Localizacao localizacao;
    @OneToOne(cascade = CascadeType.ALL)
    private Inventario inventario;
    private Integer denuncias = 0;

    public boolean isTraidor() {
        return denuncias >=3;
    }
}