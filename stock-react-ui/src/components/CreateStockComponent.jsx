import React, { Component } from 'react'
import StockService from '../services/StockService';

class CreateStockComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
            stockSymbol: '',
            bidPrice: '',
            askPrice: ''
        }
        this.changeStockSymbolHandler = this.changeStockSymbolHandler.bind(this);
        this.changeBidPriceHandler = this.changeBidPriceHandler.bind(this);
        this.changeAskPriceHandler = this.changeAskPriceHandler.bind(this);
        this.saveOrUpdateStock = this.saveOrUpdateStock.bind(this);
    }

    // step 3
    componentDidMount(){

        // step 4
        if(this.state.id === '_add'){
            return
        }else{
            console.log(" this.state.id = " +this.state.id );
            StockService.getStockById(this.state.id).then( (res) =>{
                let stock = res.data;
                this.setState({stockSymbol: stock.stockSymbol,
                                 bidPrice: stock.bidPrice,
                                 askPrice : stock.askPrice
                });
            });
        }        
    }
    saveOrUpdateStock = (e) => {
        e.preventDefault();
        let stock = {stockSymbol: this.state.stockSymbol, bidPrice: this.state.bidPrice, askPrice: this.state.askPrice};
        console.log('create stock => ' + JSON.stringify(stock));

        // step 5
        if(this.state.id === '_add'){
            console.log('ADD stock => ');
            StockService.createStock(stock).then(res =>{
                this.props.history.push('/stockspricing');
            });
        }else{
            console.log('UPDATE stock => ' +this.state.id );
            StockService.updateStock(stock, this.state.id).then( res => {
                this.props.history.push('/stockspricing');
            });
        }
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

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add Stock</h3>
        }else{
            return <h3 className="text-center">Update Stock</h3>
        }
    }
    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                {
                                    this.getTitle()
                                }
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

                                        <button className="btn btn-success" onClick={this.saveOrUpdateStock}>Save</button>
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

export default CreateStockComponent
