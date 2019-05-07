import React, { Component } from 'react'
import ChatInput from './ChatInput'
import ChatMessage from './ChatMessage'
import SockJsClient from "react-stomp";


class Chat extends Component {
  state = {
    name: 'Bob',
    messages: [{message: "msg 1" , name: "name1"}],
  }


  componentDidMount() {
    this.setState({name: "loading"});
  }

  addMessage = message =>
      this.setState(state => ({ messages: [message, ...state.messages] }))

  submitMessage = messageString => {
    // on submitting the ChatInput form, send the message, add it to the list and reset the input
    const message = { "sender": this.state.name, "content": messageString ,"type": "CHAT"};
    console.log("send", message);
    this.clientRef.sendMessage("/app/chat.sendMessage", JSON.stringify(message));
    this.addMessage(message)
  }

  onMessageReceive = messageString => {
   console.log(messageString)
  }

  render() {
    return (
        <div>

          <SockJsClient url="/ws" topics={["/topic/public"]}
                        onMessage={ this.onMessageReceive } ref={ (client) => { this.clientRef = client }} />


          <label htmlFor="name">
            Name:&nbsp;
            <input
                type="text"
                id={'name'}
                placeholder={'Enter your name...'}
                value={this.state.name}
                onChange={e => this.setState({ name: e.target.value })}
            />
          </label>

          <ChatInput
              onSubmitMessage={messageString => this.submitMessage(messageString)}
          />

          {this.state.messages.map((message, index) =>
              <ChatMessage
                  key={index}
                  message={message.message}
                  name={message.name}
              />,
          )}
        </div>
    )
  }
}

export default Chat