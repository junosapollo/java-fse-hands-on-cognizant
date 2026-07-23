import React, { Component } from 'react';

export class CountPeople extends Component {
  constructor(props) {
    super(props);
    this.state = { entrycount: 0, exitcount: 0 };
  }

  UpdateEntry = () => this.setState(({ entrycount }) => ({ entrycount: entrycount + 1 }));

  UpdateExit = () => this.setState(({ exitcount }) => ({ exitcount: exitcount + 1 }));

  render() {
    return (
      <section className="counter-app">
        <h1>Mall Visitor Counter</h1>
        <p>People entered: <strong data-testid="entry-count">{this.state.entrycount}</strong></p>
        <p>People exited: <strong data-testid="exit-count">{this.state.exitcount}</strong></p>
        <button type="button" onClick={this.UpdateEntry}>Login</button>
        <button type="button" onClick={this.UpdateExit}>Exit</button>
      </section>
    );
  }
}

function App() { return <CountPeople />; }

export default App;
