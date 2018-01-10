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
  }

  componentDidMount() {
    var socket = new WebSocket(`ws://localhost:80/api`);
    socket.onopen = (event) => {
      console.log('connected')
      socket.send(JSON.stringify( { event_name: 'furhatos.event.actions.ActionRealTimeAPISubscribe', name: 'furhatos.app.burger.OrderUpdateEvent' }));
    };
    socket.onmessage = (event) => {
      var data = JSON.parse(event.data);
      if (data.event_name == 'furhatos.app.burger.OrderUpdateEvent') {
        console.log(data)
        this.setState({main: data.main, side: data.side, drink: data.drink})
      }
    };
  }

  componentDidUpdate() {
    console.log('update')
  }

  render() {
    return (
      <div className="container-fluid">
        <div className="row">
            <div className="col-lg-4 order">
              <h2>Main</h2>
              <Item value={this.state.main} />
            </div>
            <div className="col-lg-4 order">
              <h2>Side</h2>
              <Item value={this.state.side} />
            </div>
            <div className="col-lg-4 order">
              <h2>Drink</h2>
              <Item value={this.state.drink}/>
            </div>
        </div>
      </div>
    );
  }
}
export default App;
