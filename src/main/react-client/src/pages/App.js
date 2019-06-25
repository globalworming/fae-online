import React, {Component} from "react";
import Chat from '../organisms/Chat'
import World from '../organisms/World'
import EnterWorld from '../organisms/EnterWorld'
import {connect} from 'react-redux'
import {setWorld, setSockJsClient, addChatMessage} from '../redux/actions'
import SockJsClient from "react-stomp";

class App extends Component {

  enterWorld = world => {
    fetch("/service/enterWorld/" + world)
        .then(response => {console.log("resp", response); return response.json()})
        .then(world => this.props.setWorld(world));
  };


  fetchWorld = id => {
    fetch("/world/" + id)
        .then(response => response.json())
        .then(world => this.props.setWorld(world));
  };

  onMessageReceive = message => {
    console.log("got message", message);
    switch (message.messageType) {
      case "CHAT": {
        this.props.addChatMessage(message);
      }
      case "UPDATE_WORLD": {
        this.fetchWorld(this.props.world.id)
      }
    }
  };


  render(){
    let props = this.props;
    console.log("app props:" , props );
    if (!props.world) {
      return <EnterWorld onSubmitRoom={this.enterWorld}/>
    }


    console.log("got world", props.world);

    return(
        <div className="App">

          <SockJsClient url="/ws" topics={["/world/" + props.world.id]}
                        onMessage={ this.onMessageReceive }
                        ref={ (client) => { this.props.setSockJsClient(client) }}/>

          <World />
          <Chat/>
        </div>
    );
  }
}

const mapStateToProps = ({world}) => {
  console.log("app mapStateToProps", {world});
  return {world};
}

const mapDispatchToProps = {setWorld, setSockJsClient, addChatMessage};


export default connect(mapStateToProps, mapDispatchToProps)(App)