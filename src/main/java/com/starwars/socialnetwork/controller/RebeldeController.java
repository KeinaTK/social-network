package com.starwars.socialnetwork.controller;

import java.util.List;

import com.starwars.socialnetwork.dto.RebeldeRequestDTO;
import com.starwars.socialnetwork.model.Rebelde;
import com.starwars.socialnetwork.service.RebeldeService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rebeldes")
public class RebeldeController {

    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    RebeldeService service;
    
    @GetMapping
    public List<Rebelde> list() {
        return service.list();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RebeldeRequestDTO dto) {
        service.create(modelMapper.map(dto, Rebelde.class));
        return ResponseEntity.noContent().build();
    }
}
