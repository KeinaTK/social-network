package com.starwars.socialnetwork.repository;

import com.starwars.socialnetwork.model.Rebelde;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebeldeRepository extends JpaRepository<Rebelde, Long> {
  
}