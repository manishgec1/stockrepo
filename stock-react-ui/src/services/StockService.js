import axios from 'axios';

const STOCK_API_BASE_URL = "http://localhost:9090/api/v1/stocks";

class StockService {

    getViewStocks(){
        return axios.get(STOCK_API_BASE_URL+'pricing');
    }

    getStocks(){
        return axios.get(STOCK_API_BASE_URL);
    }

    getStockById(stockId){
        return axios.get(STOCK_API_BASE_URL + '/' + stockId);
    }

    createStock(stock){
        return axios.post(STOCK_API_BASE_URL, stock);
    }

    updateStock(stock, stockId){
        return axios.put(STOCK_API_BASE_URL + '/' + stockId, stock);
    }

    deleteStock(stockId){
        return axios.delete(STOCK_API_BASE_URL + '/' + stockId);
    }
}

export default new StockService()