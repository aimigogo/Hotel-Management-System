package hms_backend.services;

import hms_backend.dto.StockDto;

import java.util.List;

public interface StockService {

    StockDto createStockItem(StockDto stockDto);
    List<Object> getAllStockItems();

    StockDto updateStockItem(Long id, StockDto stockDto);

    void deleteStockItem(Long id);
}
