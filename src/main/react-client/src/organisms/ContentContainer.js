import React, { Component } from 'react'

class ContentContainer extends Component {

  state = {
    focusStack : []
  }

  render() {
    console.log("render content container", "props", this.props);
    const props = this.props;
    const root = props.root;
    let contentStack = Object.assign([], props.contentStack);
    const contentName = contentStack.shift();
    const contents = root[contentName];

    if (!contents || contents.length === 0) {
      console.log("no content");
      return null;
    }

    let tab = '=='.repeat(2 - contentStack.length);
    tab = tab + "=>";

    return <React.Fragment>
      <section className={contentName}>
        <nav>
          {contents.map((e) =>
            <div key={props.containerKey + " " + e.id}>
                <p>{tab} {e.name} - one of the {contentName}</p>
                {console.log("render " + contentName, e)}
                <ContentContainer containerKey={props.containerKey + " " + e.id} root={e} contentStack={contentStack}/>
              </div>
          )}

        </nav>
      </section>
    </React.Fragment>

  }
}

export default ContentContainer;