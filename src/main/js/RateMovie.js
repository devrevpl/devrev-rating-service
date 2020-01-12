import StarRatingComponent from "react-star-rating-component";
import PropTypes from 'prop-types';
import axios from 'axios';

const React = require('react');
const ReactDOM = require('react-dom');

class RateMovie extends React.Component {

    static propTypes = {
        onRate: PropTypes.func.isRequired
    };

    constructor(props) {
        super(props);
        this.state = {
            rating: 0,
            editable: true
        };
    }

    onStarClick(nextValue, prevValue, name) {
        this.setState({rating: nextValue, editable: false});
        const movieId = this.props.movie.id;
        axios.patch(RATE_MOVIE_URL + movieId + '/ratings/' + nextValue)
            .then(res => this.props.onRate(movieId, nextValue))
            .catch(onRejected => {
                this.setState({rating: 0, editable: true});
                alert(onRejected);
            });
    }

    render() {
        const {rating} = this.state;
        return (
            <StarRatingComponent
                editing={this.state.editable}
                ref={this.props.movie.id}
                name={this.props.movie.id}
                starCount={10}
                value={rating}
                onStarClick={this.onStarClick.bind(this)}
            />
        )
    }
}

export default RateMovie;