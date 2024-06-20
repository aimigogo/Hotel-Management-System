package hms_backend.services;

import hms_backend.dto.StockDto;

import hms_backend.entity.Stock;

import hms_backend.repository.StockRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService{

    private final StockRepository stockRepository;

    @Override
    public StockDto createStockItem(StockDto stockDto) {

        if (stockRepository.findFirstByName(stockDto.getName()).isPresent()){
            throw new EntityExistsException("Stock Item already exists with name"+stockDto.getName());
        }
        Stock stock=new Stock();
        stock.setName(stockDto.getName());
        stock.setQuantity(stockDto.getQuantity());
        Stock createdStockItem=stockRepository.save(stock);
        return createdStockItem.getStockDto();
    }

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

    public StockDto updateStockItem(Long id, StockDto stockDto){
        Stock stockItem=stockRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Stock item not found"));
        stockItem.setName(stockDto.getName());
        stockItem.setQuantity(stockDto.getQuantity());
        stockRepository.save(stockItem);
        return new StockDto();

    }

    public void deleteStockItem(Long id){
        stockRepository.deleteById(id);
    }
}
