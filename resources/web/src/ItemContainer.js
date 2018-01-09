import React, {Component} from 'react';
import Item from './Item';
import ReactDOM from 'react-dom';

class ItemContainer extends Component {

  componentDidMount() {
    var element = ReactDOM.findDOMNode(this.refs.table);
    element.setAttribute('item-id', 'itemTable');
  }

  render() {
    const items = this.props.items;

    return (
      <table ref="table" className="table table-striped">
        <thead>
          <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Size</th>
          </tr>
        </thead>
        <tbody>
          {
            items.map((item) => {
              return <Item
                key={item.id}
                id={item.id}
                name={item.name}
                price={item.price}
                size={item.size}
                selectItem={(id) => this.props.selectItem(id)}
              />;
            })
          }
        </tbody>
      </table>
    );
  }
}
export default itemsContainer;
