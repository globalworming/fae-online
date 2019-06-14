import React, { Component } from 'react'
import ChatInput from './ChatInput'
import ChatMessage from './ChatMessage'
import SockJsClient from "react-stomp";

// FIXME rocketchat integration
class Chat extends Component {
  state = {
    messages: [],
    fetched: false
  }


  componentDidMount() {
    console.log("chat did mount", this.props.world);
    fetch("/message/" + this.props.world.id).then(response => response.json())
        .then(json => this.setState({messages: json, fetched: true}))
  }

  addMessage = message =>
      this.setState(state => ({ messages: [message, ...state.messages] }));

  submitMessage = messageString => {
    // on submitting the ChatInput form, send the message, add it to the list and reset the input
    const message = { "sender": "sender", "content": messageString ,"messageType": "CHAT"};
    console.log("submit chat message", JSON.stringify(message));
    this.clientRef.sendMessage("/app/world/sendMessage/" + this.props.world.id, JSON.stringify(message));
  }

  onMessageReceive = message => {
    if (message.messageType === "CHAT") {
      this.addMessage(message)
    }
  }

  render() {
    if (!this.state.fetched) {
      return null
    }
    return (
        <section className="e2e-chat-messages">

          <SockJsClient url="/ws" topics={["/world/" + this.props.world.id]}
                        onMessage={ this.onMessageReceive } ref={ (client) => { this.clientRef = client }} />

          <ChatInput
              onSubmitMessage={messageString => this.submitMessage(messageString)}
          />

          {this.state.messages.map((message, index) =>
              <ChatMessage
                  key={index}
                  message={message.content}
                  name={message.sender}
              />,
          )}
        </section>
    )
  }
}

export default Chat