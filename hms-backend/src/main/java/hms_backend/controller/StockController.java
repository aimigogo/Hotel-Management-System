package hms_backend.controller;

import hms_backend.dto.StockDto;
import hms_backend.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/stocks")
public class StockController {
    private StockService stockService;
    @PostMapping
    public ResponseEntity<StockDto> createStockItem(@RequestBody StockDto stockDto){
        StockDto savedStockItem=stockService.createStockItem(stockDto);
        return new ResponseEntity<>(savedStockItem, HttpStatus.CREATED);
    }
}
