package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.FavoriteMovie;
import com.example.demo.service.FavoriteMovieService;
import com.example.demo.web.dto.FavoriteMovieRequest;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/favorites")
public class FavoriteMovieController {

    @Autowired
    private FavoriteMovieService favoriteMovieService;

    @PostMapping("/add")
    public ResponseEntity<String> addFavorite(@RequestBody FavoriteMovieRequest request) {
    	 System.out.println("Gelen JSON: " + request);
        try {
            favoriteMovieService.addFavorite(request.getUserEmail(), request.getMovieId());
            return ResponseEntity.ok("Favorite added");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding favorite");
        }
    }


    @PostMapping("/remove")
    public ResponseEntity<String> removeFavorite(@RequestBody FavoriteMovieRequest request) {
        favoriteMovieService.removeFavorite(request.getUserEmail(), request.getMovieId());
        return ResponseEntity.ok("Favorite removed");
    }

    @GetMapping
    public ResponseEntity<List<FavoriteMovie>> getFavorites(@RequestParam String userEmail) {
        List<FavoriteMovie> favorites = favoriteMovieService.getFavorites(userEmail);
        return ResponseEntity.ok(favorites);
    }
}



