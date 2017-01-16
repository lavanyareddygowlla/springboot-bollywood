package com.allstate.services;

import com.allstate.entities.Movie;
import com.allstate.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    private IMovieRepository repository;

    @Autowired
    public void setRepository(IMovieRepository repository) {
        this.repository = repository;
    }

    //for create
    public Movie create(Movie m){
        return this.repository.save(m);
    }


    //for read by Id
    public Movie findById(int id){
        return this.repository.findOne(id);
    }

    //for reading all
    public Iterable<Movie> findAll(){
        return this.repository.findAll();
    }

    //for reading movie by Title
    public Movie findByTitle(String title){
        return this.repository.findByTitle(title);
    }

    //for Delete
    public void deleteById(int id) {
         this.repository.delete(id);
    }

    //for update

    public Movie updateById(int id, Movie m){
        Movie movie=this.repository.findOne(id);
        movie.setTitle(m.getTitle());
        movie.setWatched(m.isWatched());
        movie.setLength(m.getLength());
        return this.repository.save(movie);
    }
}
