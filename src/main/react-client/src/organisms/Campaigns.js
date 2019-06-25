import React, { Component } from 'react'
import {connect} from 'react-redux'
import Scenarios from "./Scenarios";


class Campaigns extends Component {

  state = {
    campaigns: [],
    focus: 0,
  };

  componentDidMount() {
    fetch("/world/" + this.props.world.id + "/campaigns").then(response => response.json())
       // .then(json => this.props.setMessages(json))
        .then(json => this.setState({campaigns: json})  )
  }


  render() {
    if (this.state.campaigns.length === 0) {
      return null;
    }
    console.log("render campaign", this.state.campaigns[this.state.focus]);

    return <section name="campaigns">
      <nav>
        {this.state.campaigns.map((campaign, index) =>
          <p key={index} onClick={() => this.setFocus(index)}>{campaign.name}</p>
        )}
      </nav>
      <Scenarios campaign={this.state.campaigns[this.state.focus]} />

    </section>

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

export default connect(mapStateToProps)(Campaigns);