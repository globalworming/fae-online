import React, { Component } from 'react'
import {connect} from 'react-redux'
import Scenes from "./Scenes";
import {setScenarios} from "../redux/actions";


class Scenarios extends Component {

  state = {
    focus: undefined,
  };

  setFocus = id => {
    this.setState({focus: id})
  };

  render() {
    const props = this.props;
    const focus = this.state.focus;
    const scenarios = props.campaign.scenarios;

    console.log("render scenarios", "props", props, "state", this.state);

    let index = 0;
    if (focus) {
      index = scenarios.findIndex(e => e.id === focus);
    }

    const scenario = scenarios[index];

    return <React.Fragment>
      <section className="scenarios">
        <h2>scenarios</h2>
        <nav>
          {scenarios.map((e) =>
              <p key={e.id} onClick={() => this.setFocus(e.id)}>{e.name}</p>
          )}
        </nav>
      </section>
      {scenario.scenes && <Scenes scenario={scenarios[index]}/>}
    </React.Fragment>

  }
}

export default Scenarios;