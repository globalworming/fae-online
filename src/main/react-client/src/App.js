
import React, { Component} from "react";
import "./App.css";
import Chat from './Chat'
import EnterRoom from './EnterRoom'

class App extends Component{

  state = {}

  enterRoom = room => {
    this.setState({room})
  }

  render(){
    if (!this.state.room) {
      return <EnterRoom onSubmitRoom={this.enterRoom}/>
    }


    return(
        <div className="App">
          <h1>welcome to {this.state.room}</h1>
          <Chat room={this.state.room}/>
        </div>
    );
  }
}

export default App;