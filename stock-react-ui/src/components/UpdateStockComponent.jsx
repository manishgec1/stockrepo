import React, { Component } from 'react'
import StockService from '../services/StockService';

class UpdateStockComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            stockSymbol: '',
            bidPrice: '',
            askPrice: ''
        }
        this.changeStockSymbolHandler = this.changeStockSymbolHandler.bind(this);
        this.changeBidPriceHandler = this.changeBidPriceHandler.bind(this);
        this.changeAskPriceHandler = this.changeAskPriceHandler.bind(this);
        this.updateStock = this.updateStock.bind(this);
    }

    componentDidMount(){
        StockService.getStockById(this.state.id).then( (res) =>{
            let stock = res.data;
            this.setState({stockSymbol: stock.stockSymbol,
                             bidPrice: stock.bidPrice,
                             askPrice : stock.askPrice
            });
        });
    }

    updateStock = (e) => {
        e.preventDefault();
        let stock = {stockSymbol: this.state.stockSymbol, bidPrice: this.state.bidPrice, askPrice: this.state.askPrice};
        console.log('stock => ' + JSON.stringify(stock));
        console.log('id => ' + JSON.stringify(this.state.id));
        StockService.updateStock(stock, this.state.id).then( res => {
            this.props.history.push('/stockspricing');
        });
    }
    
    changeStockSymbolHandler= (event) => {
        this.setState({stockSymbol: event.target.value});
    }

    changeBidPriceHandler= (event) => {
        this.setState({bidPrice: event.target.value});
    }

    changeAskPriceHandler= (event) => {
        this.setState({askPrice: event.target.value});
    }

    cancel(){
        this.props.history.push('/stockspricing');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update DBS Stock</h3>
                                <div className = "card-body">
                                    <form>
                                    <div className = "form-group">
                                            <label> Stock Symbol: </label>
                                            <input placeholder="Stock Synbol" name="stockSymbol" className="form-control" 
                                                value={this.state.stockSymbol} onChange={this.changeStockSymbolHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label>Bid Price: </label>
                                            <input placeholder="Bid Price" name="bidPrice" className="form-control" 
                                                value={this.state.bidPrice} onChange={this.changeBidPriceHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Ask Price: </label>
                                            <input placeholder="Ask Price" name="askPrice" className="form-control" 
                                                value={this.state.askPrice} onChange={this.changeAskPriceHandler}/>
                                        </div>

                                        <button className="btn btn-success" onClick={this.updateStock}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }
}

export default UpdateStockComponent
