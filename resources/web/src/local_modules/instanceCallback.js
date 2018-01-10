import dataModel from './data'
import App from '../App'

const callback = (status, hat) => {
  if (status === 'open') {
    hat.subscribe('furhatos.app.burger.OrderUpdateEvent', function(event) {            
      var data = JSON.parse(event.data);
      console.log(data);
    });
  }
};

export default callback;
