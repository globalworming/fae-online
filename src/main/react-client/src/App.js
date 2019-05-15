
import React, { Component} from "react";
import "./App.css";
import Chat from './Chat'
import World from './World'
import EnterWorld from './EnterWorld'

class App extends Component{

  state = { }

  enterWorld = world => {
    fetch("/" + world + "/id")
        .then(response => response.json())
        .then(id => fetch("/world/" + id)
            .then(response => response.json())
            .then(world => this.setState({world})))
  };

  render(){
    if (!this.state.world) {
      return <EnterWorld onSubmitRoom={this.enterWorld}/>
    }

    console.log("got world", this.state.world);

    return(
        <div className="App">
          <World world={this.state.world}/>
          <Chat world={this.state.world}/>
        </div>
    );
  }
}

export default App;