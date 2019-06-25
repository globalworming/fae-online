import React, { Component } from 'react'
import ConfigurableText from "../molecules/ConfigurableText";
import {connect} from 'react-redux'
import ChatMessage from "../molecules/ChatMessage";


class Campaign extends Component {

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
    console.log("render campaigns", this.state.campaigns);
    return <section name="campaigns">
      <nav>
        {this.state.campaigns.map((campaign, index) =>
          <p key={index}>{campaign.name}</p>
        )}
      </nav>

    </section>

  }
}

const mapStateToProps = ({}) => {
  return {};
}

export default connect(mapStateToProps)(Campaign);