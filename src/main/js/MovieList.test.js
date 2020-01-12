import React from 'react';
import {shallow, mount, render} from 'enzyme';
import MovieList from "./MovieList";
import Movie from "./Movie";

describe('list of movies', () => {
    const movies = [
        {id: "5ce7003ff8163e5f388b23ca", title: "Movie Title 1"},
        {id: "5ce7003ff8163e5f388b23cb", title: "Movie Title 2"},
        {id: "5ce7003ff8163e5f388b23cc", title: "Movie Title 3"}
    ];
    const movieList = shallow(<MovieList movies={movies} />);

    movies.forEach(movie => {
        it(`includes title ${movie.title} on the list`, () => {
            expect(movieList.containsMatchingElement(<Movie key={movie.id} movie={movie}/>)).toEqual(true)
        });
    });
});