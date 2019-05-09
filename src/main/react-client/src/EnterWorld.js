import React, { Component } from 'react'
import ChatInput from './ChatInput'
import ChatMessage from './ChatMessage'
import SockJsClient from "react-stomp";


class EnterWorld extends Component {

  state = { room: ''};

  render() {
    return (
        <section>
          <form
              action="."
              onSubmit={e => {
                e.preventDefault()
                this.props.onSubmitRoom(this.state.room)
              }}
          >
            <input
                className="e2e-world-input"
                type="text"
                placeholder={'Enter room...'}
                value={this.state.room}
                onChange={e => this.setState({ room: e.target.value })}
            />
            <input type="submit" value={'Send'} />
          </form>
        </section>
    )
  }
}

export default EnterWorld