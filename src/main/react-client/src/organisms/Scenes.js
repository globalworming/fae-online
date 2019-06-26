import React, { Component } from 'react'
import {connect} from 'react-redux'


class Scenes extends Component {

  state = {
    scenes: [],
    focus: 0,
  };

  componentDidMount() {
    fetch("/scenario/" + this.props.scenario.id + "/scenes").then(response => response.json())
        .then(json => this.setState({scenes: json})  )
  }

  render() {
    console.log("render scenes", this.state.scenes);
    if (this.state.scenes.length === 0) {
      return null;
    }

    return<React.Fragment>
      <section className="scenes">
        <nav>
          <h4>scenes</h4>
          {this.state.scenes.map((scene, index) =>
            <p key={index} onClick={() => this.setFocus(index)}>{scene.name}</p>
          )}
        </nav>
      </section>
      <p>focused: {this.state.scenes[this.state.focus].name}</p>
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

export default connect(mapStateToProps)(Scenes);