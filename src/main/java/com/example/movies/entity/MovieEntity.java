package com.example.movies.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

import java.util.Date;
import java.util.Set;

@Entity
    public class MovieEntity {

        @Id
        @GeneratedValue
        private Long id;
        private String title;
        private String tagline;
        private double voteAverage;
        private int voteCount;
        private Date releaseDate;
        private String posterPath;
        private String overview;
        private int budget;
        private int revenue;
        private Set< String> genres;
        private Integer runtime;
        public MovieEntity() {
        }
}
