import {ADD_MESSAGE, SET_MESSAGES, SET_SOCK_JS_CLIENT, SET_WORLD} from './actionTypes'

export const setWorld = world => ({
  type: SET_WORLD,
  payload: world
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

