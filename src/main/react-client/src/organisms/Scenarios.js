import React, { Component } from 'react'
import {connect} from 'react-redux'
import Scenes from "./Scenes";


class Scenarios extends Component {

  // FIXME only fetch once for every campaign

  state = {
    scenarios: [],
    focus: 0,
  };

  fetchData() {
    fetch("/campaigns/" + this.props.campaign.id + "/scenarios").then(response => response.json())
    // .then(json => this.props.setMessages(json))
        .then(json => this.setState({scenarios: json})  )
  }

  componentDidMount() {
    this.fetchData()
  }

  componentDidUpdate(prevProps) {
    if (this.props.campaign.id !== prevProps.campaign.id) {
      this.fetchData();
    }
  }

  render() {
    console.log("render scenarios", this.state.scenarios);
    if (this.state.scenarios.length === 0) {
      return null;
    }

    return <React.Fragment>
      <section className="scenarios">
        <h3>scenarios</h3>
        <nav>
          {this.state.scenarios.map((scenario, index) =>
            <p key={index} onClick={() => this.setFocus(index)}>{scenario.name}</p>
          )}
        </nav>
      </section>
      <Scenes scenario={this.state.scenarios[this.state.focus]} />
    </React.Fragment>
  }

  setFocus = index => {
    let newState = Object.assign({}, this.state);
    newState.focus = index;
    this.setState(newState)
  }

}

const mapStateToProps = ({}) => {
  return {};
}

export default connect(mapStateToProps)(Scenarios);