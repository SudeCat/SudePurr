package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.FavoriteMovie;
import com.example.demo.repository.FavoriteMovieRepository;

import java.util.List;

@Service
public class FavoriteMovieService {

    @Autowired
    private FavoriteMovieRepository favoriteMovieRepository;

    public void addFavorite(String userEmail, Long movieId) {
        FavoriteMovie favoriteMovie = new FavoriteMovie();
        favoriteMovie.setUserEmail(userEmail);
        favoriteMovie.setMovieId(movieId);
        favoriteMovieRepository.save(favoriteMovie);
    }

    public void removeFavorite(String userEmail, Long movieId) {
        FavoriteMovie favoriteMovie = favoriteMovieRepository.findByUserEmailAndMovieId(userEmail, movieId);
        if (favoriteMovie != null) {
            favoriteMovieRepository.delete(favoriteMovie);
        }
    }

    public List<FavoriteMovie> getFavorites(String userEmail) {
        return favoriteMovieRepository.findByUserEmail(userEmail);
    }
}

