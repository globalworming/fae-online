import React, { Component } from 'react'

class World extends Component {


  render() {
    return <section name="world">
      <h1>welcome to {this.props.world.name}</h1>
      <div className="e2e-world-description">{this.props.world.description}</div>
    </section>

  }
}

export default World;