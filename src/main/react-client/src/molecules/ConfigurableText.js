import React, { Component } from 'react'
import TextForm from './TextForm'

class ConfigurableText extends Component {

  constructor(props) {
    super(props);
    console.log("props", props);
    this.state = {
      displayed: true,
      editable: false,
      text: props.text || ''
    }

    // This binding is necessary to make `this` work in the callback
    this.startEdit = this.startEdit.bind(this);
  }


  startEdit() {
    this.setState({
      displayed: false,
      editable: true,
      text: this.state.text
    })
  }

  onSubmit = text => {
    this.props.onSubmit(text);
    this.setState({
      displayed: true,
      editable: false,
      text: text
    })
  }

  onCancel = text => {
    this.setState({
      displayed: true,
      editable: false,
      text: text
    })
  }

  render() {
    return <div className={this.props.className} style={{whiteSpace: 'pre-line'}}>
      <h3>world description</h3>
      { this.state.displayed && this.props.text }
      { this.state.editable && <TextForm text={this.props.text} type="textarea"
                                         onSubmit={text => this.onSubmit(text)}
                                         onCancel={text => this.onCancel(text)} />}
      { this.state.editable || <button className="e2e-edit" onClick={this.startEdit}>edit</button> }
    </div>
  }

}

export default ConfigurableText;