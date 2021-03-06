package com.starwars.socialnetwork.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starwars.socialnetwork.dto.InventarioDTO;
import com.starwars.socialnetwork.dto.LocalizacaoDTO;
import com.starwars.socialnetwork.dto.RebeldeCreateDTO;
import com.starwars.socialnetwork.dto.RebeldeListDTO;
import com.starwars.socialnetwork.dto.TrocaItensDTO;
import com.starwars.socialnetwork.exception.NegociacaoInvalidaException;
import com.starwars.socialnetwork.exception.NoContentException;
import com.starwars.socialnetwork.model.Inventario;
import com.starwars.socialnetwork.model.Localizacao;
import com.starwars.socialnetwork.model.Rebelde;
import com.starwars.socialnetwork.repository.RebeldeRepository;

@Service
public class RebeldeService {

    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    RebeldeRepository rebeldeRepository;

    public RebeldeListDTO create(RebeldeCreateDTO requestDto) {
        Rebelde rebelde = rebeldeRepository.save(modelMapper.map(requestDto, Rebelde.class));
        return modelMapper.map(rebelde, RebeldeListDTO.class);
    }

    public List<RebeldeListDTO> list() {
        List<Rebelde> rebeldes = rebeldeRepository.findAll();
        return rebeldes
        	.stream()
        	.map(rebelde -> modelMapper.map(rebelde, RebeldeListDTO.class))
        	.collect(Collectors.toList());
    }

	public RebeldeListDTO updateLocation(Long id, LocalizacaoDTO dto) {
		Optional<Rebelde> optional = rebeldeRepository.findById(id);
		if(optional.isPresent()) {
			Rebelde rebelde = optional.get();
			rebelde.setLocalizacao(modelMapper.map(dto, Localizacao.class));
			rebeldeRepository.save(rebelde);
			return  modelMapper.map(rebelde, RebeldeListDTO.class);
		}
		throw new NoContentException();
	}

	public void trocaItens(List<TrocaItensDTO> trocaItensDTO) {
		if(trocaItensDTO.size() != 2) throw new NegociacaoInvalidaException("?? necess??rio que 2 Rebeldes participem da negocia????o");
		
		List<Long> ids = trocaItensDTO
			.stream()
			.map(rebelde -> rebelde.getRebeldeId())
			.collect(Collectors.toList());
		
		List<Rebelde> rebeldes = rebeldeRepository.findAllById(ids);
		if(rebeldes.size() != 2) throw new NegociacaoInvalidaException("Rebelde n??o encontrado");;
		
		// Algor??tmo implementado com complexidade quadr??tica (O(n??)).
		// Por??m, estamos garantindo que N = 2 em todos os casos, portanto,
		// a complexidade torna-se O(2??) = O(4) ~ O(1) (Domina????o assint??tica).
		rebeldes.forEach(rebelde -> {
			trocaItensDTO.forEach(item -> {
				if(rebelde.getId() == item.getRebeldeId()) {
					InventarioDTO itemNegociado = item.getInventario();
					Inventario itemArmazenado = rebelde.getInventario();
					if(itemNegociado.getAgua() > itemArmazenado.getAgua()) throw new NegociacaoInvalidaException("Rebelde n??o pode negociar esta quantia de ??gua");
					if(itemNegociado.getArma() > itemArmazenado.getArma()) throw new NegociacaoInvalidaException("Rebelde n??o pode negociar esta quantia de arma");
					if(itemNegociado.getComida() > itemArmazenado.getComida()) throw new NegociacaoInvalidaException("Rebelde n??o pode negociar esta quantia de comida");
					if(itemNegociado.getMunicao() > itemArmazenado.getMunicao()) throw new NegociacaoInvalidaException("Rebelde n??o pode negociar esta quantia de muni????o");
					if(rebelde.isTraidor()) throw new NegociacaoInvalidaException("TRAIDORRR!");
				}
			});
		});
		
		if(trocaItensDTO.get(0).getTotalPontos() != trocaItensDTO.get(1).getTotalPontos()) 
			throw new NegociacaoInvalidaException("Negocia????o n??o pode ser feita! Quantidade de pontos entre as ofertas n??o equivatente");
		List<Inventario> ofertas = new ArrayList<>();
		rebeldes.forEach(rebelde -> {
			trocaItensDTO.forEach(item -> {
				if(rebelde.getId() == item.getRebeldeId()) {
					ofertas.add(modelMapper.map(item.getInventario(), Inventario.class));
				}
			});
		});
			
		Inventario ofertaDiferenca = ofertas.get(0).subtraiInventarios(ofertas.get(1));

		rebeldes.get(0).setInventario(rebeldes.get(0).getInventario().subtraiInventarios(ofertaDiferenca));
		rebeldes.get(1).setInventario(rebeldes.get(1).getInventario().somaInventarios(ofertaDiferenca));
		rebeldeRepository.saveAll(rebeldes);
	}

	public RebeldeListDTO denuncia(long id) {
		Optional<Rebelde> optional = rebeldeRepository.findById(id);
		if(optional.isPresent()) {
			Rebelde rebelde = optional.get();
			rebelde.setDenuncias(rebelde.getDenuncias()+1);
			rebeldeRepository.save(rebelde);
			return  modelMapper.map(rebelde, RebeldeListDTO.class);
		}
		throw new NoContentException();
	}

	public Double relatorioTraidor() {
		Double rebeldes = (double)rebeldeRepository.totalRebeldes();
		Double traidores = (double)rebeldeRepository.totalTraidores();
		if(traidores == 0) return 0.0;
		return traidores / rebeldes;
	}

}
