import RateMovie from "./RateMovie";

const React = require('react');

class Movie extends React.Component {

    render() {
        const formatter = new Intl.NumberFormat('pl-PL', {
            style: 'decimal',
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        });
        let avgRate;

        if (this.props.movie.ratings.length === 0) {
            avgRate = "Brak ocen";
        } else {
            avgRate = formatter.format(
                this.props.movie.ratings.reduce((total, rate, index, array) => {
                    total += rate;
                    if (index === array.length - 1) {
                        return total / array.length;
                    } else {
                        return total;
                    }
                })
            );
        }
        return (
            <tr>
                <td>{this.props.movie.title}</td>
                <td>{this.props.movie.releaseDate}</td>
                <td><img src={"icons/" + this.props.movie.genreIconUri}/><br/>
                    {this.props.movie.genreName}</td>
                <td>
                    {avgRate}
                </td>
                <td><RateMovie onRate={this.props.onRate} key={this.props.movie.id} movie={this.props.movie}/></td>
            </tr>
        )
    }
}

export default Movie;