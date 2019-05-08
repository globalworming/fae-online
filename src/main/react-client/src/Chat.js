import React, { Component } from 'react'
import ChatInput from './ChatInput'
import ChatMessage from './ChatMessage'
import SockJsClient from "react-stomp";


class Chat extends Component {
  state = {
    messages: [],
    fetched: false
  }


  componentDidMount() {
    fetch("/message/" + this.props.room).then(response => response.json())
        .then(json => this.setState({messages: json, fetched: true}))
  }

  addMessage = message =>
      this.setState(state => ({ messages: [message, ...state.messages] }))

  submitMessage = messageString => {
    // on submitting the ChatInput form, send the message, add it to the list and reset the input
    const message = { "sender": "sender", "content": messageString ,"type": "CHAT"};
    this.clientRef.sendMessage("/app/world/" + this.props.room + "/sendMessage", JSON.stringify(message));
  }

  onMessageReceive = message => {
    this.addMessage(message)
  }

  render() {
    if (!this.state.fetched) {
      return null
    }
    return (
        <section className="e2e-chat-messages">

          <SockJsClient url="/ws" topics={["/world/" + this.props.room]}
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