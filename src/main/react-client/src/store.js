import { createStore } from "redux";
import {ADD_MESSAGE, SET_MESSAGES, SET_SOCK_JS_CLIENT, SET_WORLD} from "./actionTypes";

const initialState = {
};

let reducer = (state = initialState, {type, payload}) => {
  console.log("reduce", initialState, type, payload);
  switch (type) {
    case SET_WORLD: {
      let newState = Object.assign({}, state);
      newState.world = payload;
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