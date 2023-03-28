package com.example.movies.service.impl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.movies.dto.MovieDTO;
import com.example.movies.entity.MovieEntity;
import com.example.movies.exception.MovieNotFoundException;
import com.example.movies.repository.MovieRepository;
import com.example.movies.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MovieDTO> findAll() {
        return movieRepository.findAll()
                .stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MovieDTO> findById(Long id) {
        return movieRepository.findById(id)
                .map(m -> modelMapper.map(m, MovieDTO.class));
    }

    @Override
    public MovieDTO create(MovieDTO movieDTO) {
        movieDTO.setId(null);

        MovieEntity movieToSave = modelMapper.map(movieDTO, MovieEntity.class);
        MovieEntity savedMovie = movieRepository.save(movieToSave);

        return modelMapper.map(savedMovie, MovieDTO.class);
    }

    @Override
    public MovieDTO update(MovieDTO movieDTO) {
        Long id = movieDTO.getId();
        Optional<MovieEntity> movieToUpdate = movieRepository.findById(id);

        if (movieToUpdate.isEmpty()) {
            throw new MovieNotFoundException("Movie not found with id=" + id);
        }

        MovieEntity movieToPersist = modelMapper.map(movieDTO, MovieEntity.class);
        MovieEntity savedMovie = movieRepository.save(movieToPersist);

        return modelMapper.map(savedMovie, MovieDTO.class);
    }

    @Override
    public void delete(Long id) {
        Optional<MovieEntity> movieToDelete = movieRepository.findById(id);

        if (movieToDelete.isPresent()) {
            movieRepository.delete(movieToDelete.get());
        } else {
            throw new MovieNotFoundException("Movie not found with id=" + id);
        }
    }

}}
