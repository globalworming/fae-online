import {
  ADD_MESSAGE,
  SET_MESSAGES,
  SET_SOCK_JS_CLIENT,
  SET_WORLD,
  SET_CAMPAIGNS,
  SET_SCENARIOS,
  SET_SCENES
} from './actionTypes'

export const setWorld = world => ({
  type: SET_WORLD,
  payload: world
});

export const setCampaigns = campaigns => ({
  type: SET_CAMPAIGNS,
  payload: campaigns
});

export const setScenarios = campaigns => ({
  type: SET_SCENARIOS,
  payload: campaigns
});

export const setScenes = campaigns => ({
  type: SET_SCENES,
  payload: campaigns
});

export const setSockJsClient = client => ({
  type: SET_SOCK_JS_CLIENT,
  payload: client
});

export const setMessages = messages => ({
  type: SET_MESSAGES,
  payload: messages
});

export const addChatMessage = message => ({
  type: ADD_MESSAGE,
  payload: message
});

