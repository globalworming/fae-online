import React, { Component } from 'react'


class TextForm extends Component {

  constructor(props) {
    super(props);
    this.state = {
      text: props.text || ''
    }
  }

  render() {
    let props = this.props;
    return (
        <form
            action=".."
            onSubmit={e => {
              e.preventDefault();
              this.props.onSubmit(this.state.text)
              this.setState({ text: '' })
            }}
        >
          {  props.type == "textarea" && <textarea
              className="e2e-input"
              placeholder={'add description'}
              value={this.state.text}
              onChange={e => this.setState({ text: e.target.value })}
          />
          }
          { props.type !== "textarea" && <input
              className="e2e-input"
              type={props.type}
              placeholder={'add description'}
              value={this.state.text}
              onChange={e => this.setState({ text: e.target.value })}
          />}
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