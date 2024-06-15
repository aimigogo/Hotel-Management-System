package hms_backend.services;

import hms_backend.dto.StockDto;
import hms_backend.entity.Stock;
import hms_backend.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService{

    private final StockRepository stockRepository;

    @Override
    public List<Object> getAllStockItems() {
        List<Stock> stockItems = stockRepository.findAll();
        return stockItems.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private StockDto convertToDto(Stock stock) {
        StockDto stockDto = new StockDto();
        stockDto.setId(stock.getId());
        stockDto.setName(stock.getName());
        stockDto.setQuantity(stock.getQuantity());
        return stockDto;
    }
}
