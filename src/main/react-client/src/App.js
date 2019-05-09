
import React, { Component} from "react";
import "./App.css";
import Chat from './Chat'
import EnterWorld from './EnterWorld'

class App extends Component{

  state = { }

  enterWorld = world => {
    fetch("/" + world + "/id").then(response => response.json())
        .then(data => this.setState({world: {id: data, name: world}}))
    //this.setState({room})
  }

  render(){
    if (!this.state.world) {
      return <EnterWorld onSubmitRoom={this.enterWorld}/>
    }

    console.log("got world", this.state.world);

    return(
        <div className="App">
          <h1>welcome to {this.state.world.name}</h1>
          <Chat world={this.state.world}/>
        </div>
    );
  }
}

export default App;