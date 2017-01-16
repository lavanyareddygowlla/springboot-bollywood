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
}