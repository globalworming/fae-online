import React, { Component } from 'react'
import Scenarios from "./Scenarios";

class Campaigns extends Component {

  state = {
    focusStack: undefined,
  };

  setFocus = id => {
    this.setState({focus: id})
  };

  render() {
    const props = this.props;
    const focus = this.state.focus;
    const campaigns = props.world.campaigns;

    console.log("render campaign", "props", props, "state", this.state);

    let index = 0;
    if (focus) {
      index = campaigns.findIndex(campaign => campaign.id === focus);
    }

    const campaign = campaigns[index];

    return <React.Fragment>
      <section name="campaigns">
        <h2>campaigns</h2>
        <nav>
          {campaigns.map((campaign) =>
            <p key={campaign.id} onClick={() => this.setFocus(campaign.id)}>{campaign.name}</p>
          )}
        </nav>
      </section>
      {campaign.scenarios && <Scenarios campaign={campaign}/>}
    </React.Fragment>

  }
}

export default Campaigns;