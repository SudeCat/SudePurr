package com.example.demo.web.dto;


public class FavoriteMovieRequest {
    private String userEmail;
    private Long movieId;

    // Getter and Setter methods
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}


