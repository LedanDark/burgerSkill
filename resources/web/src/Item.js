import React, {Component} from 'react';

class Item extends Component {
  constructor(props) {
    super(props);
  }

  removeOrder() {
    var event = {
      event_name: 'RemoveOrder',
      item: this.props.value.value
    }
    this.props.furhat.send(JSON.stringify(event));
  }

  getItemContent() {
    if (this.props.value) {
      if (this.props.value.value == 'none') {
        return <h3>None</h3>
      }
      return (
        <div>
          <h3>{ this.props.value.value } <span onClick={this.removeOrder.bind(this)} className="orderRemove">Remove</span></h3>
          <img className="orderImage" src={`src\\img\\${this.props.value.value.replace(/ /g,'_')}.png`} />
        </div>
      );
    }
    return <h3>Not Selected</h3>;
  }
  render() {
    return (
      <div className="row main orderHolder">
        {this.getItemContent()}
      </div>
    )
  }
}
export default Item;
