import React, { Component } from 'react'
import ConfigurableText from "../molecules/ConfigurableText";
import {connect} from 'react-redux'
import Campaign from './Campaign'


class World extends Component {

  submitUpdateDescription = description => {
    console.log("submit description", description);
    fetch("/world/" + this.props.world.id + "/description", {
      method: description ? 'PUT' : 'DELETE',
      body: description
    }).then(response => console.log(description ? "PUT" : "DELETE", response))
  }


  render() {
    console.log("render world", this.props);
    let world = this.props.world;
    return <React.Fragment>
      <section name="world">

      <h1>welcome to {world.name}</h1>
      <ConfigurableText text={world.description} className="e2e-world-description" onSubmit={this.submitUpdateDescription}/>

      </section>
      <Campaign world={world} />
    </React.Fragment>
  }
}

const mapStateToProps = ({world, sockJsClient}) => {
  return {world, sockJsClient};
}

export default connect(mapStateToProps)(World);