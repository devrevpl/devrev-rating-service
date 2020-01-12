import PropTypes from 'prop-types';
import axios from 'axios';

const React = require('react');
const ReactDOM = require('react-dom');

import {createStore} from 'redux';
import {connect, Provider} from 'react-redux';

import MovieList from './MovieList';

const reducer = (state, action) => {

    switch (action.type) {
        case 'MOVIES_LOADED':
            return {...state, movies: action.movies};
        case 'RATE_ADDED':
            return {...state, movies: updateRate()};
        default:
            return state;
    }

    function updateRate() {
        var moviesNew = [...state.movies];
        moviesNew.filter(movie => {
            return movie.id === action.id
        })[0].ratings.push(parseInt(action.value, 10));
        return moviesNew;
    }
};

const store = createStore(reducer, {movies: []});

class App extends React.Component {
    static propTypes = {
        movies: PropTypes.array
    };

    componentDidMount() {
        axios.get(MOVIES_URL)
            .then(response => {
                this.props.onLoadMovies(response.data);
            })
            .catch(onRejected => {
                alert("Failed on getting movie list: " + onRejected);
            });
    }

    render() {
        const {movies} = this.props;
        return (
            <MovieList movies={movies} onRate={this.props.onRate}/>
        )
    }
}

const mapStateToProps = (state) => {
    return {movies: state.movies};
};

const mapDispatchToProps = (dispatch) => {
    return {
        onLoadMovies: (array) => dispatch({type: 'MOVIES_LOADED', movies: array}),
        onRate: (id, value) => dispatch({type: 'RATE_ADDED', id: id, value: value})
    }
};

App = connect(mapStateToProps, mapDispatchToProps)(App);

ReactDOM.render(
    <Provider store={store}>
        <App/>
    </Provider>,
    document.getElementById('react')
);