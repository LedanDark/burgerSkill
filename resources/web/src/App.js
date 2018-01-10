import React, {Component} from 'react'
import Item from './Item'

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      main: undefined,
      side: undefined,
      drink: undefined
    }
    this.socket = new WebSocket('ws://localhost:80/api');
    this.socket.onopen = (event) => {
      this.socket.send(JSON.stringify( { event_name: 'furhatos.event.actions.ActionRealTimeAPISubscribe', name: 'furhatos.app.burger.OrderUpdateEvent' }));
    };
    this.socket.onmessage = (event) => {
      var data = JSON.parse(event.data);
      if (data.event_name == 'furhatos.app.burger.OrderUpdateEvent') {
        this.setState({main: data.main, side: data.side, drink: data.drink})
      }
    };
  }

  render() {
    return (
      <div className="container-fluid">
        <div className="row">
            <div className="col-lg-4 order">
              <h2>Main</h2>
              <Item value={this.state.main} furhat={this.socket} />
            </div>
            <div className="col-lg-4 order">
              <h2>Side</h2>
              <Item value={this.state.side} furhat={this.socket} />
            </div>
            <div className="col-lg-4 order">
              <h2>Drink</h2>
              <Item value={this.state.drink} furhat={this.socket} />
            </div>
        </div>
      </div>
    );
  }
}
export default App;
