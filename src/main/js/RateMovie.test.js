import React from 'react';
import {shallow, mount, render} from 'enzyme';
import RateMovie from "./RateMovie";

import axios from 'axios';

jest.mock('axios');

describe('rate component editable', () => {
    const movie = {id: "5ce7003ff8163e5f388b23ca", title: "Movie Title"};
    const onRateMock = jest.fn();
    const rateMovie = shallow(<RateMovie onRate={onRateMock} key={movie.id} movie={movie}/>);

    it('is editable and not yet rated', () => {
        expect(rateMovie.state().editable).toEqual(true);
        expect(rateMovie.state().rating).toEqual(0);
    });

    it('is not editable after rating', async () => {
        const instance = rateMovie.instance();

        const patchSpy = jest.spyOn(axios, 'patch');

        instance.onStarClick(5, 0, "test");

        expect(rateMovie.state().editable).toEqual(false);
        expect(rateMovie.state().rating).toEqual(5);

        // we are expecting one call of onRate function
        await expect(patchSpy).toBeCalled();
        expect(onRateMock.mock.calls.length).toBe(1);
    });
});