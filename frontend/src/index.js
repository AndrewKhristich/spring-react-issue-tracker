import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import registerServiceWorker from './registerServiceWorker';
import MainHeader from "./container/MainHeader"

ReactDOM.render(<MainHeader />, document.getElementById('main-header'));

registerServiceWorker();
