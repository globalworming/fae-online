import React, { Component } from 'react'
import Scenarios from "./Scenarios";

class ContentContainer extends Component {

  state = {
    focusStack : []
  }

  render() {
    console.log("render content container", "props", this.props);
    const props = this.props;
    const root = props.root;
    let contentStack = props.contentStack;
    const contentName = contentStack.shift();
    const contents = root[contentName];

    if (!contents || contents.length === 0) {
      console.log("no content");
      return null;
    }

    return <React.Fragment>
      <section className={contentName}>
        <h2>{contentName}</h2>
        <nav>
          {contents.map((e) =>
            <p key={e.id} //onClick={() => this.setFocus(campaign.id)}>{campaign.name}</p>
            >{e.name}</p>
          )}
        </nav>
      </section>
      <ContentContainer root={contents[0]} contentStack={contentStack}/>
    </React.Fragment>

  }
}

export default ContentContainer;