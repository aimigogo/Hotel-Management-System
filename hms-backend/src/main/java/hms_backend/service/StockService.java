package hms_backend.service;


import hms_backend.model.Stock;

public interface StockService extends BaseService<Stock,Long>{


    Stock getStockByName(String name);

}
