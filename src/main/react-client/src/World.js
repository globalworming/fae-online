import React, { Component } from 'react'
import SockJsClient from "react-stomp";
import ConfigurableText from "./ConfigurableText";

class World extends Component {

  constructor(props) {
    super(props);
    this.state = {
      worldDescription: this.props.world.description
    }
  }

  submitUpdateDescription = description => {
    console.log("submit description", description);
    fetch("/world/" + this.props.world.id + "/description", {
      method: description ? 'PUT' : 'DELETE',
      body: description
    }).then(response => console.log(description ? "PUT" : "DELETE", response))
  }

  onMessageReceive = message => {
    console.log("got message", message);
    if (message.type === "UPDATE_DESCRIPTION") {
      console.log("update description", message.content);
      this.setState({worldDescription: message.content})
    }
  }

  render() {
    console.log("render world", this.state);
    return <section name="world">

      // FIXME move client to global, redux?
      <SockJsClient url="/ws" topics={["/world/" + this.props.world.id]}
                    onMessage={ this.onMessageReceive } ref={ (client) => { this.clientRef = client }} />

      <h1>welcome to {this.props.world.name}</h1>
      <ConfigurableText text={this.state.worldDescription} className="e2e-world-description" onSubmit={this.submitUpdateDescription}/>
    </section>

  }
}

export default World;