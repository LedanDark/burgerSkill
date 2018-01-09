import React, {Component} from 'react'
import ItemContainer from './ItemContainer'
import Header from './Header'

class App extends Component {

  constructor(props) {
    super(props);
    var temp = []
    this.state = {
      "main": {
        "value": "Hamburger"
      },
      "side": {
        "value": "Fries"
      },
      "drink": {
        "value": "Coke Zero"
      }
    };

    this.system = parent.system;

    if (this.system) {
      this.system.onEvent = function (name, params) {
        if (name == "action.item.results") {
          if (params.results) {
              console.log(params.results);
              this.setState({ "items": params.results })
          }
          else {
            this.setState({ "items": [] })
          }

        }
      }.bind(this)
    }
    else {
      console.log("Furhat system not loaded");
    }
  }

  selectItem(id) {
    console.log(this.system);
    if (this.system) {
      this.system.sendEvent("action.item.select", {
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
        { this.state.length == 0 ? <h3>Hello, my eyes are up there!</h3> :
          <ItemContainer
          items={this.state}
          selectItem={(id) => this.selectItem(id)}
          />
        }

      </div>
    );
  }
}
export default App;
