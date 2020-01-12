import Movie from "./Movie";

const React = require('react');
const ReactDOM = require('react-dom');

class MovieList extends React.Component {
    render() {
        const movies = this.props.movies.map(movie =>
            <Movie key={movie.id} movie={movie} onRate={this.props.onRate}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>Title</th>
                    <th>Release Date</th>
                    <th>Genre</th>
                    <th>Avg Rate</th>
                    <th>Your Rate</th>
                </tr>
                {movies}
                </tbody>
            </table>
        )
    }
}

export default MovieList;