import Furhat from './furhat';
import callback from './instanceCallback'


const furhat = new Furhat();

const connected = (status, hat) => {
  const connect = () => {
    hat.send({
      event_name: 'furhatos.event.requests.RequestSystemStatus',
    });
  };

  hat.subscribe('furhatos.event.senses.SenseSystemStarted', (event) => {
    const data = JSON.parse(event.data);
    console.log('connected')
    callback(status, hat)
  });

  connect();
};

const port = process.env.DEV_ENV ? 80 : window.location.port;

furhat.init(window.location.hostname, 80, 'api', connected);

export default furhat;
export { furhat as INSTANCE };
