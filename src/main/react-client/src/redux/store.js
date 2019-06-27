import { createStore } from "redux";
import {
  ADD_MESSAGE,
  SET_CAMPAIGNS,
  SET_MESSAGES,
  SET_SCENARIOS,
  SET_SCENES,
  SET_SOCK_JS_CLIENT,
  SET_WORLD
} from "./actionTypes";

const initialState = {
};

let reducer = (state = initialState, {type, payload}) => {
  console.log("reduce", initialState, type, payload);
  switch (type) {
    case SET_WORLD: {
      let newState = Object.assign({}, state);
      // preserve campaigns
      // FIXME shouldn't this be case UPDATE_WORLD then?
      if (newState.world) {
        payload.campaigns = newState.world.campaigns;
      }
      newState.world = payload;
      return newState;
    }

    case SET_CAMPAIGNS: {
      let newState = Object.assign({}, state);
      newState.world.campaigns = payload;
      console.log("reduced to", newState);
      return newState;
    }

    case SET_SCENARIOS: {
      let newState = Object.assign({}, state);
      newState.world.campaigns.find(campaign => campaign.id === payload[0].campaign.id)
          .scenarios = payload;
      console.log("reduced to", newState);
      return newState;
    }

    case SET_SCENES: {
      let newState = Object.assign({}, state);
      newState.world.campaigns.flatMap(campaign => campaign.scenarios)
          .find(scenario => scenario.id === payload[0].scenario.id)
          .scenes = payload;
      console.log("reduced to", newState);
      return newState;
    }

    case SET_SOCK_JS_CLIENT: {
      let newState = Object.assign({}, state);
      newState.sockJsClient = payload;
      return newState;
    }

    case SET_MESSAGES: {
      let newState = Object.assign({}, state);
      newState.messages = payload;
      return newState;
    }

    case ADD_MESSAGE: {
      let newState = Object.assign({}, state);
      newState.messages = newState.messages.concat([payload]);
      return newState;
    }

    default:
      return state;
  }
};

export default createStore(reducer);