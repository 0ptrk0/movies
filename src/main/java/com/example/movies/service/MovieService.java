package com.example.movies.service;

import java.util.List;
import java.util.Optional;

import com.example.movies.dto.MovieDTO;
import com.example.movies.exception.MovieNotFoundException;

/
public interface MovieService {


    List<MovieDTO> findAll();


    Optional<MovieDTO> findById(Long id);


    MovieDTO create(MovieDTO movieDTO);


    MovieDTO update(MovieDTO movieDTO);


    void delete(Long id);}
