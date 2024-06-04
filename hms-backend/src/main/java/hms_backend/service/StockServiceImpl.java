package hms_backend.service;


import hms_backend.dto.StockDto;
import hms_backend.mapper.StockMapper;
import hms_backend.model.Stock;
import hms_backend.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StockServiceImpl implements StockService{

    private StockRepository stockRepository;
    @Override
    public StockDto createStockItem(StockDto stockDto) {
        Stock stock= StockMapper.mapToStock(stockDto);
        Stock savedStockItem=stockRepository.save(stock);
        return StockMapper.mapToStockDto(savedStockItem);
    }
}
