package com.starwars.socialnetwork.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starwars.socialnetwork.dto.RebeldeCreateDTO;
import com.starwars.socialnetwork.dto.RebeldeListDTO;
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

}