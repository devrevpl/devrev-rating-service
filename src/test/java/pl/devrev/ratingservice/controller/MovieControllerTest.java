package pl.devrev.ratingservice.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieControllerTest {

    private MovieController movieController = new MovieController();

    @Test
    void index() {
        assertEquals("index", movieController.index());
    }
}