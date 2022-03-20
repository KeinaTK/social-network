package com.starwars.socialnetwork.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starwars.socialnetwork.dto.LocalizacaoDTO;
import com.starwars.socialnetwork.dto.RebeldeCreateDTO;
import com.starwars.socialnetwork.dto.RebeldeListDTO;
import com.starwars.socialnetwork.exception.NoContentException;
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

}