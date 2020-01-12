import React from 'react';
import {shallow, mount, render} from 'enzyme';
import Movie from "./Movie";
import ShallowRenderer from 'react-test-renderer/shallow';


it(`should render movie row`, () => {
    const movie = {
        id: "5ce7003ff8163e5f388b23ca",
        title: "Movie Title",
        genreName: "action",
        genreIconUri: "action.png",
        ratings: [7, 3, 8]
    };

    const onRateMock = jest.fn();
    const renderer = new ShallowRenderer();
    renderer.render(<Movie key={movie.id} movie={movie} onRate={onRateMock}/>);

    const result = renderer.getRenderOutput();

    expect(result.props.children[0]).toEqual(<td>Movie Title</td>);
    expect(result.props.children[1]).toEqual(<td/>);
    expect(result.props.children[2]).toEqual(<td><img src="icons/action.png"/><br />action</td>);
    expect(result.props.children[3]).toEqual(<td>6,00</td>);
});