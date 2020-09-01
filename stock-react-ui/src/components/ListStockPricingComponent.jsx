import React, { Component } from 'react'
import StockService from '../services/StockService';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowAltCircleDown, faArrowAltCircleUp } from "@fortawesome/free-solid-svg-icons";


class ListStockPricingComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                stocks: [],
                isUpDown: true
        }
   
        this.addStock = this.addStock.bind(this);
        this.viewStock = this.viewStock.bind(this);
    }

    componentDidMount(){
        StockService.getViewStocks().then((res) => {
            this.setState({ stocks: res.data});
        });

       
    }

    addStock(){
        this.props.history.push('/add-stock/_add');
    }

    viewStock(){
        this.props.history.push('/stocks');
    }

    render() {
     
        return (
            <div>
                 <h2 className="text-center">DBS Stock List</h2>
                 <div className = "row">
                    <button className="btn btn-primary" onClick={this.addStock}> Add Stock</button>
                    <button style={{marginLeft: "10px"}} onClick={ this.viewStock} className="btn btn-info">View Stock</button>
                                             
                 </div>
                 <br></br>
                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> Stock Symbol</th>
                                    <th> Market Price</th>
                                    <th> Trend</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.stocks.map(
                                        stock => 
                                        <tr key = {stock.id}>
                                             <td> {stock.stockSymbol} </td>   
                                             <td> {stock.marketPrice}</td>
                                          {  stock.upDown ?  
                                             <td> <FontAwesomeIcon icon={faArrowAltCircleUp} style={{ color: 'green' }}   size="2x"/></td> 
                                             :
                                             <td> <FontAwesomeIcon icon={faArrowAltCircleDown} style={{ color: 'red' }}   size="2x"/></td> 
                                          }
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

export default ListStockPricingComponent
