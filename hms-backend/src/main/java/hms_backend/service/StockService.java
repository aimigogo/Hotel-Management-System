package hms_backend.service;


import hms_backend.dto.StockDto;

public interface StockService {
    StockDto createStockItem(StockDto stockDto);
}
