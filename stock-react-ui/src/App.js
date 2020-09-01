import React from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import ListStockPricingComponent from './components/ListStockPricingComponent';
import ListStockComponent from './components/ListStockComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import CreateStockComponent from './components/CreateStockComponent';
import UpdateStockComponent from './components/UpdateStockComponent';

function App() {
  return (
    <div>
        <Router>
              <HeaderComponent />
                <div className="container">
                    <Switch> 
                          <Route path = "/" exact component = {ListStockPricingComponent}></Route>
                          <Route path = "/stockspricing" component = {ListStockPricingComponent}></Route>
                          <Route path = "/stocks" component = {ListStockComponent}></Route>
                          <Route path = "/add-stock/:id" component = {CreateStockComponent}></Route>
                          {/* <Route path = "/update-stock/:id" component = {UpdateStockComponent}></Route> */}
                    </Switch>
                </div>
              <FooterComponent />
        </Router>
    </div>
    
  );
}

export default App;
