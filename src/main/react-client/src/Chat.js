import React, { Component } from 'react'
import ChatInput from './ChatInput'
import ChatMessage from './ChatMessage'
import {connect} from 'react-redux'
import {setMessages} from './actions'

class Chat extends Component {
  state = {
    fetched: false
  };

  componentDidMount() {
    console.log("chat did mount", this.props.world);
    fetch("/message/" + this.props.world.id).then(response => response.json())
        .then(json => this.props.setMessages(json))
        .then(() => this.setState({fetched: true})  )
  }

  submitMessage = messageString => {
    const message = { "sender": "sender", "content": messageString ,"messageType": "CHAT"};
    console.log("submit chat message", JSON.stringify(message));
    this.props.sockJsClient.sendMessage("/app/world/sendMessage/" + this.props.world.id, JSON.stringify(message));
  };

  render() {
    console.log("render chat", this.props);
    if (!this.state.fetched) {
      return null
    }
    return (
        <section className="e2e-chat-messages">

          <ChatInput onSubmitMessage={messageString => this.submitMessage(messageString)} />

          {this.props.messages.map((message, index) =>
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

const mapStateToProps = ({messages, world, sockJsClient}) => {
  return {messages, world, sockJsClient};
}

const mapDispatchToProps = {setMessages};

export default connect(mapStateToProps, mapDispatchToProps)(Chat)