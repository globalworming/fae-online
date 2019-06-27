import React, { Component } from 'react'
import {connect} from 'react-redux'
import Scenarios from "./Scenarios";


class Scenes extends Component {

  state = {
    focus: undefined,
  };

  setFocus = id => {
    this.setState({focus: id})
  };

  render() {
    const props = this.props;
    const focus = this.state.focus;
    const scenes = props.scenario.scenes;

    console.log("render scenes", "props", props, "state", this.state);


    let index = 0;
    if (focus) {
      index = scenes.findIndex(e => e.id === focus);
    }

    return <React.Fragment>
      <section className="scenes">
        <h2>scenes</h2>
        <nav>
          {scenes.map((e) =>
              <p key={e.id} onClick={() => this.setFocus(e.id)}>{e.name}</p>
          )}
        </nav>
      </section>
      <p>focused: {scenes[index].name}</p>
    </React.Fragment>

  }
}

export default Scenes;