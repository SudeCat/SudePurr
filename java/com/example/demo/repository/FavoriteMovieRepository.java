package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.FavoriteMovie;
import com.example.demo.model.User;

import java.util.List;

@Repository
public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, Long> {
	List<FavoriteMovie> findByUserEmail(String userEmail);
	FavoriteMovie findByUserEmailAndMovieId(String userEmail, Long movieId);
}

