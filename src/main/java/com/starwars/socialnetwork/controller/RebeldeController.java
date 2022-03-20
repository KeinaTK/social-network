package com.starwars.socialnetwork.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.starwars.socialnetwork.dto.RebeldeCreateDTO;
import com.starwars.socialnetwork.dto.RebeldeListDTO;
import com.starwars.socialnetwork.service.RebeldeService;

@RestController
@RequestMapping("/rebeldes")
public class RebeldeController {
    
    @Autowired
    RebeldeService service;
    
    @GetMapping
    public List<RebeldeListDTO> list() {
        return service.list();
    }

    @PostMapping
    public ResponseEntity<RebeldeListDTO> create(@RequestBody RebeldeCreateDTO requestDto, UriComponentsBuilder uriBuilder) {
    	RebeldeListDTO listDTO = service.create(requestDto);
        URI uri = uriBuilder.path("/rebeldes/{id}").buildAndExpand(listDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(listDTO);
    }
}
