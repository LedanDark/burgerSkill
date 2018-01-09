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
    console.log(items);
    return (
      <table ref="table" className="table table-striped">
        <thead>
          <tr>
            <th>Type</th>
            <th>Name</th>
          </tr>
        </thead>
        <tbody>
          {
            items.map((item) => {
              return <Item
                key={item.id}
                id={item.id}
                type={item.type}
                name={item.name}
                selectItem={(id) => this.props.selectItem(id)}
              />;
            })
          }
        </tbody>
      </table>
    );
  }
}
export default ItemContainer;
