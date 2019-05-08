import React, { Component } from 'react'
import ChatInput from './ChatInput'
import ChatMessage from './ChatMessage'
import SockJsClient from "react-stomp";


class Chat extends Component {
  state = {
    messages: [{sender: "sender" , content: "name1", type: "CHAT"}],
    fetched: false
  }


  componentDidMount() {
    fetch("/message").then(response => response.json())
        .then(json => this.setState({messages: json.content, fetched: true}))
  }

  addMessage = message =>
      this.setState(state => ({ messages: [message, ...state.messages] }))

  submitMessage = messageString => {
    // on submitting the ChatInput form, send the message, add it to the list and reset the input
    const message = { "sender": "sender", "content": messageString ,"type": "CHAT"};
    console.log("send", message);
    this.clientRef.sendMessage("/app/chat.sendMessage", JSON.stringify(message));
  }

  onMessageReceive = message => {
    this.addMessage(message)
  }

  render() {
    if (!this.state.fetched) {
      return null
    }

    return (
        <div>

          <SockJsClient url="/ws" topics={["/topic/public"]}
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
        </div>
    )
  }
}

export default Chat