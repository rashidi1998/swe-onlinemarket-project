import React from "react";

export function withTitleBar(Component, title) {
  return class extends React.Component {
    render() {
      return (
        <React.Fragment>
          <div className="header">
            <h1>
              {[...title].map((c) => (
                <span className="hover-ecffect">{c}</span>
              ))}
            </h1>
          </div>
          <Component {...this.props} />
        </React.Fragment>
      );
    }
  };
}
