import React, {Component} from 'react'
import FlightsContainer from './FlightsContainer'
import Header from './Header'

class App extends Component {

  constructor(props) {
    super(props);
    var temp = [{"duration":"00h55m","startTimeInLocal":"14:30","formattedTotalPrice":"599:-","price":599,"direct":true,"endTimeInLocal":"16:25","id":"O_2_2","flights":"SK712","productName":"SAS GO LIGHT","segments":[{"flight":"SK712","org":"ARN","dest":"HEL"}]},{"duration":"00h55m","startTimeInLocal":"15:20","formattedTotalPrice":"599:-","price":599,"direct":true,"endTimeInLocal":"17:15","id":"O_2_3","flights":"SK716","productName":"SAS GO LIGHT","segments":[{"flight":"SK716","org":"ARN","dest":"HEL"},{"flight":"SK723216","org":"ARsN","dest":"HfEL"}]},{"duration":"00h55m","startTimeInLocal":"17:35","formattedTotalPrice":"599:-","price":599,"direct":true,"endTimeInLocal":"19:30","id":"O_2_4","flights":"SK718","productName":"SAS GO LIGHT","segments":[{"flight":"SK718","org":"ARN","dest":"HEL"}]}];
    this.state = { "flights" : temp };

    this.system = parent.system;

    if (this.system) {
      this.system.onEvent = function (name, params) {
        if (name == "action.flight.results") {
          if (params.results) {
              console.log(params.results);
              this.setState({ "flights": params.results })
          }
          else {
            this.setState({ "flights": [] })
          }

        }
      }.bind(this)
    }
    else {
      console.log("Furhat system not loaded");
    }
  }

  selectFlight(id) {
    console.log(this.system);
    if (this.system) {
      this.system.sendEvent("action.flight.select", {
        id
      })
    }
    else {
      console.log("Can't send event to Furhat since Furhat system not loaded");
    }
  }

  render() {
    return (
      <div style={{'marginTop': '50px'}} className="container">
        <Header/>
        { this.state.flights.length == 0 ? <h3>Hello, my eyes are up there!</h3> :
          <FlightsContainer
          flights={this.state.flights}
          selectFlight={(id) => this.selectFlight(id)}
          />
        }

      </div>
    );
  }
}
export default App;
