package com.starwars.socialnetwork.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.starwars.socialnetwork.dto.LocalizacaoDTO;
import com.starwars.socialnetwork.dto.RebeldeCreateDTO;
import com.starwars.socialnetwork.dto.RebeldeListDTO;
import com.starwars.socialnetwork.dto.TrocaItensDTO;
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
    public ResponseEntity<RebeldeListDTO> create(@RequestBody @Valid RebeldeCreateDTO requestDto, UriComponentsBuilder uriBuilder) {
    	RebeldeListDTO listDTO = service.create(requestDto);
        URI uri = uriBuilder.path("/rebeldes/{id}").buildAndExpand(listDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(listDTO);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RebeldeListDTO> atualizarLocalizacao(@PathVariable Long id, @RequestBody @Valid LocalizacaoDTO dto) {
    	RebeldeListDTO rebeldeUpdate = service.updateLocation(id, dto);
    	return ResponseEntity.ok(rebeldeUpdate);
    }
    
    @PostMapping("/troca")
    public ResponseEntity<?> trocaItens(@RequestBody List<TrocaItensDTO> rebeldes) {
    	service.trocaItens(rebeldes);
    	return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/denuncia")
    public ResponseEntity<RebeldeListDTO> denunciaTraidor(@PathVariable Long id) {
        return ResponseEntity.ok(service.denuncia(id));
    }
    
}
