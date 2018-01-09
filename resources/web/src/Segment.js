import React, {Component} from 'react';

class Segment extends Component {
  render() {
    return (
      <span style={{marginRight: '10px'}}>
        {this.props.org}
        <span style={{marginLeft: '10px', marginRight: '10px'}} className="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
        {this.props.dest}
      </span>
    );
  }
}
export default Segment;
