package hms_backend.mapper;

import hms_backend.dto.StockDto;
import hms_backend.model.Stock;

public interface StockMapper {

    public static StockDto mapToStockDto(Stock stock){
        return new StockDto(
                stock.getId(),
                stock.getName(),
                stock.getQuantity()
        );
    }

    public static Stock mapToStock(StockDto stockDto){
        return new Stock(
                stockDto.getId(),
                stockDto.getName(),
                stockDto.getQuantity()
        );
    }
}
