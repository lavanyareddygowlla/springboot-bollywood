package com.allstate.services;

import com.allstate.entities.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"/sql/seed.sql"})
public class MovieServiceTest {
    @Autowired
    private MovieService service;


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateMovie() throws Exception {
        Movie before = new Movie();
        before.setTitle("The Matrix");
        Movie after = this.service.create(before);
        assertEquals("The Matrix",after.getTitle());
        assertEquals(2,after.getId());
        assertNotNull(after.getTitle());
        assertTrue(after.getId()>0);

    }


    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void shouldNotCreateMovie() throws Exception {
        Movie before = new Movie();
        Movie after = this.service.create(before);
        assertEquals(2,after.getId());

    }

    @Test
    public void shouldFindMovieById() throws Exception {
        Movie m = this.service.findById(1);
        assertNotNull(m);

    }

    @Test
    public void shouldNotFindMovieByBadId() throws Exception {
        Movie m = this.service.findById(5);
        assertNull(m);

    }

    @Test
    public void shouldReturnAllMovies() throws Exception{
        Movie before = new Movie();
        before.setTitle("The Lavanya");
        List<Movie> m = (List<Movie>) this.service.findAll();
        m.add(before);
        assertNotNull(m);
        assertEquals("The Avengers", m.get(0).getTitle());
        assertEquals("The Lavanya", m.get(1).getTitle());
    }


    @Test
    public void shouldReturnNullForNoMovies() throws Exception{
        List<Movie> m = (List<Movie>) this.service.findAll();
        assertNotEquals(2,m.size());
    }

    @Test
    public void shouldReturnMovieByTitle() throws Exception{
        Movie m = this.service.findByTitle("The Avengers");
        assertEquals("The Avengers",m.getTitle());

    }


    @Test
    public void shouldReturnMovieByBadTitle() throws Exception{
        Movie m = this.service.findByTitle("The Movie1");
        assertNull(m);

    }

    @Test
    public void shouldDeleteMovieById() throws Exception {
        Movie before = new Movie();
        before.setTitle("The Man");
        Movie after = this.service.create(before);
        this.service.deleteById(2);
        assertNull(this.service.findByTitle("The Man"));
    }

    @Test(expected = org.springframework.dao.EmptyResultDataAccessException.class)
    public void shouldDeleteMovieByBadId() throws Exception {
        this.service.deleteById(5);
        assertNull(this.service.findByTitle("The Man"));
    }


    @Test
    public void shouldUpdateMovie() throws Exception {
        Movie before = new Movie();
        before.setTitle("The Avengers II");
        Movie after = this.service.updateById(1,before);
        assertEquals("The Avengers II",before.getTitle());
    }
}