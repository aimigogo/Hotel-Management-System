import axios from "axios";

export const listStockItems=()=> axios.get('http://localhost:8080/api/stock/stocks');

export const updateStockItem = (stockId, stockData) => axios.put(`http://localhost:8080/api/stock/update/${stockId}`, stockData);

export const deleteStockItem = (stockId) => axios.delete(`http://localhost:8080/api/stock/delete/${stockId}`);