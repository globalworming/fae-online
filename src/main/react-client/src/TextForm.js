import React, { Component } from 'react'
import ChatInput from './ChatInput'
import ChatMessage from './ChatMessage'
import SockJsClient from "react-stomp";


class TextForm extends Component {

  constructor(props) {
    super(props);
    this.state = {
      text: props.text
    }
  }

  render() {
    return (
        <form
            action="."
            onSubmit={e => {
              e.preventDefault();
              this.props.onSubmit(this.state.text)
              this.setState({ text: '' })
            }}
        >
          <input
              className="e2e-input"
              type={this.props.type}
              placeholder={'add description'}
              value={this.state.text}
              onChange={e => this.setState({ text: e.target.value })}
          />
          <input type="submit" value={'Send'} />
          <button className="e2e-cancel" onClick={e => {
            this.props.onCancel(this.props.text);
            this.setState({ text: this.props.text })
          }}>Cancel</button>
        </form>
    )
  }

}

export default TextForm