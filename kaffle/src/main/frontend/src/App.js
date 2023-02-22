import logo from './logo.svg';
import './App.css';

import React, {useEffect, useState} from 'react';
import Board from './board.js';

function App() {

  

  return (
    <div className="App">
      {/* <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Test Game
        </p>
      </header> */}

      <main>
        <Board />
      </main>
    </div>
  );
}

export default App;