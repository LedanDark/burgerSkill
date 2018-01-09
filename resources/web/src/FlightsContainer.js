import React, {Component} from 'react';
import FlightEntry from './FlightEntry';
import ReactDOM from 'react-dom';

class FlightsContainer extends Component {


  componentDidMount() {
    var element = ReactDOM.findDOMNode(this.refs.table);
    element.setAttribute('item-id', 'flightTable');
  }

  render() {
    const flights = this.props.flights;

    return (
      <table ref="table" className="table table-striped">
        <thead>
          <tr>
            <th>Departure</th>
            <th>Arrival</th>
            <th>Price (SEK)</th>
            <th>Segments</th>
          </tr>
        </thead>
        <tbody>
          {
            flights.map((flight) => {
              return <FlightEntry
                key={flight.id}
                id={flight.id}
                startTime={flight.startTimeInLocal}
                endTime={flight.endTimeInLocal}
                price={flight.formattedTotalPrice}
                direct={flight.direct}
                segments={flight.segments}
                selectFlight={(id) => this.props.selectFlight(id)}
              />;
            })
          }
        </tbody>
      </table>
    );
  }
}
export default FlightsContainer;
