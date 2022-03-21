package com.starwars.socialnetwork.repository;

import com.starwars.socialnetwork.model.Rebelde;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RebeldeRepository extends JpaRepository<Rebelde, Long> {
    @Query("SELECT COUNT(*) FROM Rebelde")
    public int totalRebeldes();
    @Query("SELECT COUNT(*) FROM Rebelde WHERE denuncias >= 3")
    public int totalTraidores();

}