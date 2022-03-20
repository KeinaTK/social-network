package com.starwars.socialnetwork.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.starwars.socialnetwork.dto.InventarioDTO;

import lombok.Data;

@Entity
@Data
public class Inventario {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private Integer arma;
    private Integer municao;
    private Integer agua;
    private Integer comida;
    
    public Inventario somaInventarios(Inventario outroInventario) {
		this.setAgua(this.getAgua() + outroInventario.getAgua());
		this.setArma(this.getArma() + outroInventario.getArma());
		this.setMunicao(this.getMunicao() + outroInventario.getMunicao());
		this.setComida(this.getComida() + outroInventario.getComida());
		return this;
	}
    
    public Inventario subtraiInventarios(Inventario outroInventario) {
		this.setAgua(this.getAgua() - outroInventario.getAgua());
		this.setArma(this.getArma() - outroInventario.getArma());
		this.setMunicao(this.getMunicao() - outroInventario.getMunicao());
		this.setComida(this.getComida() - outroInventario.getComida());
		return this;
	}
}
