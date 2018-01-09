import React, {Component} from 'react';
import Segment from './Segment.js';

class Item extends Component {

  componentWillMount() {
    this.setState({
       success: false,
    })
  }

  handleClick() {
    this.props.selectItem(this.props.id);
    this.setState({ success: !this.state.success })
  }

  render() {
    const success = this.state.success;

    return (
      <tr className={success ? "success" : ""} onClick={() => this.handleClick()}>
        <td>{this.props.startTime}</td>
        <td>{this.props.endTime}</td>
        <td>{this.props.price}</td>
        <td>
          {
            this.props.segments.map((segment, i) => {
              return <Segment
                key={this.props.id + i}
                org={segment.org}
                dest={segment.dest}
                flight={segment.flight}
                />;
            })
          }
        </td>
      </tr>
    )
  }
}
export default FlightEntry;
