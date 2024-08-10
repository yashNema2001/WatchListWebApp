package com.example.watchlistApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.watchlistApp.entity.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer>{

}
