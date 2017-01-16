package com.allstate.controllers;

import com.allstate.entities.Movie;
import com.allstate.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/movies")
public class MovieController {

    private MovieService service;

    @Autowired
    public void setService(MovieService service) {
        this.service = service;
    }


    //    post / movies
    @RequestMapping(value={"","/"}, method= RequestMethod.POST)
    public Movie create(@RequestBody Movie movie){
        return this.service.create(movie);
    };




    //    put /movies / 3
    public void update(){};
    //    delete /movies / 4
    public void delete(){};
}
