import React, { Component } from 'react'
import StockService from '../services/StockService';

class ListStockComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                stocks: []
        }
   
        this.addStock = this.addStock.bind(this);
        this.editStock = this.editStock.bind(this);
        this.deleteStock = this.deleteStock.bind(this);
        this.viewStockPricing = this.viewStockPricing.bind(this);
    }

    addStock(){
        this.props.history.push('/add-stock/_add');
    }

    editStock(id){
        console.log(" editStock = " +id)
        this.props.history.push(`/add-Stock/${id}`);
    }

    viewStockPricing(){
        this.props.history.push('/stockspricing');
    }


    deleteStock(id){
        StockService.deleteStock(id).then( res => {
            this.setState({stock: this.state.stocks.filter(stock => stock.id !== id)});
        });
        this.props.history.push('/stocks');
    }
   
    componentDidMount(){
        StockService.getStocks().then((res) => {
            this.setState({ stocks: res.data});
        });   
    }

    render() {
     
        return (
            <div>
                 <h2 className="text-center">DBS Stock List</h2>
                 <div className = "row">
                    <button style={{marginLeft: "10px"}} onClick={ this.viewStockPricing} className="btn btn-info">Stock Pricing</button>
                 </div>
                 <br></br>
                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> Stock Symbol</th>
                                    <th> Bid Price</th>
                                    <th> Ask Price</th>
                                    <th> Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.stocks.map(
                                        stock => 
                                        <tr key = {stock.id}>
                                             <td> {stock.stockSymbol} </td>   
                                             <td> {stock.bidPrice}</td>
                                             <td> {stock.askPrice}</td>
                                             <td>
                                                 <button onClick={ () => this.editStock(stock.id)} className="btn btn-info">Update </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deleteStock(stock.id)} className="btn btn-danger">Delete </button>
                                             </td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>

                 </div>

            </div>
        )
    }
}

export default ListStockComponent
