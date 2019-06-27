import React, {Component} from "react";
import Chat from '../organisms/Chat'
import World from '../organisms/World'
import EnterWorld from '../organisms/EnterWorld'
import {connect} from 'react-redux'
import {setWorld, setSockJsClient, addChatMessage, setCampaigns, setScenarios, setScenes} from '../redux/actions'
import SockJsClient from "react-stomp";

class App extends Component {

  state = {
    fetched: false
  }

  componentDidMount() {
    // convenience world entering
    if (document.location.href.indexOf("world=") > -1) {
      this.enterWorld(document.location.href.split("world=")[1])
    }
  }

  enterWorld = world => {
    fetch("/service/enterWorld/" + world)
        .then(response => {console.log("resp", response); return response.json()})
        .then(world => this.props.setWorld(world));
  };

  fetchWorld = id => {
    console.log("start fetch world");
    fetch("/world/" + id).then(response => response.json())
        .then(world => {
          this.props.setWorld(world);
          return world.id;})
        .then(this.fetchCampaigns)
        .finally(() => this.setState({fetched: true}))
  };

  fetchCampaigns = worldId => {
    console.log("start fetch campaigns");
    return fetch("/world/" + worldId + "/campaigns").then(response => response.json())
        .then(json => {
          if (json.length > 0) {
            this.props.setCampaigns(json);
          }
          return json.map(campaign=> campaign.id)})
        .then(this.fetchScenarios)
  };

  fetchScenarios = campaignIds => {
    console.log("start fetch scenarios");
    return Promise.all(campaignIds.map(campaignId => {
      return fetch("/campaign/" + campaignId + "/scenarios").then(response => response.json())
          .then(json => {
            if (json.length > 0) {
              this.props.setScenarios(json);
            }
            return json.map(scenario => scenario.id)})
          .then(this.fetchScenes)
    }));
  };

  fetchScenes = scenarioIds => {
    console.log("start fetch scenes");
    return Promise.all(scenarioIds.map(scenarioId=> {
      return fetch("/scenario/" + scenarioId + "/scenes").then(response => response.json())
          .then(json => {
            if (json.length > 0) {
              this.props.setScenes(json)
            }
          })
    }));
  };



  onMessageReceive = message => {
    console.log("got message", message);
    switch (message.messageType) {
      case "CHAT": {
        this.props.addChatMessage(message);
      }
      case "UPDATE_WORLD": {
        this.fetchWorld(this.props.world.id)
      }
    }
  };


  render(){
    let state = this.state;
    let props = this.props;
    console.log("render app", "props", props, "state", state);
    if (!props.world) {
      return <EnterWorld onSubmitRoom={this.enterWorld}/>
    }

    return <div className="App">
      <SockJsClient url="/ws" topics={["/world/" + props.world.id]}
                    onMessage={ this.onMessageReceive }
                    ref={ (client) => { this.props.setSockJsClient(client) }}/>

      {this.state.fetched && <World world={props.world}/>}
      {//<Chat/>
      }
    </div>
        ;
  }
}

const mapStateToProps = ({world}) => {
  return {world};
}

const mapDispatchToProps = {setWorld, setCampaigns, setSockJsClient, addChatMessage, setScenarios, setScenes};


export default connect(mapStateToProps, mapDispatchToProps)(App)