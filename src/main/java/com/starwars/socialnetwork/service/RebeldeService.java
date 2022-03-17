package com.starwars.socialnetwork.service;

import java.util.List;

import com.starwars.socialnetwork.model.Rebelde;
import com.starwars.socialnetwork.repository.RebeldeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RebeldeService {

    @Autowired
    RebeldeRepository rebeldeRepository;

    public void create(Rebelde rebelde) {
        rebeldeRepository.save(rebelde);
    }

    public List<Rebelde> list() {
        return rebeldeRepository.findAll();
    }

}