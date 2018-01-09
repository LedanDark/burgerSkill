import React, {Component} from 'react';

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
        <td>{this.props.type}</td>
        <td>{this.props.name}</td>
      </tr>
    )
  }
}
export default Item;
