package com.starwars.socialnetwork.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Localizacao {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private Double latitude;
    private Double longitude;
    private String nomeBase;
}
